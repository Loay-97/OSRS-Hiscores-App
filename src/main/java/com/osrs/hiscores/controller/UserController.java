package com.osrs.hiscores.controller;

import com.osrs.hiscores.entity.UserProfile;
import com.osrs.hiscores.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserProfileRepository userProfileRepository;

    /**
     * Haal de huidige ingelogde gebruiker op
     * @param supabaseUserId Het userId uit het Supabase JWT token
     */
    @GetMapping("/me")
    public UserProfile getCurrentUser(@RequestHeader("supabase-user-id") UUID supabaseUserId) {
        return userProfileRepository.findById(supabaseUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
