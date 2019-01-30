package itsgosho.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//The idea behind this is to load text files and to place content in the placeholder
//Example for placeholder  ${{username}}
//You MUST set the viewLocation and she must be valid ,otherwise it will throw a exception
//If one of the properties mismatch or doesnt exist ,nothing will happen
//Why it uses Builder pattern? - I didn't have other job :D:D:D::D:D
public class GoshoTemplatingEngine {

    // viewLocation must be under resources!
    private String viewLocation;

    private Map<String,String> properties;

    private String result;

    private GoshoTemplatingEngine(TemEngineBuilder temEngineBuilder){
        this.viewLocation = temEngineBuilder.viewLocation;
        this.properties = temEngineBuilder.properties;
        this.result = temEngineBuilder.result;
    }

    public static class TemEngineBuilder {
        private String viewLocation;
        private Map<String,String> properties;
        private String fileContent;
        private String result;

        public TemEngineBuilder(){}

        public TemEngineBuilder setViewLocation(String viewLocation) {
            this.viewLocation = viewLocation;
            return this;
        }

        public TemEngineBuilder setProperties(Map<String, String> properties) {
            this.properties = properties;
            return this;
        }

        public TemEngineBuilder addProperty(String key,String value) {

            if(this.properties==null){
                this.properties = new LinkedHashMap<>();
            }

            this.properties.put(key, value);
            return this;
        }

        public GoshoTemplatingEngine build() {
            this.proceedFileContent();
            this.proceedReplacingPlaceholders();

            return new GoshoTemplatingEngine(this);
        }

        private void proceedFileContent(){
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

        private void proceedReplacingPlaceholders(){
            this.result = fileContent;
            this.properties.forEach((key,value)->{
                String placeholder = String.format("${{%s}}",key);
                this.result = this.result.replace(placeholder,value);
            });
        }
    }

    public String getViewPath() {
        return viewLocation;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public String getResult() {
        return result;
    }
}
