package com.ichop.plugin.linkaccount.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JmsReplyModel {

    private boolean isSuccessful;
    private String message;

    private Object data;


    public JmsReplyModel(boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
    }
}
