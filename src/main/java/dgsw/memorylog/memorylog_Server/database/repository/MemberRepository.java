package dgsw.memorylog.memorylog_Server.database.repository;

import dgsw.memorylog.memorylog_Server.database.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    public Member findByEmailAndPw(String email, String pw);
}
