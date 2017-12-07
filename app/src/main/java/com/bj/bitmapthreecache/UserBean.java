package com.bj.bitmapthreecache;

/**
 * Created by 1 on 2017/11/4.
 */

public class UserBean<T> {

    private T var;

    public T getVar() {

        return var;
    }

    public void setVar(T var) {
        this.var = var;
    }

    public UserBean(T var) {
        this.var = var;
    }
}
