package event.crud.converters;

import event.crud.model.EventCRUD;
import event.crud.response.EventCRUDResponse;

public class EventCRUDConverter
{
    public static EventCRUDResponse convert(EventCRUD event)
    {
        if (event == null)
        {
            return new EventCRUDResponse();
        }
        return EventCRUDResponse.builder()
                .id(event.getId())
                .resourceId(event.getResourceId())
                .type(event.getType())
                .status(event.getStatus())
                .reason(event.getReason())
                .timeCreated(event.getTimeDismissed())
                .timeDismissed(event.getTimeCreated())
                .build();
    }
}
