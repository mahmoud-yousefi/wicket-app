package com.example.demo;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;

public class HomePage extends WebPage {

    public HomePage() {
        String nonce = ((WicketApplication) getApplication()).generateNonce();
        add(new Label("nonce", nonce));

        String token = (String) getSession().getAttribute("token");

        if (token == null || !JwtUtil.validateToken(token)) {
            throw new RestartResponseException(LoginPage.class); // Redirect to login page
        }

        String username = JwtUtil.getUsernameFromToken(token);

        add(new Label("message", "Welcome to the Wicket Homepage, " + username + "!"));

        Form<Void> logoutForm = new Form<>("logoutForm") {
            @Override
            protected void onSubmit() {
                getSession().invalidate();
                setResponsePage(LoginPage.class);
            }
        };

        add(logoutForm);
    }
}