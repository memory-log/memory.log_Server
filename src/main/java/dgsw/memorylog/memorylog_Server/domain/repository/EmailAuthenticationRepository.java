package dgsw.memorylog.memorylog_Server.domain.repository;

import dgsw.memorylog.memorylog_Server.domain.entity.EmailAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailAuthenticationRepository extends JpaRepository<EmailAuthentication, Integer> {
    public EmailAuthentication findByEmail(String email);
    public EmailAuthentication findByCode(String code);
}
