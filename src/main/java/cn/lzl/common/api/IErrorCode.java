package cn.lzl.common.api;

/**
 * 封装API的错误码
 * Created by ljy on 2020/11/06.
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
