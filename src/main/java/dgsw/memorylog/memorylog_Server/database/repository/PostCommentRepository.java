package dgsw.memorylog.memorylog_Server.database.Repository;

import dgsw.memorylog.memorylog_Server.database.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Integer> {
}
