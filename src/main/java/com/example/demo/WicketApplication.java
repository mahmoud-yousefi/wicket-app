package com.example.demo;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

public class WicketApplication extends WebApplication {
    // @Override
    // protected void init() {
    //     super.init();
    //     mountPage("/login", LoginPage.class); // Explicitly map /login to LoginPage
    // }

    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class; // Default homepage
    }
}