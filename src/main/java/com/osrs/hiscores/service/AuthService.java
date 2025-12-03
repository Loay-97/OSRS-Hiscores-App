package com.osrs.hiscores.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.osrs.hiscores.entity.UserProfile;
import com.osrs.hiscores.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final RestClient restClient;
    private final UserProfileRepository userProfileRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.service-role-key}")
    private String serviceRoleKey;

    public AuthService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
        this.restClient = RestClient.builder().build();
    }

    // -------- REGISTER ----------
    public JsonNode register(String email, String password, String displayName) {
        String url = supabaseUrl + "/auth/v1/signup";

        if (displayName == null || displayName.isBlank()) {
            displayName = "Player" + (int)(Math.random() * 10000);
        }

        String requestBody = String.format("""
    {
      "email": "%s",
      "password": "%s"
    }
    """, email, password);

        try {
            String response = restClient.post()
                    .uri(url)
                    .header("apikey", serviceRoleKey)
                    .header("Authorization", "Bearer " + serviceRoleKey)
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                    .retrieve()
                    .body(String.class);

            JsonNode json = objectMapper.readTree(response);

            // Supabase user id check
            if (!json.has("id") || json.get("id").isNull()) {
                throw new RuntimeException("Register failed: " + json.toString());
            }

            UUID supabaseId = UUID.fromString(json.get("id").asText());

            // Lokaal UserProfile aanmaken
            UserProfile profile = new UserProfile(supabaseId, email);
            profile.setDisplayName(displayName);
            userProfileRepository.save(profile);

            // Voeg displayName toe aan JSON response
            if (json instanceof ObjectNode objectNode) {
                objectNode.put("displayName", displayName);
            }

            return json;

        } catch (Exception e) {
            throw new RuntimeException("Register failed: " + e.getMessage(), e);
        }
    }


    // -------- LOGIN ----------
    public JsonNode login(String email, String password) {
        String url = supabaseUrl + "/auth/v1/token?grant_type=password";

        String requestBody = String.format("""
    {
      "email": "%s",
      "password": "%s"
    }
    """, email, password);

        try {
            String response = restClient.post()
                    .uri(url)
                    .header("apikey", serviceRoleKey)
                    .header("Authorization", "Bearer " + serviceRoleKey)
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                    .retrieve()
                    .body(String.class);

            JsonNode json = objectMapper.readTree(response);

            // Check of Supabase login geslaagd is
            if (!json.has("user") || json.get("user").isNull()) {
                throw new RuntimeException("Login failed: " + json.toString());
            }

            // Vind lokaal profiel op email
            Optional<UserProfile> optionalProfile = userProfileRepository.findByEmail(email);
            if (optionalProfile.isEmpty()) {
                throw new RuntimeException("Login failed: account not found locally. Please register first.");
            }

            UserProfile profile = optionalProfile.get();

            // Voeg displayName toe aan response
            if (json instanceof ObjectNode objectNode) {
                objectNode.put("displayName", profile.getDisplayName());
            }

            return json;

        } catch (Exception e) {
            throw new RuntimeException("Login failed: " + e.getMessage(), e);
        }
    }

}
