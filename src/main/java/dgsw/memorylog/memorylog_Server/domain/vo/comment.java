package dgsw.memorylog.memorylog_Server.domain.vo;

import lombok.Getter;
import lombok.Setter;
import java.sql.Time;

@Getter
@Setter
public class comment {
    private int idx;
    private int rolling_paper_idx;
    private String location;
    private String content;
    private String image;
    private int rotate;
    private Time updated_at;
    private Time created_at;
}
