package event.crud.response;

import event.crud.model.EventCRUD;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class EventCRUDResponse
{
    private Long id;
    private String resourceId;
    private EventCRUD.Type type;
    private EventCRUD.Status status;
    private String reason;
    private Date timeCreated;
    private Date timeDismissed;
}
