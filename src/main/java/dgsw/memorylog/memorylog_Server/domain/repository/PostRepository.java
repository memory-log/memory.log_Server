package dgsw.memorylog.memorylog_Server.domain.repository;

import dgsw.memorylog.memorylog_Server.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
