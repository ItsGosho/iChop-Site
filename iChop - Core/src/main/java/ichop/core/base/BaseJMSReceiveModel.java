package ichop.core.base;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class BaseJMSReceiveModel {

    private List<String> errors;

    public boolean hasErrors() {

        if(this.errors == null){
            return false;
        }

        return this.errors.size() >= 1;
    }

}
