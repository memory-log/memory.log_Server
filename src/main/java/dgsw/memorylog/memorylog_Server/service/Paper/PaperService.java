package dgsw.memorylog.memorylog_Server.service.Paper;

import dgsw.memorylog.memorylog_Server.domain.entity.Member;
import dgsw.memorylog.memorylog_Server.domain.vo.paper.PaperCreatePaperVo;

public interface PaperService {
    public void createPaper(Member member, PaperCreatePaperVo paperCreatePaperVo);
}
