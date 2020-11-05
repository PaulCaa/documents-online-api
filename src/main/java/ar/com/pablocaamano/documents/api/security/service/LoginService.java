package ar.com.pablocaamano.documents.api.security.service;

public interface LoginService {
    String getTokenBy(String username, String trace);
}
