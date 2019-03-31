package com.ichop.basicstatistics.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ichop.basicstatistics.domain.entities.PlayerStatistics;
import com.ichop.basicstatistics.domain.models.service.PlayerStatisticsServiceModel;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Objects;

@Configuration
public class BeanConfiguration {


    @Bean
    public ObjectMapper getObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    public ModelMapper getModelMapper(){
        ModelMapper modelMapper = new ModelMapper();


        Converter<Integer, LocalDateTime> epochBraaaaat = new AbstractConverter<>() {
            protected LocalDateTime convert(Integer source) {
                LocalDateTime date =
                        LocalDateTime
                                .ofEpochSecond(source, 0, OffsetDateTime.now().getOffset());
                return date;
            }
        };

        PropertyMap<PlayerStatistics, PlayerStatisticsServiceModel> playerStatsPropMap = new PropertyMap<>() {
            protected void configure() {
                using(epochBraaaaat).map(source.getLastJoin()).setLastJoin(null);
            }
        };

        modelMapper.addMappings(playerStatsPropMap);


        return modelMapper;
    }

}
