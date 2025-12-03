package com.osrs.hiscores.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.osrs.hiscores.entity.GePriceHistory;
import com.osrs.hiscores.repository.GePriceHistoryRepository;
import com.osrs.hiscores.mapping.ItemIdLookup;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GePriceService {

    private final GePriceHistoryRepository repository;
    private final ObjectMapper mapper = new ObjectMapper();

    private static final String GE_API_URL =
            "https://prices.runescape.wiki/api/v1/osrs/latest?id=";

    public GePriceService(GePriceHistoryRepository repository) {
        this.repository = repository;
    }

    // ---------- Haal GE Prijs op via itemName ----------
    public int fetchPriceByName(String itemName) throws Exception {

        // 1. Controleer of item bestaat via lookup
        if (!ItemIdLookup.exists(itemName)) {
            throw new RuntimeException("Item not found: " + itemName);
        }

        // 2. itemName -> itemId
        int itemId = ItemIdLookup.getItemId(itemName);

        // ----------------- API REQUEST -----------------
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GE_API_URL + itemId))
                .header("User-Agent", "Java-Spring")
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonNode body = mapper.readTree(response.body());

        // ----------------- VEILIGE JSON CHECKS -----------------
        if (!body.has("data")) {
            throw new RuntimeException("GE API returned no 'data' field for: " + itemName);
        }

        JsonNode dataNode = body.get("data");

        if (!dataNode.has(String.valueOf(itemId))) {
            throw new RuntimeException("GE API returned no price entry for item: " + itemName);
        }

        JsonNode itemNode = dataNode.get(String.valueOf(itemId));

        if (!itemNode.has("high")) {
            throw new RuntimeException("GE API returned no 'high' price for: " + itemName);
        }

        // → veilig prijs uitlezen
        int price = itemNode.get("high").asInt();

        // ----------------- OPSLAAN IN DATABASE -----------------
        GePriceHistory history = new GePriceHistory(itemName, price, LocalDateTime.now());
        repository.save(history);

        return price;
    }

    // ---------- Haal historie op ----------
    public List<GePriceHistory> getHistory(String itemName) {
        return repository.findAllByItemNameOrderByTimestampAsc(itemName);
    }
}
