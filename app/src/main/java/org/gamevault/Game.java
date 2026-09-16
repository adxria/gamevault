package org.gamevault;

import java.util.concurrent.atomic.AtomicInteger;

public class Game {
    private static final AtomicInteger idCounter = new AtomicInteger(1);
    private final int id;
    private String title;

    Game(String title) {
        this.id = idCounter.getAndIncrement();
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }
}
