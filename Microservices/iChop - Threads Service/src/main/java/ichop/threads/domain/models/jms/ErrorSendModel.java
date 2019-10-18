package ichop.threads.domain.models.jms;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ErrorSendModel extends BaseSendModel {

    public ErrorSendModel(String message) {
        super(false, message);
    }
}
