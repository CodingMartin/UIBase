package com.martin.framework.utils.thread;

/**
 * Desc:
 * Author:Martin
 * Date:2016/9/29
 */

public interface ThreadDelegate {

    long start();

    boolean cancel(long key);
}
