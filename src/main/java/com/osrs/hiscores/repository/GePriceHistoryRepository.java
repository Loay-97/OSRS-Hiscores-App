package com.osrs.hiscores.repository;

import com.osrs.hiscores.entity.GePriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GePriceHistoryRepository extends JpaRepository<GePriceHistory, Long> {

    List<GePriceHistory> findAllByItemNameOrderByTimestampAsc(String itemName);
}
