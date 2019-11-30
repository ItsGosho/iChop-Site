package com.ichop.plugin.linkaccount.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichop.plugin.linkaccount.domain.entities.Key;
import com.ichop.plugin.linkaccount.constants.KeyConstants;
import com.ichop.plugin.linkaccount.domain.models.binding.KeyCreateBindingModel;
import com.ichop.plugin.linkaccount.domain.models.service.KeyServiceModel;
import com.ichop.plugin.linkaccount.repository.KeyRepository;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.RandomStringUtils;

import javax.inject.Inject;
import java.time.LocalDateTime;

public class KeyServicesImp implements KeyServices {

    public static final String IS_ACCOUNT_LINKED_URL = "http://localhost:8000/player/is-account-linked?uuid={uuid}";
    public static final Integer SHORT_KEY_LENGTH = 5;

    private final KeyRepository keyRepository;
    private final ObjectMapper objectMapper;

    @Inject
    public KeyServicesImp(KeyRepository keyRepository, ObjectMapper objectMapper) {
        this.keyRepository = keyRepository;
        this.objectMapper = objectMapper;
    }


    @Override
    public KeyServiceModel create(KeyCreateBindingModel keyCreateBindingModel) {

        Key key = this.objectMapper.convertValue(keyCreateBindingModel,Key.class);
        this.deleteLastByUUID(key.getPlayerUUID());
        String randomKey = RandomStringUtils.randomAlphabetic(SHORT_KEY_LENGTH);

        key.setKey(randomKey);
        key.setExpirityDate(LocalDateTime.now().plusSeconds(KeyConstants.KEY_EXPIRATION_IN_SECONDS));

        return this.objectMapper.convertValue(this.keyRepository.save(key),KeyServiceModel.class);
    }

    @Override
    public boolean isKeyExpired(String key) {

        Key keyy = this.keyRepository.findByKey(key);

        if(keyy == null){
            return true;
        }


        boolean isDateExpired = keyy.getExpirityDate().compareTo(LocalDateTime.now()) < 0;

        if (isDateExpired) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isAccountLinked(String uuid) {

        String url = IS_ACCOUNT_LINKED_URL.replace("{uuid}",uuid);
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get(url)
                    .header("accept","application/json")
                    .asJson();

            return (boolean) jsonResponse.getBody().getObject().get("isAccountLinked");

        } catch (UnirestException e) {
        }
        return false;
    }

    private void deleteLastByUUID(String uuid) {
        Key key = this.keyRepository.findByPlayerUUID(uuid);
        if (key != null) {
            this.keyRepository.delete(key);
        }

    }

}
