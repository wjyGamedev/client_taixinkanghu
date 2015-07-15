package com.taixinkanghu.app.ui.data;

import com.taixinkanghu.R;

/**
 * Created by Administrator on 2015/7/13.
 */
public class ChooseWorkerData {

    public int pic = R.mipmap.ic_launcher;
    public String name = "姓名";
    public float star = 3f;
    public int price = 200;
    public int age = 30;
    public String province = "中国"; //籍贯
    public int workYear = 5;
    public String levelName = "中级护理员";
    public int level = 3;
    public boolean isInService = false;
    public String inService = "空闲中";

    public ChooseWorkerData(int id) {
        if (id == 1){
            this.pic = R.mipmap.face_img2;
            this.name = "谢征";
            this.star = 5f;
            this.price = 666;
            this.age = 29;
            this.province = "北京";
            this.workYear = 6;
            this.levelName = "特技护理员";
            this.level = 5;
            this.isInService = false;
            this.inService = "空闲中";
        }else if (id == 2){
            this.pic = R.mipmap.face_img;
            this.name = "王瑾瑜";
            this.star = 2.5f;
            this.price = 250;
            this.age = 31;
            this.province = "吉林";
            this.workYear = 8;
            this.levelName = "特技护理员";
            this.level = 5;
            this.isInService = true;
            this.inService = "服务中";
        }

    }




}
