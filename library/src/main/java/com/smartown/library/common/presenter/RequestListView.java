package com.smartown.library.common.presenter;

import com.smartown.library.base.BaseView;

import java.util.List;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-08-08 14:55
 * <p/>
 * 描述：
 */
public interface RequestListView<T> extends BaseView {

    void showData(List<T> dataList);

}
