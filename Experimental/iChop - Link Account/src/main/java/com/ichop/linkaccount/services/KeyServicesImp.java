package com.ichop.linkaccount.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichop.linkaccount.domain.entities.Key;
import com.ichop.linkaccount.domain.models.service.KeyServiceModel;
import com.ichop.linkaccount.repositories.KeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class KeyServicesImp extends BaseService<Key,KeyRepository> implements KeyServices {

    @Autowired
    public KeyServicesImp(ObjectMapper objectMapper, KeyRepository repository) {
        super(objectMapper, repository);
    }

    @Override
    public boolean isKeyExpired(String key) {

        Key foundedKey = this.repository.findByKey(key);

        if(foundedKey == null){
            return true;
        }


        boolean isDateExpired = foundedKey.getExpirityDate().compareTo(LocalDateTime.now()) < 0;

        if (isDateExpired) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isKeyValid(String key) {
        Key entityKey = this.repository.findByKey(key);

        if(entityKey == null){
            return false;
        }

        if(this.isKeyExpired(entityKey.getKey())){
            return false;
        }

        return true;
    }

    @Override
    public KeyServiceModel getByKey(String key) {
        Key entityKey = this.repository.findByKey(key);

        if(entityKey != null){
            return this.objectMapper.convertValue(entityKey,KeyServiceModel.class);
        }

        return null;
    }

    @Override
    public KeyServiceModel getByPlayerUUID(String playerUUID) {
        Key entityKey = this.repository.findByPlayerUUID(playerUUID);

        if(entityKey != null){
            return this.objectMapper.convertValue(entityKey,KeyServiceModel.class);
        }

        return null;
    }

    @Override
    public void deleteByUUID(String uuid) {
        Key key = this.repository.findByPlayerUUID(uuid);

        if (key != null) {
            this.repository.delete(key);
        }

    }

}
