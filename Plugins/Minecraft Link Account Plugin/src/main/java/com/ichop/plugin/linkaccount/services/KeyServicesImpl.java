package com.ichop.plugin.linkaccount.services;

import com.ichop.plugin.linkaccount.constants.KeyConstants;
import com.ichop.plugin.linkaccount.domain.entities.Key;
import com.ichop.plugin.linkaccount.domain.models.binding.KeyCreateBindingModel;
import com.ichop.plugin.linkaccount.domain.models.service.KeyServiceModel;
import com.ichop.plugin.linkaccount.repository.KeyRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.time.LocalDateTime;

public class KeyServicesImpl implements KeyServices {

    public static final Integer KEY_LENGTH = 4;

    private final KeyRepository keyRepository;
    private final ModelMapper modelMapper;

    @Inject
    public KeyServicesImpl(KeyRepository keyRepository, ModelMapper modelMapper) {
        this.keyRepository = keyRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public KeyServiceModel create(KeyCreateBindingModel keyCreateBindingModel) {

        Key key = this.modelMapper.map(keyCreateBindingModel, Key.class);
        this.deleteLastByUUID(key.getPlayerUUID());

        key.setLinkKey(RandomStringUtils.randomAlphabetic(KEY_LENGTH));
        key.setExpirationDate(LocalDateTime.now().plusSeconds(KeyConstants.KEY_EXPIRATION_IN_SECONDS));

        key = this.keyRepository.save(key);
        return this.modelMapper.map(key, KeyServiceModel.class);
    }

    @Override
    public boolean isKeyExpired(String key) {

        Key linkKey = this.keyRepository.findByKey(key);

        if (linkKey == null) {
            return true;
        }


        boolean isExpired = linkKey.getExpirationDate().compareTo(LocalDateTime.now()) < 0;

        if (isExpired) {
            return false;
        }

        return true;
    }

    private void deleteLastByUUID(String uuid) {
        Key key = this.keyRepository.findByPlayerUUID(uuid);
        if (key != null) {
            this.keyRepository.delete(key);
        }

    }

}
