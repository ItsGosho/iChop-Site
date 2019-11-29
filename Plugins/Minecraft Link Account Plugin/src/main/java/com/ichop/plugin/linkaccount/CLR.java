package com.ichop.plugin.linkaccount;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import javax.persistence.Persistence;

public class CLR {

    public static final String IS_ACCOUNT_LINKED_URL = "http://http://localhost:8000/player/is-account-linked?uuid={uuid}";

    public static void main(String[] args) {
//        String url = IS_ACCOUNT_LINKED_URL.replace("{playerUUID}","8ed20904-3262-401a-901a-1946504d2eea");
//        try {
//            HttpResponse<JsonNode> jsonResponse = Unirest.get(url)
//                    .header("accept","application/json")
//                    .asJson();
//
//            boolean isAccountLinked = (boolean) jsonResponse.getBody().getObject().get("isAccountLinked");
//
//            System.out.println(isAccountLinked);
//
//        } catch (UnirestException e) {
//            e.printStackTrace();
//        }

    }
}
