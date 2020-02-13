package com.wentao.ncov.util.response;


import com.wentao.ncov.util.StringUtil;

import java.io.Serializable;

/**
 * 返回参数形式
 *
 * @time: 2018年04月16日
 * @author: wentao
 * @copyright: Wuxi Yazuo ,Ltd.copyright 2015-2025
 */
public class RestResponse<T> implements Serializable {
    private static final long serialVersionUID = -4118386780685379201L;
    private static final int STACK_TRACE_ELEMENT_INX = 2;
    private RestResponseHeader header;
    private T body = null;

    public RestResponse() {
        this.header = new RestResponseHeader();
        this.makeStackTrace(Thread.currentThread().getStackTrace());
    }

    public RestResponse(RestResponseHeader header, T body) {
        this.header = header;
        this.body = body;
        this.makeStackTrace(Thread.currentThread().getStackTrace());
    }

    public RestResponse(RestResponseHeader header) {
        this.header = header;
        this.makeStackTrace(Thread.currentThread().getStackTrace());
    }

    public RestResponse(T body) {
        this.header = new RestResponseHeader();
        this.body = body;
        this.makeStackTrace(Thread.currentThread().getStackTrace());
    }

    public RestResponse(String code, String message) {
        this.header = new RestResponseHeader(code, message);
        this.makeStackTrace(Thread.currentThread().getStackTrace());
    }

    public RestResponse(String code, String message, String errTrace) {
        this.header = new RestResponseHeader(code, message, errTrace);
        this.makeStackTrace(Thread.currentThread().getStackTrace());
    }

    public RestResponse(String code, String message, String errType, String errTrace) {
        this.header = new RestResponseHeader(code, message, errType, errTrace);
        this.makeStackTrace(Thread.currentThread().getStackTrace());
    }

    public RestResponse(String code, String message, String subCode, String subMessage, String errType, String errTrace) {
        this.header = new RestResponseHeader(code, message, subCode, subMessage, errType, errTrace);
        this.makeStackTrace(Thread.currentThread().getStackTrace());
    }

    public RestResponse(String code, String message, T body) {
        this.header = new RestResponseHeader(code, message);
        this.body = body;
        this.makeStackTrace(Thread.currentThread().getStackTrace());
    }

    public String fetchCode() {
        return this.getHeader() != null ? this.getHeader().getCode() : null;
    }

    public String fetchMessage() {
        return this.getHeader() != null ? this.getHeader().getMessage() : null;
    }

    public String fetchSubCode() {
        return this.getHeader() != null ? this.getHeader().getSubCode() : null;
    }

    public String fetchSubMessage() {
        return this.getHeader() != null ? this.getHeader().getSubMessage() : null;
    }

    public static <T> RestResponse<T> success() {
        return new RestResponse();
    }

    public static <T> RestResponse<T> success(T body) {
        return new RestResponse(body);
    }

    public static <T> RestResponse<T> buildWithCode(String code) {
        RestResponse rr = new RestResponse(code, (String) null);
        rr.makeStackTrace(Thread.currentThread().getStackTrace());
        return rr;
    }

    public RestResponse withCode(String code) {
        this.header.setCode(code);
        return this;
    }

    public RestResponse withMessage(String message) {
        this.header.setMessage(message);
        return this;
    }

    public RestResponse withMessage(String messagePattern, Object... argArray) {
        this.header.setMessage(StringUtil.formatMessage(messagePattern, argArray));
        return this;
    }

    public RestResponse withSubCode(String subCode) {
        this.header.setSubCode(subCode);
        return this;
    }

    public RestResponse withSubMessage(String subMessage) {
        this.header.setSubMessage(subMessage);
        return this;
    }

    public RestResponse withSubMessage(String subMessagePattern, Object... argArray) {
        this.header.setSubMessage(StringUtil.formatMessage(subMessagePattern, argArray));
        return this;
    }

    public RestResponse withErrType(String errType) {
        this.header.setType(errType);
        return this;
    }

    public RestResponse withErrTrace(String errTrace) {
        this.header.setErrTrace(errTrace);
        return this;
    }

    public RestResponse withBody(T body) {
        this.body = body;
        return this;
    }

    public RestResponse withStackTraceElements(StackTraceElement[] stackTraceElements) {
        this.header.setStackTraceElements(stackTraceElements);
        return this;
    }

    private void makeStackTrace(StackTraceElement[] stackTraceElements) {
        if (stackTraceElements != null && stackTraceElements.length > 1) {
            this.header.setStackTraceElements(new StackTraceElement[]{stackTraceElements[2]});
        }

    }

    public RestResponseHeader getHeader() {
        return this.header;
    }

    public T getBody() {
        return this.body;
    }

    public void setHeader(RestResponseHeader header) {
        this.header = header;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof RestResponse)) {
            return false;
        } else {
            RestResponse<?> other = (RestResponse) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$header = this.getHeader();
                Object other$header = other.getHeader();
                if (this$header == null) {
                    if (other$header != null) {
                        return false;
                    }
                } else if (!this$header.equals(other$header)) {
                    return false;
                }

                Object this$body = this.getBody();
                Object other$body = other.getBody();
                if (this$body == null) {
                    if (other$body != null) {
                        return false;
                    }
                } else if (!this$body.equals(other$body)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof RestResponse;
    }

    @Override
    public int hashCode() {
        int PRIME = 31;
        int result = 1;
        Object $header = this.getHeader();
        result = result * 59 + ($header == null ? 43 : $header.hashCode());
        Object $body = this.getBody();
        result = result * 59 + ($body == null ? 43 : $body.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "RestResponse(header=" + this.getHeader() + ", body=" + this.getBody() + ")";
    }
}
