package dgsw.memorylog.memorylog_Server.domain.vo;

import lombok.Getter;
import lombok.Setter;
import java.sql.Time;

@Getter
@Setter
public class rolling_paper {
    private int idx;
    private int user_idx;
    private String title;
    private String scope;
    private String code;
    private Time deadline;
    private Time created_at;
}
