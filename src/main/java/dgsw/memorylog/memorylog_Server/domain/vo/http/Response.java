package dgsw.memorylog.memorylog_Server.domain.vo.http;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class Response {
    private final int status;
    private final String message;

    public Response(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }
}
