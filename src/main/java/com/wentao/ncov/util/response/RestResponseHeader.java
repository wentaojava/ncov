package com.wentao.ncov.util.response;


import java.io.Serializable;
import java.util.Arrays;


/**
 * 返回参数header
 *
 * @time: 2018年04月17日
 * @author: wentao
 * @copyright: Wuxi Yazuo ,Ltd.copyright 2015-2025
 */
public class RestResponseHeader implements Serializable {
    private static final long serialVersionUID = 1031448932631195295L;
    private static final String KEY_APP = "spring.application.name";
    private String code = "10000";
    private String message;
    private String subCode;
    private String subMessage;
    private String type;
    private String errTrace;
    private String ext;
    private StackTraceElement[] stackTraceElements;

    public RestResponseHeader() {
    }

    public RestResponseHeader(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public RestResponseHeader(String code, String message, String errTrace) {
        this.code = code;
        this.message = message;
        this.errTrace = errTrace;
    }

    public RestResponseHeader(String code, String message, String errType, String errTrace) {
        this.code = code;
        this.message = message;
        this.type = errType;
        this.errTrace = errTrace;
    }

    public RestResponseHeader(String code, String message, String subCode, String subMessage, String errType, String errTrace) {
        this.code = code;
        this.message = message;
        this.subCode = subCode;
        this.subMessage = subMessage;
        this.type = errType;
        this.errTrace = errTrace;
    }

    public String getCode() {
        return this.code;
    }


    public String getMessage() {
        return this.message;
    }

    public String getSubCode() {
        return this.subCode;
    }

    public String getSubMessage() {
        return this.subMessage;
    }

    public String getType() {
        return this.type;
    }

    public String getErrTrace() {
        return this.errTrace;
    }

    public String getExt() {
        return this.ext;
    }

    public StackTraceElement[] getStackTraceElements() {
        return this.stackTraceElements;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public void setSubMessage(String subMessage) {
        this.subMessage = subMessage;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setErrTrace(String errTrace) {
        this.errTrace = errTrace;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public void setStackTraceElements(StackTraceElement[] stackTraceElements) {
        this.stackTraceElements = stackTraceElements;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof RestResponseHeader)) {
            return false;
        } else {
            RestResponseHeader other = (RestResponseHeader) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label111:
                {
                    Object this$code = this.getCode();
                    Object other$code = other.getCode();
                    if (this$code == null) {
                        if (other$code == null) {
                            break label111;
                        }
                    } else if (this$code.equals(other$code)) {
                        break label111;
                    }

                    return false;
                }
                Object this$message = this.getMessage();
                Object other$message = other.getMessage();
                if (this$message == null) {
                    if (other$message != null) {
                        return false;
                    }
                } else if (!this$message.equals(other$message)) {
                    return false;
                }

                label90:
                {
                    Object this$subCode = this.getSubCode();
                    Object other$subCode = other.getSubCode();
                    if (this$subCode == null) {
                        if (other$subCode == null) {
                            break label90;
                        }
                    } else if (this$subCode.equals(other$subCode)) {
                        break label90;
                    }

                    return false;
                }

                label83:
                {
                    Object this$subMessage = this.getSubMessage();
                    Object other$subMessage = other.getSubMessage();
                    if (this$subMessage == null) {
                        if (other$subMessage == null) {
                            break label83;
                        }
                    } else if (this$subMessage.equals(other$subMessage)) {
                        break label83;
                    }

                    return false;
                }

                Object this$type = this.getType();
                Object other$type = other.getType();
                if (this$type == null) {
                    if (other$type != null) {
                        return false;
                    }
                } else if (!this$type.equals(other$type)) {
                    return false;
                }

                Object this$errTrace = this.getErrTrace();
                Object other$errTrace = other.getErrTrace();
                if (this$errTrace == null) {
                    if (other$errTrace != null) {
                        return false;
                    }
                } else if (!this$errTrace.equals(other$errTrace)) {
                    return false;
                }

                label62:
                {
                    Object this$ext = this.getExt();
                    Object other$ext = other.getExt();
                    if (this$ext == null) {
                        if (other$ext == null) {
                            break label62;
                        }
                    } else if (this$ext.equals(other$ext)) {
                        break label62;
                    }

                    return false;
                }

                if (!Arrays.deepEquals(this.getStackTraceElements(), other.getStackTraceElements())) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof RestResponseHeader;
    }

    @Override
    public int hashCode() {
        int PRIME = 31;
        int result = 1;
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        Object $subCode = this.getSubCode();
        result = result * 59 + ($subCode == null ? 43 : $subCode.hashCode());
        Object $subMessage = this.getSubMessage();
        result = result * 59 + ($subMessage == null ? 43 : $subMessage.hashCode());
        Object $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        Object $errTrace = this.getErrTrace();
        result = result * 59 + ($errTrace == null ? 43 : $errTrace.hashCode());
        Object $ext = this.getExt();
        result = result * 59 + ($ext == null ? 43 : $ext.hashCode());
        result = result * 59 + Arrays.deepHashCode(this.getStackTraceElements());
        return result;
    }

    @Override
    public String toString() {
        return "RestResponseHeader(code=" + this.getCode() + ", app=" + this.getMessage() + ", subCode=" + this.getSubCode() + ", subMessage=" + this.getSubMessage() + ", type=" + this.getType() + ", errTrace=" + this.getErrTrace() + ", ext=" + this.getExt() + ", stackTraceElements=" + Arrays.deepToString(this.getStackTraceElements()) + ")";
    }
}
