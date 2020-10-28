package dgsw.memorylog.memorylog_Server.domain.vo.member;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

/**
 * 롤링페이퍼 생성 오브젝트
 */
@Getter
public class PaperCreatePaperVo {
    @NotBlank
    private String content;

    @NotBlank
    private String location;


}
