package com.wentao.ncov.util.request;


import javax.validation.Valid;
import java.io.Serializable;

/**
 * 请求参数形式
 *
 * @time: 2018年04月16日
 * @author: wentao
 * @copyright: Wuxi Yazuo ,Ltd.copyright 2015-2025
 */


public class RestRequest<T> implements Serializable {
    private static final long serialVersionUID = -1296515585582912062L;
    private RestRequestHeader header = new RestRequestHeader();
    @Valid
    private T body = null;

    public RestRequest() {
    }

    public RestRequest(T body) {
        this.body = body;
    }

    public static <T> RestRequest<T> instance(T body) {
        return new RestRequest(body);
    }

    public RestRequestHeader getHeader() {
        return this.header;
    }

    public T getBody() {
        return this.body;
    }

    public void setHeader(RestRequestHeader header) {
        this.header = header;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof RestRequest)) {
            return false;
        } else {
            RestRequest<?> other = (RestRequest) o;
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
        return other instanceof RestRequest;
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
        return "RestRequest(header=" + this.getHeader() + ", body=" + this.getBody() + ")";
    }
}

