package com.example.demo;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class FrontendPage extends WebPage {
    public FrontendPage(PageParameters parameters) {
        add(new org.apache.wicket.markup.html.link.ExternalLink("frontend", "/front-end/index.html"));
    }
}