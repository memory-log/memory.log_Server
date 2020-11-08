package dgsw.memorylog.memorylog_Server.domain.vo.paper;

import dgsw.memorylog.memorylog_Server.enums.PaperScope;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Time;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * 롤링페이퍼 생성 오브젝트
 */
@Getter
@Setter
public class PaperCreatePaperVo {
    @NotBlank
    private String title;

    @NotNull
    private PaperScope scope;

    @NotBlank
    private String code;

    private Date endTime;
}
