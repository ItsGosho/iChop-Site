package ichop.core.areas.thread.models.jms.retrieve;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichop.commons.domain.BaseRequestModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThreadFindByIdRequest extends BaseRequestModel {

    private String id;

}
