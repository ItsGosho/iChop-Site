package com.ichop.core.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 *
 * Responsible to get resource file under resources folder ,then
 * to replace all placeholders that match the @field placeholder
 * and then to return the file as String with the replaced values.
 *
 *
 * */
@Getter
@Setter
@Builder
public class CustomTemplateEngine {

    private String viewLocation;
    private Map<String, String> properties;
    private String result;
    private String placeholder;


    public static class CustomTemplateEngineBuilder {
        private String fileContent;

        public CustomTemplateEngineBuilder proceedParameters() {
            this.proceedFileContent();
            this.proceedReplacingPlaceholders();
            return this;
        }

        private void proceedFileContent() {
            URL url = this.getClass().getClassLoader().getResource(this.viewLocation);

            File file = null;
            try {
                file = new File(url.toURI());
            } catch (URISyntaxException e) {
                file = new File(url.getPath());
            }

            Stream<String> allLines = null;
            try {
                allLines = Files.lines(Paths.get(file.getAbsolutePath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String result = allLines
                    .collect(Collectors.joining("\n"));
            this.fileContent = result;
        }

        private void proceedReplacingPlaceholders() {
            this.result = fileContent;
            this.properties.forEach((key, value) -> {
                String placy = this.placeholder.replace("VALUE", "%s");
                String placeholder = String.format(placy, key);
                this.result = this.result.replace(placeholder, value);
            });
        }

    }
}
