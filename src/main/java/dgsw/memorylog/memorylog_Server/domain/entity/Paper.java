package dgsw.memorylog.memorylog_Server.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import dgsw.memorylog.memorylog_Server.enums.PaperScope;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "paper")
@Getter
@Setter
public class Paper {
    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @ManyToOne(targetEntity=Member.class, fetch=FetchType.LAZY)
    @JoinColumn(columnDefinition = "member_idx")
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private PaperScope scope;

    @Column()
    private String code;

    @Column()
    @JsonProperty("end_time")
    private LocalDateTime endTime;

    @CreatedDate
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @JsonProperty("created_at")
    private Date createdAt;

    @Column()
    @JsonProperty("updated_at")
    private Date updatedAt;
}
