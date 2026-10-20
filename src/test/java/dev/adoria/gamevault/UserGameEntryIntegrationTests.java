package dev.adoria.gamevault;

import dev.adoria.gamevault.entry.GameStatus;
import dev.adoria.gamevault.entry.UserGameEntry;
import dev.adoria.gamevault.entry.UserGameEntryRepository;
import dev.adoria.gamevault.game.Game;
import dev.adoria.gamevault.game.GameRepository;
import dev.adoria.gamevault.user.User;
import dev.adoria.gamevault.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class UserGameEntryIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserGameEntryRepository userGameEntryRepository;

    @AfterEach
    void cleanUp() {
        userGameEntryRepository.deleteAll();
        userRepository.deleteAll();
        gameRepository.deleteAll();
    }

//    @Test
//    void existingUserWithoutEntriesReturnsEmptyList() throws Exception {
//        User savedUser = userRepository.save(new User("TestName", "test@email.com"));
//        mockMvc.perform(get("/users/{userId}/entries", savedUser.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().json("[]"));
//    }
//
//    @Test
//    void nonexistentUserReturnsNotFound() throws Exception {
//        mockMvc.perform(get("/users/{userId}/entries", -1))
//            .andExpect(status().isNotFound())
//            .andExpect(jsonPath("$.error").value("User with id -1 not found"));
//    }
//
//    @Test
//    void returnsOnlyRequestedUsersEntriesWithGameDetails() throws Exception {
//        Game savedGame = gameRepository.save(new Game("Deadlock", LocalDate.of(2024, 8, 23)));
//
//        User savedUser1 = userRepository.save(new User("TestName", "test@email.com"));
//        UserGameEntry savedEntry = userGameEntryRepository.save(new UserGameEntry(savedUser1, savedGame,
//            GameStatus.COMPLETED, 8.5));
//
//        User savedUser2 = userRepository.save(new User("different_name", "another@email.com"));
//        userGameEntryRepository.save(new UserGameEntry(savedUser2, savedGame, GameStatus.COMPLETED, 8.5));
//
//        mockMvc.perform(get("/users/{userId}/entries", savedUser1.getId()))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$.length()").value(1))
//            .andExpect(jsonPath("$[0].id").value(savedEntry.getId()))
//            .andExpect(jsonPath("$[0].gameId").value(savedGame.getId()))
//            .andExpect(jsonPath("$[0].gameTitle").value("Deadlock"))
//            .andExpect(jsonPath("$[0].status").value("COMPLETED"))
//            .andExpect(jsonPath("$[0].score").value(8.5));
//    }
}