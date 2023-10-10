package pl.deepsoft.dmapp.Entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

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


    public User() {
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public int getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(int idUsers) {
        this.idUsers = idUsers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUsers=" + idUsers +
                ", Name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
