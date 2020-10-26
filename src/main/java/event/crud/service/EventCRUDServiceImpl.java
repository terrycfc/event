package event.crud.service;

import event.crud.dal.EventCRUDRepository;
import event.crud.exception.EventException;
import event.crud.model.EventCRUD;
import event.crud.request.EventCRUDCreateRequest;
import event.crud.request.EventCRUDSearchRequest;
import event.crud.request.EventCRUDUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventCRUDServiceImpl implements EventCRUDService
{
    private final EventCRUDRepository eventRepo;

    @Override
    public Page<EventCRUD> searchEvents(EventCRUDSearchRequest searchRequest)
    {
        if (searchRequest == null)
        {
            throw new EventException("Missing valid search request parameters");
        }
        return eventRepo.findByTypeInAndStatusIn(searchRequest.getPageable(), searchRequest.getTypes(), searchRequest.getStatuses());
    }

    @Override
    public EventCRUD getEvent(Long eventId)
    {
        if (eventId == null)
        {
            throw new EventException("Missing event id");
        }

        EventCRUD event = eventRepo.findById(eventId).orElse(null);
        if (event == null)
        {
            throw new EventException(String.format("Event not found with id %s", eventId));
        }
        return event;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EventCRUD dismissEvent(Long eventCRUDId, EventCRUDUpdateRequest request)
    {
        EventCRUD event = eventRepo.findById(eventCRUDId).orElse(null);
        if (event == null)
        {
            throw new EventException(String.format("Event not found with id %s", eventCRUDId));
        }
        if (request == null || request.getStatus() != EventCRUD.Status.INACTIVE || Objects.equals(event.getStatus(), EventCRUD.Status.INACTIVE))
        {
            throw new EventException(String.format("Event %s can only be dismissed", eventCRUDId));
        }

        event.setStatus(request.getStatus());
        event.setReason(request.getReason());
        event.setTimeDismissed(new Date());
        return eventRepo.save(event);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public EventCRUD createEvent(EventCRUDCreateRequest request)
    {
        if (request == null || request.getType() == null)
        {
            throw new EventException("Event type is missing");
        }
        if (StringUtils.isBlank(request.getResourceId()))
        {
            throw new EventException("Event resource id is missing");
        }

        Optional<EventCRUD> eventCRUDOptional = eventRepo.findByResourceIdAndTypeAndStatus(request.getResourceId(), request.getType(), EventCRUD.Status.ACTIVE);
        if (eventCRUDOptional.isPresent())
        {
            throw new EventException("Event with resourceId and type already exists");
        }
        
        EventCRUD eventCRUD = new EventCRUD();
        eventCRUD.setStatus(EventCRUD.Status.ACTIVE);
        eventCRUD.setResourceId(request.getResourceId());
        eventCRUD.setType(request.getType());
        return eventRepo.save(eventCRUD);
    }
}
