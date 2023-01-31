package nuricanozturk.dev.passwordmanager.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "log")
@Getter
@Setter
public class Log
{
    @Id
    @Column(name = "log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int log_id;
    @Column(name = "date")
    @Getter
    @Setter
    private LocalDateTime date;
    @Column(name = "is_success")
    private boolean is_success;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private User user;

    public Log() {}

    public Log(LocalDateTime date, boolean is_success, User user) {
        this.date = date;
        this.is_success = is_success;
        this.user = user;

    }

    public void setSuccess(boolean success)
    {
        is_success = success;
    }

    public boolean getSuccess()
    {
        return is_success;
    }
}
