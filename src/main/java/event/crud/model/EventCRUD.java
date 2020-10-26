package event.crud.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "event_crud")
public class EventCRUD
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String resourceId;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String reason;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeDismissed;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreated;

    @PrePersist
    protected void onCreate()
    {
        if (timeCreated == null)
        {
            timeCreated = new Date();
        }
    }

    public enum Status
    {
        ACTIVE,
        INACTIVE,
        DELETED;
    }

    public enum Type
    {
        SIGN_UP,
        SIGN_IN,
        LOG_OUT;
    }
}
