package dgsw.memorylog.memorylog_Server.database.vo.http;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class Response {
    private int status;
    private String message;

    public Response(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }
}
