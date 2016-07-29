package com.smartown.library.common.adapter;

/**
 * Created by Tiger on 2016-07-07.
 * <p>
 * item内容获取工具
 */
public class ValueGetter<T> {

    /**
     * 对象中需重写此方法，重写取值逻辑；
     * <p>
     * 由于这里只是简单的表达实现思想，针对不同的使用场景，可以写一些其他方法来获取对应的值，如item中有图片可以写一个getImage(T t)方法；
     *
     * @param t
     * @return
     */
    public String getValue(T t) {
        return "";
    }

}
