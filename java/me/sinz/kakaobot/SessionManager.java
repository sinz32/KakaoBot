package me.sinz.kakaobot;

import java.util.HashMap;

public class SessionManager {

    private static SessionManager instance = null;

    final private HashMap<String, HashMap<String, Replier>> sessions;
    final private HashMap<String, HashMap<String, Replier>> sessions_id;

    private SessionManager() {
        sessions = new HashMap<>();
        sessions_id = new HashMap<>();
    }

    public static SessionManager getInstance() {
        if (instance == null) instance = new SessionManager();
        return instance;
    }

    public void put(long userId, String packageName, String room, Replier replier) {
        String pid_key = packageName + "@" + userId;
        if (sessions.get(pid_key) == null) sessions.put(pid_key, new HashMap<>());
        sessions.get(pid_key).put(room, replier);
    }


    public void put(long userId, String packageName, String room, String chat_id, Replier replier) {
        String pid_key = packageName + "@" + userId;
        if (sessions.get(pid_key) == null) sessions.put(pid_key, new HashMap<>());
        sessions.get(pid_key).put(room, replier);
        if (sessions_id.get(pid_key) == null) sessions_id.put(pid_key, new HashMap<>());
        sessions_id.get(pid_key).put(chat_id, replier);
    }

    public Replier get(long userId, String packageName, String room) {
        String pid_key = packageName + "@" + userId;
        if (sessions.get(pid_key) == null) return null;
        return sessions.get(pid_key).get(room);
    }

    public Replier getById(long userId, String packageName, String chat_id) {
        String pid_key = packageName + "@" + userId;
        if (sessions_id.get(pid_key) == null) return null;
        return sessions_id.get(pid_key).get(chat_id);
    }

    public String[] getRoomList(long userId, String packageName) {
        String pid_key = packageName + "@" + userId;
        if (sessions.get(pid_key) == null) return new String[0];
        return sessions.get(pid_key).keySet().toArray(new String[0]);
    }

    public void clear() {
        sessions.clear();
        sessions_id.clear();
    }

}
