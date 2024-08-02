package tech.nocountry.eco_rent.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class GeneradorTokenService {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int TOKEN_LENGTH = 8;
    private final SecureRandom random = new SecureRandom();

    private String generateToken() {
        StringBuilder token = new StringBuilder(TOKEN_LENGTH);
        for (int i = 0; i < TOKEN_LENGTH; i++) {
            token.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return token.toString();
    }

    public String generateRetiroToken() {
        return "R" + generateToken();
    }

    public String generateEntregaToken() {
        return "E" + generateToken();
    }
}