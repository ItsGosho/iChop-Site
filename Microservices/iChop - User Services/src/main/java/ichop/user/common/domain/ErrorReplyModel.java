package ichop.user.common.domain;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ErrorReplyModel extends BaseReplyModel {

    public ErrorReplyModel(String message) {
        super(false, message);
    }
}
