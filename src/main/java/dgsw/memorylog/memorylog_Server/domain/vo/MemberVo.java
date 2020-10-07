package dgsw.memorylog.memorylog_Server.domain.vo;

import lombok.*;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="member")
public class MemberVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;
    private String name;
    private String email;
    private String pw;
}
