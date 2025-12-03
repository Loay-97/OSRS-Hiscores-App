package com.osrs.hiscores.controller;

import com.osrs.hiscores.mapping.ItemIdLookup;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/ge")
public class GeItemController {

    @GetMapping("/items")
    public List<String> getAllItems() {
        String[] items = ItemIdLookup.getAllItemNames();
        Arrays.sort(items); // optioneel: alfabetisch sorteren
        return Arrays.asList(items);
    }
}
