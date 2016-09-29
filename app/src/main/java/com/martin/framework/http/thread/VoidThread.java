package com.martin.framework.http.thread;

import android.support.annotation.MainThread;

import com.martin.framework.utils.ThreadUtil;

/**
 * Desc:
 * Author:Martin
 * Date:2016/9/29
 */

public abstract class VoidThread implements ThreadDelegate{
    private Thread mThread;
    private long mKey;

    public VoidThread() {
        mThread = new Thread(new Runnable() {
            @Override public void run() {
                operate();
                deliverEnd();
            }
        });
    }

    private void deliverEnd() {
        ThreadUtil.getMainHandler().post(new Runnable() {
            @Override public void run() {
                onOperateEnd();
            }
        });
    }

    @MainThread
    protected void onOperateEnd() {

    }

    /**
     * 执行线程返回对应的key
     *
     * @return
     */
    public long start() {
        mKey = ThreadUtil.execute(mThread);
        return mKey;
    }

    public boolean cancel() {
        return ThreadUtil.cancel(mKey);
    }

    /**
     * 线程需要执行的内容
     */
    public abstract void operate();
}
