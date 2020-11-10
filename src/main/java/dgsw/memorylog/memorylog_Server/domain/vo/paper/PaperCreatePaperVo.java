package dgsw.memorylog.memorylog_Server.domain.vo.paper;

import com.fasterxml.jackson.annotation.JsonFormat;
import dgsw.memorylog.memorylog_Server.domain.entity.Member;
import dgsw.memorylog.memorylog_Server.enums.PaperScope;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Time;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDateTime;

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

    private Member member;

    private LocalDateTime endTime;
}
