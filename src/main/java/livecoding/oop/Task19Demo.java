package livecoding.oop;

import java.util.HashSet;
import java.util.Set;

class Post {
    private final String id;
    private final String text;
    private final Set<String> likes = new HashSet<>();

    public Post(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public void like(String user) {
        likes.add(user); // Set не добавляет повторный лайк того же пользователя
    }

    public void unlike(String user) {
        likes.remove(user);
    }

    public int getLikesCount() {
        return likes.size(); // Отдельный счётчик не нужен
    }

    public boolean isLikedBy(String user) {
        return likes.contains(user);
    }
}

public class Task19Demo {
    public static void main(String[] args) {
        Post post = new Post("p-1", "Изучаем Set");

        post.like("Alex");
        post.like("Maria");
        post.like("Alex");
        System.out.println("Количество лайков: " + post.getLikesCount());

        post.unlike("Alex");
        System.out.println("Количество лайков: " + post.getLikesCount());
        System.out.println("Лайк Maria: " + post.isLikedBy("Maria"));
    }
}
