package com.ichop.basicstatistics.domain.models.jms.returnn;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public abstract class BaseJMSReturnModel {

    private List<String> errors;

    public boolean hasErrors(){
        return errors != null && errors.size() >= 1;
    }

}
