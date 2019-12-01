package com.ichop.plugin.linkaccount.commons.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EmptyRequest extends RequestCandidate {

}
