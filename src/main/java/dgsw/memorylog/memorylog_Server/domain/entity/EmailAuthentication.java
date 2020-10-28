package dgsw.memorylog.memorylog_Server.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "email_authentication")
@Getter
@Setter
public class EmailAuthentication {
    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "expire_time", nullable = false)
    private Time expire_time;

    @Column(name = "is_certified", nullable = false)
    private Boolean is_certified;
}
