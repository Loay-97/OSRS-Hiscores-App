package com.osrs.hiscores.repository;

import com.osrs.hiscores.entity.PlayerLookupHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerLookupHistoryRepository extends JpaRepository<PlayerLookupHistory, Long> {
    Optional<PlayerLookupHistory> findByPlayerUsernameIgnoreCase(String playerUsername);
}
