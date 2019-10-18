package ichop.common.jms.models;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ErrorReplyModel extends BaseReplyModel {

    public ErrorReplyModel(String message) {
        super(false, message);
    }
}
