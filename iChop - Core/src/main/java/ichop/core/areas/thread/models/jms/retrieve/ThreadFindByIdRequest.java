package ichop.core.areas.thread.models.jms.retrieve;


import ichop.core.common.domain.BaseRequestModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThreadFindByIdRequest extends BaseRequestModel {

    private String id;

}
