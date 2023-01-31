package nuricanozturk.dev.passwordmanager.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "application")
@Getter
@Setter
public class Application
{
    @Id
    @Column(name = "app_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int app_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;
    @Column(name = "app_name")
    private String app_name;
    @Column(name = "app_username")
    private String app_username;
    @Column(name = "app_password")
    private String app_password;


    public Application() {}

    public Application(User user, String app_name, String app_username, String app_password)
    {
        this.user_id = user;
        this.app_name = app_name;
        this.app_username = app_username;
        this.app_password = app_password;
    }

    public Application(String app_name, String app_username, String app_password)
    {
        this.app_name = app_name;
        this.app_username = app_username;
        this.app_password = app_password;
    }

    @Override
    public String toString() {
        return "Application{" +
                "app_id=" + app_id +
                ", user_id=" + user_id +
                ", app_name='" + app_name + '\'' +
                ", app_username='" + app_username + '\'' +
                ", app_password='" + app_password + '\'' +
                '}';
    }
}
