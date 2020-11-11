package dgsw.memorylog.memorylog_Server.domain.vo.paper;

import dgsw.memorylog.memorylog_Server.domain.entity.Member;
import dgsw.memorylog.memorylog_Server.domain.entity.Paper;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * 롤링페이퍼 좋아요 오브젝트
 */
@Getter
@Setter
public class PaperUpdateLikeVo {
    private Paper paper;

    private Member member;
}
