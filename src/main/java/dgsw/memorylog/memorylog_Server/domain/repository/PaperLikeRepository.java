package dgsw.memorylog.memorylog_Server.domain.repository;

import dgsw.memorylog.memorylog_Server.domain.entity.Member;
import dgsw.memorylog.memorylog_Server.domain.entity.Paper;
import dgsw.memorylog.memorylog_Server.domain.entity.PaperLike;
import dgsw.memorylog.memorylog_Server.domain.vo.paper.PaperUpdateLikeVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PaperLikeRepository extends JpaRepository<PaperLike, Integer> {
    public Integer countByPaperIdxAndMemberIdx(Integer paper_idx, Integer member_idx);

    @Transactional
    public void deleteByPaperIdxAndMemberIdx(Integer paper_idx, Integer member_idx);
}
