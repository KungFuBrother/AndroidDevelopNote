package com.smartown.note.app.model;

/**
 * 作者：Tiger
 * <p>
 * 时间：2016-07-29 9:45
 * <p>
 * 描述：
 */
public class ModelMenuItem {

    private String lable;
    private String packageName;

    public ModelMenuItem(String lable, String packageName) {
        this.lable = lable;
        this.packageName = packageName;
    }

    public String getLable() {
        return lable;
    }

    public String getPackageName() {
        return packageName;
    }

}
