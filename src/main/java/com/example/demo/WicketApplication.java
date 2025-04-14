package com.example.demo;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.ResourceReference;

public class WicketApplication extends WebApplication {
    @Override
    protected void init() {
        super.init();

        getServletContext().addFilter("CORSFilter", new CorsFilter())
            .addMappingForUrlPatterns(null, false, "/*");

        // Mount the frontend page for client-side routing
        mountPage("/front-end/*", FrontendPage.class);

        // Mount the login page
        mountPage("/login", LoginPage.class);

        // Mount the token validation resource
        mountResource("/api/auth/validate", new ResourceReference("validateTokenResource") {
            private static final long serialVersionUID = 1L;

            @Override
            public ValidateTokenResource getResource() {
                return new ValidateTokenResource();
            }
        });

        // Mount the static file resource
        mountResource("/front-end/{filename}", new ResourceReference("staticFileResource") {
            private static final long serialVersionUID = 1L;

            @Override
            public StaticFileResource getResource() {
                // Use RequestCycle to access the current request
                String filename = RequestCycle.get().getRequest().getRequestParameters()
                        .getParameterValue("filename").toString();
                String filePath = "src/main/webapp/front-end/" + filename;
                return new StaticFileResource(filePath);
            }
        });

        // Mount the login resource
        mountResource("/api/auth/login", new ResourceReference("loginResource") {
            private static final long serialVersionUID = 1L;

            @Override
            public LoginResource getResource() {
                return new LoginResource();
            }
        });
    }

    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }
}