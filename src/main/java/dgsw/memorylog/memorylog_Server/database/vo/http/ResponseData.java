package dgsw.memorylog.memorylog_Server.database.vo.http;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseData extends Response{
    private Object data;

    public ResponseData(HttpStatus status, String message, Object data) {
        super(status, message);
        this.data = data;
    }
}
