package com.ichop.linkaccount.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichop.linkaccount.domain.entities.Key;
import com.ichop.linkaccount.domain.models.service.KeyServiceModel;
import com.ichop.linkaccount.repositories.KeyRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class KeyServicesUnitTests {

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private KeyRepository keyRepository;

    @Spy
    @InjectMocks
    private KeyServicesImp keyServices;


    @Before
    public void setUp() {
    }

    @Test
    public void isKeyExpired_withNotExistingKey_shouldReturnTrue() {
        String key = "key";
        when(this.keyRepository.findByKey(key)).thenReturn(null);

        boolean result = this.keyServices.isKeyExpired(key);

        assertTrue(result);
    }

    @Test
    public void isKeyExpired_withExpiredKey_shouldReturnTrue() {
        String key = "key";
        Key foundedKey = mock(Key.class);
        LocalDateTime expirationDate = LocalDateTime.of(1000, 1, 1, 1, 1);

        when(foundedKey.getExpirityDate()).thenReturn(expirationDate);
        when(this.keyRepository.findByKey(key)).thenReturn(foundedKey);

        boolean result = this.keyServices.isKeyExpired(key);

        assertTrue(result);
    }

    @Test
    public void isKeyExpired_withValidKey_shouldReturnFalse() {
        String key = "key";
        Key foundedKey = mock(Key.class);
        LocalDateTime expirationDate = LocalDateTime.of(3000, 1, 1, 1, 1);

        when(foundedKey.getExpirityDate()).thenReturn(expirationDate);
        when(this.keyRepository.findByKey(key)).thenReturn(foundedKey);

        boolean result = this.keyServices.isKeyExpired(key);

        assertFalse(result);
    }

    @Test
    public void isKeyValid_withNotExistingKey_shouldReturnFalse() {
        String key = "key";

        when(this.keyRepository.findByKey(key)).thenReturn(null);

        boolean result = this.keyServices.isKeyValid(key);

        assertFalse(result);
    }

    @Test
    public void isKeyValid_withExpiredKey_shouldReturnFalse() {
        String key = "key";
        Key foundedKey = mock(Key.class);

        when(foundedKey.getKey()).thenReturn(key);
        doReturn(true).when(this.keyServices).isKeyExpired(key);
        when(this.keyRepository.findByKey(key)).thenReturn(foundedKey);

        boolean result = this.keyServices.isKeyValid(key);

        assertFalse(result);
    }

    @Test
    public void isKeyValid_withValidKey_shouldReturnTrue() {
        String key = "key";
        Key foundedKey = mock(Key.class);

        when(foundedKey.getKey()).thenReturn(key);
        doReturn(false).when(this.keyServices).isKeyExpired(key);
        when(this.keyRepository.findByKey(key)).thenReturn(foundedKey);

        boolean result = this.keyServices.isKeyValid(key);

        assertTrue(result);
    }

    @Test
    public void getByKey_withNotExistingKey_shouldReturnNull() {
        String key = "key";

        KeyServiceModel result = this.keyServices.getByKey(key);

        assertNull(result);
    }

    @Test
    public void getByKey_withValidData_shouldInvokeMethods() {
        String key = "key";
        Key foundedKey = mock(Key.class);

        when(this.keyRepository.findByKey(key)).thenReturn(foundedKey);
        this.keyServices.getByKey(key);

        verify(this.objectMapper,times(1)).convertValue(foundedKey,KeyServiceModel.class);
    }

    @Test
    public void getPlayerByUUID_withNotExisting_shouldReturnNull() {
        String uuid = "uuid";

        KeyServiceModel result = this.keyServices.getByPlayerUUID(uuid);

        assertNull(result);
    }

    @Test
    public void getPlayerByUUID_withValidData_shouldInvokeMethods() {
        String uuid = "uuid";
        Key foundedKey = mock(Key.class);

        when(this.keyRepository.findByPlayerUUID(uuid)).thenReturn(foundedKey);
        KeyServiceModel result = this.keyServices.getByPlayerUUID(uuid);

        verify(this.objectMapper,times(1)).convertValue(foundedKey,KeyServiceModel.class);
    }

    @Test
    public void deleteByUUID_withNotExistingKey_shouldNotInvokeMethods() {
        String uuid = "uuid";
        Key foundedKey = mock(Key.class);

        when(this.keyRepository.findByPlayerUUID(uuid)).thenReturn(null);
        this.keyServices.deleteByUUID(uuid);

        verify(this.keyRepository,times(0)).delete(foundedKey);
    }

    @Test
    public void deleteByUUID_withNotExistingKey_shouldInvokeMethods() {
        String uuid = "uuid";
        Key foundedKey = mock(Key.class);

        when(this.keyRepository.findByPlayerUUID(uuid)).thenReturn(foundedKey);
        this.keyServices.deleteByUUID(uuid);

        verify(this.keyRepository,times(1)).delete(foundedKey);
    }


}
