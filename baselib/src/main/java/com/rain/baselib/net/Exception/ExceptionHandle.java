package com.rain.baselib.net.Exception;

import android.util.Log;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

/**
 * Author:rain
 * Date:2018/6/5 16:32
 * Description:
 * 网络异常处理类
 */
public class ExceptionHandle {
    private static final String TAG = "ExceptionHandle";
    private static int errorCode = ErrorStatus.UNKNOWN_ERROR;
    private static String errorMsg = "请求失败，请稍后重试";

    public static String getErrorMsg() {
        return errorMsg;
    }

    private static void setErrorMsg(String errorMsg) {
        ExceptionHandle.errorMsg = errorMsg;
    }


    public static int getErrorCode() {
        return errorCode;
    }

    private static void setErrorCode(int errorCode) {
        ExceptionHandle.errorCode = errorCode;
    }

    public static String handleException(Throwable e) {
        e.printStackTrace();
        if (e instanceof SocketTimeoutException) {
            setErrorMsg("网络连接异常");
            setErrorCode(ErrorStatus.NETWORK_ERROR);
        } else if (e instanceof ConnectException) {
            setErrorMsg("网络连接异常");
            setErrorCode(ErrorStatus.NETWORK_ERROR);
        } else if (!(e instanceof JsonParseException) && !(e instanceof JSONException) && !(e instanceof ParseException)) {
            if (e instanceof ApiException) {
                setErrorMsg(String.valueOf(e.getMessage()));
                setErrorCode(ErrorStatus.SERVER_ERROR);
            } else if (e instanceof UnknownHostException) {
                setErrorMsg("网络连接异常");
                setErrorCode(ErrorStatus.NETWORK_ERROR);
            } else if (e instanceof IllegalArgumentException) {
                setErrorMsg("参数错误");
                setErrorCode(ErrorStatus.SERVER_ERROR);
            } else {
                try {
                    Log.e(TAG, "错误: " + e.getMessage());
                } catch (Exception var3) {
                    Log.e(TAG, "未知错误Debug调试 ");
                }
                setErrorMsg("未知错误，可能抛锚了吧~");
                setErrorCode(ErrorStatus.UNKNOWN_ERROR);
            }
        } else {
            setErrorMsg("数据解析异常");
            setErrorCode(ErrorStatus.SERVER_ERROR);
        }
        return getErrorMsg();
    }
}

