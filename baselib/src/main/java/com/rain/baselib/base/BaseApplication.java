package com.rain.baselib.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

/**
 * Author:rain
 * Date:2017/11/13 15:26
 * Description:
 */

public class BaseApplication extends Application {

    private static BaseApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;

    }

    public static BaseApplication getInstance() {
        return myApplication;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    /**
     * 获取当前进程的名字
     *
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
