package dgsw.memorylog.memorylog_Server.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;

@Entity
@Table(name = "paper")
@Getter
@Setter
public class Paper {
    @Id
    @Column(name = "idx")
    private Integer idx;

    @Column(name = "member_idx", nullable = false)
    private Integer member_idx;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "scope", nullable = false)
    private Byte scope;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "end_time")
    private Time end_time;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private Time created_at;
}
