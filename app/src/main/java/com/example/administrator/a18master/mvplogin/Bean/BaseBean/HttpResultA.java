package com.example.administrator.a18master.mvplogin.Bean.BaseBean;

public class HttpResultA<T> {
    private int b;
    private int i;
    private String msg;
    private T a;

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

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "HttpResultA{" +
                "b=" + b +
                ", i=" + i +
                ", msg='" + msg + '\'' +
                ", a=" + a +
                '}';
    }
}
