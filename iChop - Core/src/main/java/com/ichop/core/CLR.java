package com.ichop.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class CLR implements CommandLineRunner {

    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    @Autowired
    public CLR(ModelMapper modelMapper, ObjectMapper objectMapper) {
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {

//        HashMap<String,Object> testMap = new HashMap<>();
//        testMap.put("playerName","ItsGosho");
//        testMap.put("playerUUID","241234");
//        testMap.put("siteUserUsername","123");


        UserServiceModel userServiceModel = new UserServiceModel();
        userServiceModel.setPassword("123");
        Set<UserServiceModel> followings = new HashSet<>();
        UserServiceModel foll = new UserServiceModel();
        foll.setUsername("BUHAHAHAHAHA");
        followings.add(foll);
        userServiceModel.setFollowings(followings);
        Map<String,Object> test = this.objectMapper.convertValue(userServiceModel,Map.class);
        System.out.println();

        UserServiceModel MURAAA = this.objectMapper.convertValue(test,UserServiceModel.class);
        System.out.println();
    }


}
