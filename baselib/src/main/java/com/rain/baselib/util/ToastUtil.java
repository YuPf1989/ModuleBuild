package com.rain.baselib.util;

import android.widget.Toast;


import com.rain.baselib.base.BaseApplication;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Author:rain
 * Date:2018/5/14 16:35
 * Description:
 * 这是一个可以实时显示的toast
 */
public class ToastUtil {
    private static Toast toast;
    public static void showToast(CharSequence charSequence) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(BaseApplication.getInstance(), charSequence, LENGTH_SHORT);
        toast.show();
    }
}
