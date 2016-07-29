package com.smartown.note.app.model;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-07-29 9:45
 * <p/>
 * 描述：
 */
public class ModelMenuItem {

    private String lable;
    private Class fragmentClass;

    public ModelMenuItem(String lable, Class fragmentClass) {
        this.lable = lable;
        this.fragmentClass = fragmentClass;
    }

    public String getLable() {
        return lable;
    }

    public Class getFragmentClass() {
        return fragmentClass;
    }

}
