package com.zhuoxin.news.entity;

import java.util.List;

/**
 * Created by nh on 2017/1/10.
 */

public class NewsJsonBean {
    String reason;
    Result result;
    public class Result{
        String stat;
        List<News> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<News> getData() {
            return data;
        }

        public void setData(List<News> data) {
            this.data = data;
        }
    }
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }


}
