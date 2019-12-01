package com.ichop.plugin.linkaccount.domain.models.jms;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LinkCreateJmsRequest {

    private String candidateUID;
    private String linkKey;
}
