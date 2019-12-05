package com.ichop.plugin.linkaccount.domain.models.jms;

import com.ichop.plugin.linkaccount.commons.domain.RequestCandidate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IsKeyValidJmsRequest extends RequestCandidate {

    private String linkKey;

}
