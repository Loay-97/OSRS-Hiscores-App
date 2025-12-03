package com.osrs.hiscores.controller;

import com.osrs.hiscores.entity.GePriceHistory;
import com.osrs.hiscores.service.GePriceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ge")
@CrossOrigin(origins = "*")
public class GePriceController {

    private final GePriceService service;

    public GePriceController(GePriceService service) {
        this.service = service;
    }

    @GetMapping("/price/{itemName}")
    public int getPrice(@PathVariable String itemName) throws Exception {
        return service.fetchPriceByName(itemName);
    }

    @GetMapping("/history/{itemName}")
    public List<GePriceHistory> getHistory(@PathVariable String itemName) {
        return service.getHistory(itemName);
    }
}
