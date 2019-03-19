package itsgosho.config;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Valid;

@Configuration
public class DropboxConfiguration {

    @Value("${dropbox.accessToken}")
    private String accessToken;

    @Bean
    public DbxClientV2 getDropBoxClient(){
        DbxRequestConfig config = DbxRequestConfig.newBuilder("iChop").build();
        return new DbxClientV2(config,this.accessToken);
    }

}
