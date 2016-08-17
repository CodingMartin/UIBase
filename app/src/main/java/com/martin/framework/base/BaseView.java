package com.martin.framework.base;

/**
 * Desc:
 * Author:Martin
 * Date:2016/8/15
 */
public interface BaseView<T extends BasePresenter> extends BaseRevealView {

    void setPresenter(T presenter);


}
