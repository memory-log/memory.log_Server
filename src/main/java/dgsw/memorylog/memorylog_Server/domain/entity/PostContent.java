package dgsw.memorylog.memorylog_Server.domain.entity;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "post_content")
@Getter
@Setter
public class PostContent {
    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(name = "post_idx", nullable = false)
    private Integer post_idx;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "comment")
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
