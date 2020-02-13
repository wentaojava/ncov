package com.wentao.ncov.exceptionhandler;


import com.wentao.ncov.util.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

/**
 * 未捕获异常的处理
 *
 * @author wentao
 * @time 2019年05月05日
 * @copyright Gods bless me,code never with bug.
 */
@ControllerAdvice
@Slf4j
public class RuntimeExceptionHandler {
    /**
     * 捕获业务层未定义的异常，返回通用异常错误信息
     * 此处默认返回视图，加上注解@ResponseBody就可以只返回一个自定义类型
     *
     * @param exception
     * @return com.wentao.selforder.bassupport.response.RestResponse
     * @author wentao
     * @time 2018年11月12日
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RestResponse exceptionHandler(Exception exception) {
        String exceptionFlag = UUID.randomUUID().toString().replaceAll("\\-", "");
        //记录日志
        log.info("捕获异常,exceptionFlag={},exception={}", exceptionFlag, exception);
        //返回特定内容
        RestResponse restResponse = new RestResponse();
        restResponse.getHeader().setCode(SystemErrorCode.SYSTEM_ERROR_CODE.getCode());
        restResponse.getHeader().setMessage(SystemErrorCode.SYSTEM_ERROR_CODE.getMessage());
        restResponse.getHeader().setSubCode(exceptionFlag);
        restResponse.getHeader().setSubMessage("出现未捕获的异常");
        StackTraceElement[] stackTraceElements = new StackTraceElement[1];
        stackTraceElements[0] = exception.getStackTrace()[0];
        restResponse.getHeader().setStackTraceElements(stackTraceElements);
        return restResponse;
    }

    @ExceptionHandler(value = SystemException.class)
    @ResponseBody
    public RestResponse exceptionHandler(SystemException exception) {
        String exceptionFlag = UUID.randomUUID().toString().replaceAll("\\-", "");
        //记录日志
        log.info("捕获异常,exceptionFlag={},exception={}", exceptionFlag, exception);
        //返回特定内容
        RestResponse restResponse = new RestResponse();
        restResponse.getHeader().setCode(SystemErrorCode.SYSTEM_ERROR_CODE.getCode());
        restResponse.getHeader().setMessage(SystemErrorCode.SYSTEM_ERROR_CODE.getMessage());
        restResponse.getHeader().setSubCode(exceptionFlag);
        restResponse.getHeader().setSubMessage("出现未捕获的异常");
        StackTraceElement[] stackTraceElements = new StackTraceElement[1];
        stackTraceElements[0] = exception.getStackTrace()[0];
        restResponse.getHeader().setStackTraceElements(stackTraceElements);
        return restResponse;
    }


    /**
     * 捕获@Validated参数校验的异常
     *
     * @param exception
     * @return com.wentao.selforder.bassupport.response.RestResponse
     * @author wentao
     * @time 2018年11月13日
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public RestResponse MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        //获取参数校验出错的参数名
        StringBuffer subCodeStringBuffer = new StringBuffer();
        StringBuffer subMessageStringBuffer = new StringBuffer();
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        for (int i = 0; i < errorList.size(); i++) {
            subCodeStringBuffer.append(errorList.get(i).getDefaultMessage()).append(";");
            subMessageStringBuffer.append(((FieldError) errorList.get(i)).getField()).append(";");
        }

        //返回特定内容
        RestResponse restResponse = new RestResponse();
        restResponse.getHeader().setCode(SystemErrorCode.PARAM_NOT_ALLOW.getCode());
        restResponse.getHeader().setMessage(SystemErrorCode.PARAM_NOT_ALLOW.getMessage());
        restResponse.getHeader().setSubCode(subCodeStringBuffer.toString());
        restResponse.getHeader().setSubMessage(subMessageStringBuffer.toString());
        StackTraceElement[] stackTraceElements = new StackTraceElement[1];
        stackTraceElements[0] = exception.getStackTrace()[0];
        restResponse.getHeader().setStackTraceElements(stackTraceElements);
        return restResponse;
    }

}
