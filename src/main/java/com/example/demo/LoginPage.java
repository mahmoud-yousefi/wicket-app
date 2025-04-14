package com.example.demo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

public class LoginPage extends WebPage {
    private String username;
    private String password;

    public LoginPage() {
        Form<Void> loginForm = new Form<>("loginForm") {
            @Override
            protected void onSubmit() {
                // Validate credentials (hardcoded for simplicity)
                if ("admin".equals(username) && "password".equals(password)) {
                    // Generate JWT token
                    String token = JwtUtil.generateToken(username);

                    // Store token in a secure cookie
                    HttpServletResponse response = (HttpServletResponse) getRequestCycle().getResponse().getContainerResponse();
                    Cookie jwtCookie = new Cookie("jwt_token", token);
                    jwtCookie.setHttpOnly(true); // Prevent JavaScript access
                    jwtCookie.setSecure(true);   // Only send over HTTPS
                    jwtCookie.setPath("/");      // Available across the entire domain
                    jwtCookie.setMaxAge(3600);   // 1 hour expiration
                    response.addCookie(jwtCookie);

                    setResponsePage(HomePage.class); // Redirect to home page
                } else {
                    error("Invalid credentials"); // Display error message
                }
            }
        };

        // Add form fields
        loginForm.add(new TextField<>("username", new PropertyModel<>(this, "username")));
        loginForm.add(new PasswordTextField("password", new PropertyModel<>(this, "password")));

        add(loginForm); // Add form to the page
    }
}