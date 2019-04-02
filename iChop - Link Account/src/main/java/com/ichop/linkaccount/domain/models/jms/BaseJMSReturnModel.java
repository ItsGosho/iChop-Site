package com.ichop.linkaccount.domain.models.jms;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class BaseJMSReturnModel {

    private List<String> errors;

    public boolean hasErrors(){
        return errors != null && errors.size() >= 1;
    }

}
