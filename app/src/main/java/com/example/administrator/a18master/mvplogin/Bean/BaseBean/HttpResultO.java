package com.example.administrator.a18master.mvplogin.Bean.BaseBean;

public class HttpResultO<T> {
    private int b;
    private int i;
    private String msg;
    private T o;

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getO() {
        return o;
    }

    public void setO(T o) {
        this.o = o;
    }

    @Override
    public String toString() {
        return "HttpResultO{" +
                "b=" + b +
                ", i=" + i +
                ", msg='" + msg + '\'' +
                ", o=" + o +
                '}';
    }
}
