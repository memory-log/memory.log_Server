package dgsw.memorylog.memorylog_Server.domain.repository;

import dgsw.memorylog.memorylog_Server.domain.entity.Paper;
import dgsw.memorylog.memorylog_Server.domain.vo.paper.PaperCreatePaperVo;
import dgsw.memorylog.memorylog_Server.enums.PaperScope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperRepository extends JpaRepository<Paper, Integer> {
    public Paper findByIdx(Integer idx);
    public Paper findByIdxAndCode(Integer idx, String code);
    public List<Paper> findAllByScope(PaperScope scope);
    public List<Paper> findAllByMember_Name(String name);
    public List<Paper> findAllByTitleContaining(String title);
}
