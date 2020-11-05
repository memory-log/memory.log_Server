package dgsw.memorylog.memorylog_Server.service.Paper;

import dgsw.memorylog.memorylog_Server.domain.vo.paper.PaperCreatePaperVo;

public interface PaperService {
    public Integer createPaper(Integer member_idx, PaperCreatePaperVo paperCreatePaperVo);
}
