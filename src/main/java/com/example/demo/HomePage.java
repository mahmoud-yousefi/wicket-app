package com.example.demo;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
    public HomePage(PageParameters parameters) {
        super(parameters);

        String username = (String) getSession().getAttribute("username");

        if (username == null) {
            throw new RestartResponseException(LoginPage.class); // Redirect to login page
        }

        add(new Label("message", "Welcome to the Wicket Homepage, " + username + "!"));
    }
}