package dgsw.memorylog.memorylog_Server.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "paper_comment")
@Getter
@Setter
public class PaperComment {
    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "member_idx")
    private Member member;

    @ManyToOne(targetEntity = Paper.class, fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "paper_idx")
    private Paper paper;

    @Column(nullable = false)
    @JsonProperty("location_x")
    private Integer locationX;

    @Column(nullable = false)
    @JsonProperty("location_y")
    private Integer locationY;

    @Column()
    private String fontFamily;

    @Column()
    private String comment;

    @Column()
    private String image;

    @CreatedDate
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @JsonProperty("created_at")
    private Date createdAt;

    @Column()
    @JsonProperty("updated_at")
    private Date updatedAt;
}
