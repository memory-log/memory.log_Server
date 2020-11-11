package dgsw.memorylog.memorylog_Server.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "paper_like")
@Getter
@Setter
public class PaperLike {
    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @ManyToOne(targetEntity=Paper.class, fetch=FetchType.LAZY)
    @JoinColumn(columnDefinition = "paper_idx")
    private Paper paper;

    @ManyToOne(targetEntity = Member.class, fetch=FetchType.LAZY)
    @JoinColumn(columnDefinition = "member_idx")
    private Member member;
}
