package dgsw.memorylog.memorylog_Server.domain.vo.paperComment;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class PaperCommentCreateVo {
    @NotBlank
    private Integer paperIdx;

    private Integer locationX;

    private Integer locationY;

    private String comment;

    private String image;
}
