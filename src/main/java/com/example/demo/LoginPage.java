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
                if ("admin".equals(username) && "password".equals(password)) {
                    String token = JwtUtil.generateToken(username);

                    HttpServletResponse response = (HttpServletResponse) getRequestCycle().getResponse().getContainerResponse();
                    Cookie jwtCookie = new Cookie("jwt_token", token);
                    jwtCookie.setHttpOnly(false);
                    jwtCookie.setSecure(false);
                    jwtCookie.setPath("/");
                    jwtCookie.setMaxAge(3600);
                    response.addCookie(jwtCookie);

                    setResponsePage(HomePage.class);
                } else {
                    error("Invalid credentials");
                }
            }
        };

        loginForm.add(new TextField<>("username", new PropertyModel<>(this, "username")));
        loginForm.add(new PasswordTextField("password", new PropertyModel<>(this, "password")));

        add(loginForm);
    }
}