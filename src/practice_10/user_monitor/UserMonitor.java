package practice_10.user_monitor;

/*
Пример 4. Система для отслеживания уникальных посетителей сайта. Необходимо хранить информацию о посещениях.
*/

import java.util.HashSet;

public class UserMonitor {
    // хранение уникальных сессий
    private HashSet<String> sessions;

    public UserMonitor() {
        this.sessions = new HashSet<>();
    }

    // добавление новой сессии
    public void addNewSession(String session) {
        sessions.add(session);
    }

    // доступ к сессии
    public HashSet<String> getSessions() {
        return this.sessions;
    }

    public void printSessions() {
        System.out.println("Все уникальные сессии:");
        sessions.forEach(System.out::println);
    }
}
