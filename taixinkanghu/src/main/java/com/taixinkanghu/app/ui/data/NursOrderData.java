package com.taixinkanghu.app.ui.data;

import com.taixinkanghu.R;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2015/7/17.
 */
public class NursOrderData {

    public ChooseWorkerData worker;
    public int order_state;
    public long order_id;
    public Date[] date;
    public String days;
//    public int hospital_id;





    public NursOrderData(int id) {
        if (id == 1) {
            this.worker = new ChooseWorkerData(1);
            this.order_state = 0;
            this.order_id = 2015061727161046l;
            this.date = new Date[]{new Date(115,6,15),new Date(115,6,16)};
            this.days =getDays(date[0],date[1]);
        } else if (id == 2) {
            this.worker = new ChooseWorkerData(2);
            this.order_state = 1;
            this.order_id = 2015061727161047l;
            this.date = new Date[]{new Date(115,6,18),new Date(115,6,20)};
            this.days =getDays(date[0],date[1]);
        }
    }


    private String getDays(Date startDate,Date endDate){
        double m_days;
        GregorianCalendar cal1 = new GregorianCalendar();
        GregorianCalendar cal2 = new GregorianCalendar();
        cal1.setTime(startDate);
        cal2.setTime(endDate);
        m_days = (cal2.getTimeInMillis()-cal1.getTimeInMillis())/(1000*3600*24);

        return "服务时间共计"+ (int)m_days +"天";

    }


}
