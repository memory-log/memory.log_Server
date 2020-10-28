package dgsw.memorylog.memorylog_Server.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "post_comment")
@Getter
@Setter
public class PostComment {
    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(name = "post_idx", nullable = false)
    private Integer post_idx;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "content")
    private String comment;

    @Column(name = "image")
    private String image;

    @Column(name = "rotate")
    private Integer rotate;

    @Column(name = "updated_at")
    private Time updated_at;

    @Column(name = "created_at", nullable = false)
    private Time created_at;
}
