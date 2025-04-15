package com.example.demo;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.ResourceReference;

import java.security.SecureRandom;
import java.util.Base64;

public class WicketApplication extends WebApplication {
    private static final SecureRandom secureRandom = new SecureRandom();
    @Override
    protected void init() {
        super.init();

        getServletContext().addFilter("CORSFilter", new CorsFilter())
            .addMappingForUrlPatterns(null, false, "/*");

        mountPage("/front-end/*", FrontendPage.class);

        mountPage("/login", LoginPage.class);

        mountResource("/api/auth/validate", new ResourceReference("validateTokenResource") {
            private static final long serialVersionUID = 1L;

            @Override
            public ValidateTokenResource getResource() {
                return new ValidateTokenResource();
            }
        });

        mountResource("/front-end/{filename}", new ResourceReference("staticFileResource") {
            private static final long serialVersionUID = 1L;

            @Override
            public StaticFileResource getResource() {
                String filename = RequestCycle.get().getRequest().getRequestParameters()
                        .getParameterValue("filename").toString();
                String filePath = "src/main/webapp/front-end/" + filename;
                return new StaticFileResource(filePath);
            }
        });
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