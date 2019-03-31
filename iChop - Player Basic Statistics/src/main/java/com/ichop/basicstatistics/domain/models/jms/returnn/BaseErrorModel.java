package com.ichop.basicstatistics.domain.models.jms.returnn;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public abstract class BaseErrorModel {

    private HashMap<String,String> errors;

}
