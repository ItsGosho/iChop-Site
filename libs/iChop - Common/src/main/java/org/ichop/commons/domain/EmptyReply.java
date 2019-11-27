package org.ichop.commons.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EmptyReply extends ReplyCandidate {

    private String empty;
}
