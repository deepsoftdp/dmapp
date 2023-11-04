package pl.deepsoft.dmapp.Entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;


@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name="Users")
public class User {

    @Id
    @Column(name="idUsers", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idUsers;

    @Column(name="Name", length = 45)
    private String name;

    @Column(name="Mail", length = 45)
    private String mail;

    @Column(name="Password", length = 255)
    private String password;

    @Column(name="Active", length = 255)
    private boolean active;

    @Column(name="Roles", length = 255)
    private String roles;

    @Column(name="id_groups", length = 255)
    private int id_groups;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedTimestamp")
    private Date createdTimestamp;
    public User(int idUsers, String name, String mail, String password) {
        this.idUsers = idUsers;
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.roles="ROLE_USER";
    }

}
