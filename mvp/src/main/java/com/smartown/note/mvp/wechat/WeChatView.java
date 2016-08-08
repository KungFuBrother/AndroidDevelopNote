package com.smartown.note.mvp.wechat;

import com.smartown.library.base.BaseView;

import java.util.List;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-08-08 14:15
 * <p/>
 * 描述：
 */
public interface WeChatView extends BaseView {

    void showData(List<WeChatNews> newses);

}
