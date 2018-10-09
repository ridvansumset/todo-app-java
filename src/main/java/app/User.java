package app;

// import com.oracle.json;

public class User {

    private final String uid;
    private final String email;
    // private final JsonArray interests;

    public User(String uid, String email) {
        this.uid = uid;
        this.email = email;
        // this.interests = interests;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }
}