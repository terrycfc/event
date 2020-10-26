package event.crud.service;

import event.crud.model.EventCRUD;
import event.crud.request.EventCRUDCreateRequest;
import event.crud.request.EventCRUDSearchRequest;
import event.crud.request.EventCRUDUpdateRequest;
import org.springframework.data.domain.Page;

public interface EventCRUDService
{
    Page<EventCRUD> searchEvents(EventCRUDSearchRequest searchRequest);

    EventCRUD getEvent(Long eventCRUDId);

    EventCRUD dismissEvent(Long eventCRUDId, EventCRUDUpdateRequest request);

    EventCRUD createEvent(EventCRUDCreateRequest request);
}
