package com.example.demo;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

@SuppressWarnings("unused")
public class LoginPage extends WebPage {
    private String username; 
    private String password;

    public LoginPage() {
        Form<Void> loginForm = new Form<Void>("loginForm") {
            @Override
            protected void onSubmit() {
                // Simple authentication logic
                if ("admin".equals(username) && "password".equals(password)) {
                    getSession().setAttribute("username", username); // Store username in session
                    setResponsePage(HomePage.class); // Redirect to homepage
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