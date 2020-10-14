package dgsw.memorylog.memorylog_Server.database.Repository;

import dgsw.memorylog.memorylog_Server.database.entity.EmailAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailAuthenticationRepository extends JpaRepository<EmailAuthentication, Integer> {
}
