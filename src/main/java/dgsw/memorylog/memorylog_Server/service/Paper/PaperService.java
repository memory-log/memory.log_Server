package dgsw.memorylog.memorylog_Server.service.Paper;

import dgsw.memorylog.memorylog_Server.domain.entity.Member;
import dgsw.memorylog.memorylog_Server.domain.entity.Paper;
import dgsw.memorylog.memorylog_Server.domain.vo.paper.PaperCreatePaperVo;

import java.util.List;

public interface PaperService {
    public void createPaper(PaperCreatePaperVo paperCreatePaperVo);
    public List<Paper> showPaper();
    public List<Paper> searchPaperByTitle(String title);
    public List<Paper> searchPaperByMemberName(String name);
}
