package ichop.webstorage.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseReplyModel {

    private boolean isSuccessful;
    private String message;

    public BaseReplyModel(String message) {
        this.message = message;
    }
}
