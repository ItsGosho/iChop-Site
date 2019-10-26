package ichop.core.areas.rest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSuccessful {

    private String message;
    private Object data;

    public ResponseSuccessful(String message) {
        this.message = message;
    }
}
