package dgsw.memorylog.memorylog_Server.domain.repository;

import dgsw.memorylog.memorylog_Server.domain.entity.Paper;
import dgsw.memorylog.memorylog_Server.domain.entity.PaperComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperCommentRepository extends JpaRepository<PaperComment, Integer> {
    public List<PaperComment> findAllByPaperIdx(Integer paperIdx);
    public PaperComment findByIdx(Integer idx);
}
