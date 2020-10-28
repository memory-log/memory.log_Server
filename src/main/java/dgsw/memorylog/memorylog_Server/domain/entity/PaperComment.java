package dgsw.memorylog.memorylog_Server.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "paper_comment")
@Getter
@Setter
public class PaperComment {
    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(name = "paper_idx", nullable = false)
    private Integer post_idx;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "content")
    private String comment;

    @Column(name = "image")
    private String image;

    @Column(name = "rotate")
    private Integer rotate;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Time updated_at;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private Time created_at;
}
