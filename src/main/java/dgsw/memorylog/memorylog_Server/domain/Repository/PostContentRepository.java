package dgsw.memorylog.memorylog_Server.domain.Repository;

import dgsw.memorylog.memorylog_Server.domain.entity.PostContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostContentRepository extends JpaRepository<PostContent, Integer> {
}
