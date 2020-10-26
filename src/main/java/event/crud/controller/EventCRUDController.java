package event.crud.controller;

import static event.crud.converters.EventCRUDConverter.convert;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import event.crud.converters.EventCRUDConverter;
import event.crud.request.EventCRUDCreateRequest;
import event.crud.request.EventCRUDSearchRequest;
import event.crud.request.EventCRUDUpdateRequest;
import event.crud.response.EventCRUDResponse;
import event.crud.service.EventCRUDService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = {"/api/events"})
@ResponseBody
@RestController
@Slf4j
@RequiredArgsConstructor
public class EventCRUDController
{
    private final EventCRUDService eventService;

    @GetMapping(value = "/search", produces = APPLICATION_JSON_VALUE)
    public Page<EventCRUDResponse> searchEvents(@Validated EventCRUDSearchRequest searchRequest)
    {
        return eventService.searchEvents(searchRequest).map(EventCRUDConverter::convert);
    }

    @GetMapping(value = "/{eventId}", produces = APPLICATION_JSON_VALUE)
    public EventCRUDResponse getEvent(@PathVariable Long eventId)
    {
        return convert(eventService.getEvent(eventId));
    }

    @PatchMapping(value = "/{eventId}", produces = APPLICATION_JSON_VALUE)
    public EventCRUDResponse createEvent(@PathVariable Long eventId, @RequestBody EventCRUDUpdateRequest request)
    {
        return convert(eventService.dismissEvent(eventId, request));
    }

    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public EventCRUDResponse createEvent(@RequestBody EventCRUDCreateRequest request)
    {
        return convert(eventService.createEvent(request));
    }
}
