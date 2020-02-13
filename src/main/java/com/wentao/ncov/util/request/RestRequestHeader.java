package com.wentao.ncov.util.request;


import java.beans.ConstructorProperties;
import java.io.Serializable;


/**
 * 请求参数header
 *
 * @time: 2018年04月16日
 * @author: wentao
 * @copyright: Wuxi Yazuo ,Ltd.copyright 2015-2025
 */
public class RestRequestHeader implements Serializable {
    private static final long serialVersionUID = -4363387588926602131L;
    private int pageSize;
    private int pageNum;


    public RestRequestHeader() {
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof RestRequestHeader)) {
            return false;
        } else {
            RestRequestHeader other = (RestRequestHeader) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                if (this.getPageSize() != other.getPageSize()) {
                    return false;
                } else if (this.getPageNum() != other.getPageNum()) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof RestRequestHeader;
    }

    @Override
    public int hashCode() {
        int PRIME = 31;
        int result = 1;
        result = result * 59 + this.getPageSize();
        result = result * 59 + this.getPageNum();
        return result;
    }

    @Override
    public String toString() {
        return "RestRequestHeader(pageSize=" + this.getPageSize() + ", pageNum=" + this.getPageNum() + ")";
    }

    @ConstructorProperties({"app", "pageSize", "pageNum", "sorts", "ext"})
    public RestRequestHeader(String app, int pageSize, int pageNum) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }
}
