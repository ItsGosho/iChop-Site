package com.ichop.plugin.linkaccount.domain.models.binding;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkCreateBindingModel {

    private String playerUUID;
    private String playerName;
    private String candidateUID;

}
