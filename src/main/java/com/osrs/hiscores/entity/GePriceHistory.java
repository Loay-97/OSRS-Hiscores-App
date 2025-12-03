package com.osrs.hiscores.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ge_price_history")
public class GePriceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- itemName in plaats van itemId ---
    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    public GePriceHistory() {}

    // Nieuwe constructor die itemName accepteert
    public GePriceHistory(String itemName, int price, LocalDateTime timestamp) {
        this.itemName = itemName;
        this.price = price;
        this.timestamp = timestamp;
    }

    // --- Getters & Setters ---
    public Long getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public int getPrice() {
        return price;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
