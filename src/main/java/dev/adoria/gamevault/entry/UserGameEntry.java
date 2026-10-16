package dev.adoria.gamevault.entry;

import dev.adoria.gamevault.game.Game;
import dev.adoria.gamevault.user.User;
import jakarta.persistence.*;

@Entity
@Table(check = @CheckConstraint(name = "chk_user_game_entry_score", constraint = "score >= 0 AND score <= 10"),
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_user_game_entry_user_id_game_id", columnNames = {"user_id", "game_id"}),
    })
public class UserGameEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GameStatus status;

    @Column(nullable = true)
    private Double score;

    // NOTE(adoria): This no-argument constructor is mandatory by hibernate
    protected UserGameEntry() {
    }

    public UserGameEntry(User user, Game game, GameStatus status, Double score) {
        this.user = user;
        this.game = game;
        this.status = status;
        this.score = score;
    }

    public UserGameEntry(User user, Game game, GameStatus status) {
        this(user, game, status, null);
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Game getGame() {
        return game;
    }

    public Long getGameId() {
        return game.getId();
    }

    public String getGameTitle() {
        return game.getTitle();
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getScore() {
        return score;
    }
}