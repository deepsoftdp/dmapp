package pl.deepsoft.dmapp.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "Groups")
public class Group {

    @Id
    @Column(name="idGroup", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idGroup;

    @Column(name = "Name", length = 45)
    private String name;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedTimestamp")
    private Date createdTimestamp;
}
