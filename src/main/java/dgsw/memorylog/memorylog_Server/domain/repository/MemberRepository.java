package dgsw.memorylog.memorylog_Server.domain.repository;

import dgsw.memorylog.memorylog_Server.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    public Member findByEmailAndPw(String email, String pw);
    public Member findByEmail(String email);
    Member findByEmailAndPw(String email, String pw);
}
