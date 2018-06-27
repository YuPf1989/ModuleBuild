package com.rain.baselib.rx.scheduler;

import android.support.annotation.NonNull;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author:rain
 * Date:2018/5/31 17:07
 * Description:
 */
public class SchedulerUtils {
    public static @NonNull <T>ObservableTransformer ioToMain() {
        return new BaseScheduler<T>(Schedulers.io(), AndroidSchedulers.mainThread());
    }

    public static @NonNull <T>ObservableTransformer computationMainScheduler() {
        return new BaseScheduler<T>(Schedulers.computation(), AndroidSchedulers.mainThread());
    }

    public static @NonNull <T>ObservableTransformer newThreadToMain() {
        return new BaseScheduler<T>(Schedulers.newThread(), AndroidSchedulers.mainThread());
    }

    public static @NonNull <T>ObservableTransformer singleToMain() {
        return new BaseScheduler<T>(Schedulers.single(), AndroidSchedulers.mainThread());
    }

    public static @NonNull <T>ObservableTransformer trampolineToMain() {
        return new BaseScheduler<T>(Schedulers.trampoline(), AndroidSchedulers.mainThread());
    }
}
