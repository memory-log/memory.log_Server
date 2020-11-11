package dgsw.memorylog.memorylog_Server.handler;

import dgsw.memorylog.memorylog_Server.domain.vo.http.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(MultipartException.class)
    public Response handleMultipartException(MultipartException e) {
        return new Response(HttpStatus.BAD_REQUEST, "검증 오류.");
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public Response handleMissingServletRequestPartException(MissingServletRequestPartException e) {
        return new Response(HttpStatus.BAD_REQUEST, "검증 오류.");
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Response> handleHttpClientErrorException(HttpClientErrorException e) {
        Response data = new Response(e.getStatusCode(), e.getMessage());
        return new ResponseEntity<>(data, e.getStatusCode());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleHttpServerErrorException(HttpServerErrorException e) {
        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleUnsupportedOperationException(UnsupportedOperationException e) {
        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected Response handleWebExchangeBindException(WebExchangeBindException e) {
        return new Response(HttpStatus.BAD_REQUEST, "검증 오류.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new Response(HttpStatus.BAD_REQUEST, "검증 오류.");
    }
}