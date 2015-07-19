package com.taixinkanghu.app.ui.data;

import com.taixinkanghu.R;

import java.util.Date;

/**
 * Created by Administrator on 2015/7/19.
 */
public class ReviewsData {

    public String iphoneNumber;
    public String reviewsDate;
    public String content;
    public String orderDate;

    public ReviewsData(int id) {
        if (id == 1) {
            this.iphoneNumber = "13********6";
            this.reviewsDate = "2015-06-24 00:42";
            this.content = "服务很好，很专业，好评！";
            this.orderDate = "2015-06-14 00:42";
        } else if (id == 2) {
            this.iphoneNumber = "15********6";
            this.reviewsDate = "2015-06-22 00:22";
            this.content = "态度很烂，很不好，做饭不好吃，再也不用他了，差评！";
            this.orderDate = "2015-06-12 00:22";
        }
    }

}