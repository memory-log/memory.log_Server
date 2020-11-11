package dgsw.memorylog.memorylog_Server.service.Paper;

import dgsw.memorylog.memorylog_Server.domain.vo.paper.PaperUpdateLikeVo;

public interface PaperLikeService {
    public void updateLike(PaperUpdateLikeVo paperUpdateLikeVo);
    public void addLike(PaperUpdateLikeVo paperUpdateLikeVo);
    public void deleteLike(PaperUpdateLikeVo paperUpdateLikeVo);
}
