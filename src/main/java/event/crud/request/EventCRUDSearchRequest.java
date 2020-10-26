package event.crud.request;

import event.crud.model.EventCRUD;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

@Getter
@Setter
@Slf4j
public class EventCRUDSearchRequest
{
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    @Max(value = 1000, message = "Page size can not exceed 1000")
    @Min(value = 1, message = "Page size can not be less than 1")
    private Integer pageSize = 20;
    @Min(value = 0, message = "Page number can not be negative")
    private Integer pageNumber = 0;
    private Set<EventCRUD.Status> statuses;
    private Set<EventCRUD.Type> types;
    private String sortBy;
    private Sort.Direction sortDirection;

    public String getSortBy()
    {
        return sortBy == null ? "id" : sortBy;
    }

    public Sort.Direction getSortDirection()
    {
        return sortDirection == null ? Sort.Direction.DESC : sortDirection;
    }

    public PageRequest getPageable()
    {
        return PageRequest.of(getPageNumber(), getPageSize(), Sort.by(new Sort.Order(getSortDirection(), getSortBy(), Sort.NullHandling.NULLS_LAST)));
    }

    public Set<EventCRUD.Status> getStatuses()
    {
        return statuses != null ? statuses : Set.of(EventCRUD.Status.values());
    }

    public Set<EventCRUD.Type> getTypes()
    {
        return types != null ? types : Set.of(EventCRUD.Type.values());
    }

}
