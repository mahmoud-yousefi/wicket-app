package com.example.demo;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

import java.security.SecureRandom;
import java.util.Base64;

public class WicketApplication extends WebApplication {
    private static final SecureRandom secureRandom = new SecureRandom();
    @Override
    protected void init() {
        super.init();
        mountPage("/login", LoginPage.class);
    }

    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }
    public String generateNonce() {
        byte[] bytes = new byte[16];
        secureRandom.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }
}