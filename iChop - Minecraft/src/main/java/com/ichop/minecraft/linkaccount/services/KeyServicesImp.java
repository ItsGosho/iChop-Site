package com.ichop.minecraft.linkaccount.services;

import com.ichop.minecraft.linkaccount.domain.entities.Key;
import com.ichop.minecraft.linkaccount.domain.models.service.KeyServiceModel;
import com.ichop.minecraft.linkaccount.repository.KeyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class KeyServicesImp extends BaseService<Key,KeyRepository> implements KeyServices {

    @Autowired
    public KeyServicesImp(ModelMapper modelMapper, KeyRepository repository) {
        super(modelMapper, repository);
    }

    @Override
    public boolean isKeyExpired(String key) {

        Key keyy = super.repository.findByKey(key);

        if(keyy == null){
            return true;
        }


        boolean isDateExpired = keyy.getExpirityDate().compareTo(LocalDateTime.now()) < 0;

        if (isDateExpired) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isKeyValid(String key) {
        Key entityKey = super.repository.findByKey(key);

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
        Key entityKey = super.repository.findByKey(key);

        KeyServiceModel result = null;
        if(entityKey != null){
            result = super.modelMapper.map(entityKey,KeyServiceModel.class);
        }

        return result;
    }

    @Override
    public KeyServiceModel getByPlayerUUID(String playerUUID) {
        Key entityKey = super.repository.findByPlayerUUID(playerUUID);

        KeyServiceModel result = null;
        if(entityKey != null){
            result = super.modelMapper.map(entityKey,KeyServiceModel.class);
        }

        return result;
    }

    @Override
    public void deleteByUUID(String uuid) {
        Key key = super.repository.findByPlayerUUID(uuid);
        if (key != null) {
            super.repository.delete(key);
        }

    }

}
