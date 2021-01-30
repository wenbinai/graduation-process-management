//package com.nefu.se.graduationprocessmanagement.controller;
//
//import com.nefu.se.graduationprocessmanagement.vo.ResultVO;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//
//import javax.servlet.http.HttpServletRequest;
//
//@RestControllerAdvice
//@Slf4j
//public class ExceptionController {
//    /**
//     * 捕获参数不合法异常
//     *
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResultVO getMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        String msg = "";
//        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
//            msg = msg + fieldError.getDefaultMessage() + ";";
//        }
//
//        return ResultVO.failClientResultVO()
//                .setMessage(msg);
//    }
//
//    /**
//     * 参数类型不匹配异常
//     *
//     * @param e
//     * @param request
//     * @return
//     */
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResultVO getMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e,
//                                                           HttpServletRequest request) {
//        String msg = request.getRequestURI().replace("/api/", "/");
//        return ResultVO.failClientResultVO()
//                .setMessage("请求地址错误" + msg);
//    }
//
//}
