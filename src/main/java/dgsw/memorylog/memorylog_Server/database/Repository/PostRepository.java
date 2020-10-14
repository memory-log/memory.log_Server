package dgsw.memorylog.memorylog_Server.database.Repository;

import dgsw.memorylog.memorylog_Server.database.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
