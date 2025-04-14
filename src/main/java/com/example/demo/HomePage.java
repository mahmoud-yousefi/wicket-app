package com.example.demo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;

public class HomePage extends WebPage {

    public HomePage() {
        HttpServletRequest request = (HttpServletRequest) getRequestCycle().getRequest().getContainerRequest();
        Cookie[] cookies = request.getCookies();
        String token = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt_token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token == null || !JwtUtil.validateToken(token)) {
            throw new RestartResponseException(LoginPage.class);
        }

        String username = JwtUtil.getUsernameFromToken(token);
        add(new Label("message", "Welcome to the Wicket Homepage, " + username + "!"));

        Form<Void> logoutForm = new Form<>("logoutForm") {
            @Override
            protected void onSubmit() {
                HttpServletRequest request = (HttpServletRequest) getRequestCycle().getRequest().getContainerRequest();
                HttpServletResponse response = (HttpServletResponse) getRequestCycle().getResponse().getContainerResponse();

                Cookie jwtCookie = new Cookie("jwt_token", "");
                jwtCookie.setHttpOnly(true);
                jwtCookie.setSecure(true);
                jwtCookie.setPath("/");
                jwtCookie.setMaxAge(0);
                response.addCookie(jwtCookie);

                getSession().invalidate();
                setResponsePage(LoginPage.class);
            }
        };

        add(logoutForm);
    }
}