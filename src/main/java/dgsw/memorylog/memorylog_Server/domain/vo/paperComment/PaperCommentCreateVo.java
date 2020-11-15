package dgsw.memorylog.memorylog_Server.domain.vo.paperComment;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class PaperCommentCreateVo {
    @NotNull
    private Integer paperIdx;

    @NotNull
    private Integer locationX;

    @NotNull
    private Integer locationY;

    private String comment;

    private String color;

    private String fontFamily;

    private String image;
}
