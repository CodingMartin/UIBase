package com.martin.framework.http.thread;

import android.support.annotation.AnyThread;

import com.martin.framework.utils.ThreadUtil;

/**
 * Desc:
 * Author:Martin
 * Date:2016/9/29
 */

public abstract class VoidThread implements ThreadDelegate {
    private Thread mThread;
    private long mKey;

    public VoidThread() {
        mThread = new Thread(new Runnable() {
            @Override public void run() {
                doInBackground();
                deliverEnd();
            }
        });
    }

    private void deliverEnd() {
        ThreadUtil.getMainHandler().post(new Runnable() {
            @Override public void run() {
                onPostExecute();
            }
        });
    }

    /***
     * 这个方法执行在UI线程
     */
    @AnyThread
    protected void onPostExecute() {}

    /**
     * 执行线程返回对应的key
     *
     * @return
     */
    public long start() {
        mKey = ThreadUtil.execute(mThread);
        return mKey;
    }

    @Override public boolean cancel(long key) {
        return ThreadUtil.cancel(mKey);
    }

    /**
     * 线程需要执行的内容
     */
    public abstract void doInBackground();
}
