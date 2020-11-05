package ar.com.pablocaamano.documents.api.security.model;

public class User {

    private String username;
    private String password;
    private String token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "{'username': '" + username + "'," +
                "'token': '" + token + "'}";
    }
}
