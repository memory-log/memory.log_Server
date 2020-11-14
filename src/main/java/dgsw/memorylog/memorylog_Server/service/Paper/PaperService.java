package dgsw.memorylog.memorylog_Server.service.Paper;

import dgsw.memorylog.memorylog_Server.domain.entity.Member;
import dgsw.memorylog.memorylog_Server.domain.entity.Paper;
import dgsw.memorylog.memorylog_Server.domain.vo.paper.PaperCreatePaperVo;

import java.util.List;

public interface PaperService {
    public void createPaper(PaperCreatePaperVo paperCreatePaperVo);
    public Paper showOnePaper(Integer paper_idx);
    public List<Paper> showAllPaper();
    public List<Paper> getMyPaper(String name);
    public List<Paper> searchPaperByTitle(String title);
    public List<Paper> searchPaperByMemberName(String name);
}
