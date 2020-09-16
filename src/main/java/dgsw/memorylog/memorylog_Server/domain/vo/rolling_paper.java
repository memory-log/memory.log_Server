package dgsw.memorylog.memorylog_Server.domain.vo;

import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import java.sql.Time;

@Entity
public class rolling_paper {
    private int idx;
    private int user_idx;
    private String title;
    private String scope;
    private String code;
    private Time deadline;
    private Time created_at;
}
