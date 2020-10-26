package event.crud.dal;

import event.crud.model.EventCRUD;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.Set;

public interface EventCRUDRepository extends PagingAndSortingRepository<EventCRUD, Long>
{
    Page<EventCRUD> findByTypeInAndStatusIn(Pageable pageable, Set<EventCRUD.Type> type, Set<EventCRUD.Status> statuses);
    Optional<EventCRUD> findByResourceIdAndTypeAndStatus(String resourceId, EventCRUD.Type type, EventCRUD.Status status);
}
