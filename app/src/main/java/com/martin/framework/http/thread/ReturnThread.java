package com.martin.framework.http.thread;

import android.support.annotation.AnyThread;
import android.support.annotation.CheckResult;

import com.martin.framework.utils.ThreadUtil;

/**
 * Desc:
 * Author:Martin
 * Date:2016/9/29
 */

public abstract class ReturnThread<T> implements ThreadDelegate {
    private final Thread mThread;
    private long mKey;

    public ReturnThread() {
        mThread = new Thread(new Runnable() {
            @Override public void run() {
                deliverEnd(operate());
            }
        });
    }

    private void deliverEnd(final T result) {
        ThreadUtil.getMainHandler().post(new Runnable() {
            @Override public void run() {
                onOperateEnd(result);
            }
        });
    }

    @Override public long start() {
        mKey = ThreadUtil.execute(mThread);
        return mKey;
    }

    @Override public boolean cancel(long key) {
        ThreadUtil.cancel(mKey);
        return false;
    }

    /**
     * 线程需要执行的内容
     */
    @CheckResult
    public abstract T operate();

    /**
     * 这个方法执行在UI线程
     *
     * @param result
     */
    @AnyThread
    public abstract void onOperateEnd(T result);
}
