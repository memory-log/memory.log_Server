package dgsw.memorylog.memorylog_Server.domain.vo;

import lombok.Getter;
import lombok.Setter;
import java.sql.Time;

@Getter
@Setter
public class email_auth_code {
    private String email;
    private String code;
    private Boolean is_certified;
    private Time created_at;
}
