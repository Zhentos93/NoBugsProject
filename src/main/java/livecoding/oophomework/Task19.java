package livecoding.oophomework;

import java.util.HashSet;
import java.util.Set;

class Post {
    private String id;
    private String text;
    private Set<String> likedUsers = new HashSet<>();

    public Post(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public void addLike(String user) {
        likedUsers.add(user);
    }

    public void removeLike(String user) {
        likedUsers.remove(user);
    }

    public int countLikes() {
        return likedUsers.size();
    }

    public boolean isLikedBy(String user) {
        return likedUsers.contains(user);
    }
}

public class Task19 {
    public static void main(String[] args) {
        Post post = new Post("1", "Космос");

        post.addLike("Alex");
        post.addLike("Maria");
        post.addLike("Alex");

        System.out.println("Количество лайков: " + post.countLikes());

        post.removeLike("Alex");
        System.out.println("Количество лайков: " + post.countLikes());

        System.out.println("Пользователь поставил лайк: " + post.isLikedBy("Alex"));
        System.out.println("Пользователь поставил лайк: " + post.isLikedBy("Maria"));
    }
}
