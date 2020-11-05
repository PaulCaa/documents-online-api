package ar.com.pablocaamano.documents.api.security.service.impl;

import ar.com.pablocaamano.documents.api.security.Constants;
import ar.com.pablocaamano.documents.api.security.service.LoginService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static ar.com.pablocaamano.documents.api.security.Constants.TOKEN_EXPIRATION_TIME;

@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Override
    public String getTokenBy(String username, String trace) {
        LOGGER.info("[" + trace + "] generating user token");
        String token = Jwts.builder()
                .setId("JWT").setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, Constants.SECRET_KEY.getBytes()).compact();
        return Constants.PREFIX + token;
    }
}
