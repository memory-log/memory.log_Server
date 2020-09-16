package dgsw.memorylog.memorylog_Server.domain.vo;

import javax.persistence.Entity;
import java.sql.Time;

@Entity
public class email_auth_code {
    private String email;
    private String code;
    private Boolean is_certified;
    private Time created_at;
}
