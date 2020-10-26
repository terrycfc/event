package event.crud.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import event.crud.model.EventCRUD;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventCRUDCreateRequest
{
    private EventCRUD.Type type;
    private String resourceId;
}
