package dgsw.memorylog.memorylog_Server.domain.vo.paper;

import dgsw.memorylog.memorylog_Server.enums.PaperScope;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PaperModifyPaperVo {
    private String title;

    private PaperScope scope;

    private String code;

    private String thumbnail;

    private LocalDateTime endTime;
}
