package ichop.core.areas.user.models.jms.role;

import ichop.core.common.domain.BaseReplyModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserHasPreviousRoleReply extends BaseReplyModel {

    private boolean hasPreviousRole;

}
