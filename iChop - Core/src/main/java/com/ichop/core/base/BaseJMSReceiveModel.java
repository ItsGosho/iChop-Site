package com.ichop.core.base;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class BaseJMSReceiveModel {

    private List<String> errors;

    public boolean hasErrors(){
        return errors != null && errors.size() >= 1;
    }

}
