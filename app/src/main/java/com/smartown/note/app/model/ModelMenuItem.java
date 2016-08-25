package com.smartown.note.app.model;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-07-29 9:45
 * <p/>
 * 描述：
 */
public class ModelMenuItem {

    private int type;
    private String label;
    private String value;

    public ModelMenuItem(int type, String label, String value) {
        this.type = type;
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    public int getType() {
        return type;
    }

}
