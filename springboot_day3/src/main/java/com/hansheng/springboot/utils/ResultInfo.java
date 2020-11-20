package com.hansheng.springboot.utils;

public class ResultInfo {
    public int code;
    public String msg;
    public String data;
    public ResultInfo(){
        super();
    }

    public ResultInfo(int code, String msg, String data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    public static ResultInfo build(int code){
        return new ResultInfo(code, "成功", "");
    }

    public static ResultInfo build(int code, String msg){
        return new ResultInfo(code, msg, "");
    }
}
