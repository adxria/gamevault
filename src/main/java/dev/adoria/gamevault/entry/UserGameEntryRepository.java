package dev.adoria.gamevault.entry;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserGameEntryRepository extends JpaRepository<UserGameEntry, Long> {
    boolean existsByUser_IdAndGame_Id(Long userId, Long gameId);

    @EntityGraph(attributePaths = "game")
    List<UserGameEntry> findByUser_Id(Long userId);

    Optional<UserGameEntry> findByIdAndUser_Id(Long entryId, Long userId);
}