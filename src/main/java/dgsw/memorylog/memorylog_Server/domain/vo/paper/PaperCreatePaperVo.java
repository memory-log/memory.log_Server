package dgsw.memorylog.memorylog_Server.domain.vo.paper;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Time;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 롤링페이퍼 생성 오브젝트
 */
@Getter
@Setter
public class PaperCreatePaperVo {
    @NotBlank
    private String title;

    @NotNull
    private Byte scope;

    @NotBlank
    private String code;

    private Time end_time;

}
