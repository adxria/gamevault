import java.util.concurrent.atomic.AtomicInteger;

public class Game {
    private String title;
    private static final AtomicInteger idCounter = new AtomicInteger(1);

    private double rating;
    private final int id;

    public Game(String title) {
        this.id = idCounter.getAndIncrement();
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }
}
