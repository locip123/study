package errorHandle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice //使异常处理作用于所有控制类 @ControllerAdvice 是 Spring 框架中用于全局控制器增强的注解
@Slf4j
public class GlobalExceptionHandler {
    @ResponseBody // 方法的返回值会被 Spring 的 HttpMessageConverter 转换为指定的格式（如 JSON、XML），并写入 HTTP 响应体。
    @ExceptionHandler(XueChengPlusException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionHandle exceptionHandle(XueChengPlusException e){
        log.error("系统异常",e.getErrMessage(),e);
        return new ExceptionHandle(CommonError.PARAMS_ERROR.getErrMessage());
    }

    @ResponseBody // 方法的返回值会被 Spring 的 HttpMessageConverter 转换为指定的格式（如 JSON、XML），并写入 HTTP 响应体。
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionHandle exceptionHandle(Exception e){
        log.error("系统异常",e.getMessage(),e);
        return new ExceptionHandle(CommonError.UNKOWN_ERROR.getErrMessage());
    }
}
