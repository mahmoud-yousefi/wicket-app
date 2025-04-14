package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.request.resource.AbstractResource;

public class StaticFileResource extends AbstractResource {

    private final String filePath;

    public StaticFileResource(String filePath) {
        this.filePath = filePath;
    }

    @Override
    protected ResourceResponse newResourceResponse(Attributes attributes) {
        ResourceResponse response = new ResourceResponse();

        File file = new File(filePath);
        if (!file.exists()) {
            response.setError(HttpServletResponse.SC_NOT_FOUND, "File not found");
            return response;
        }

        try {
            String mimeType = Files.probeContentType(file.toPath());
            response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
            response.setWriteCallback(new WriteCallback() {
                @Override
                public void writeData(Attributes attributes) throws IOException {
                    attributes.getResponse().write((CharSequence) new FileInputStream(file));
                }
            });
        } catch (IOException e) {
            response.setError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error reading file");
        }

        return response;
    }
}