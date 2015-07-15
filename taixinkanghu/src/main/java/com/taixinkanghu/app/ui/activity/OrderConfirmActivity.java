package com.taixinkanghu.app.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.taixinkanghu.R;
import com.taixinkanghu.app.ui.main_page.MainActivity;

/**
 * Created by Administrator on 2015/7/11.
 */
public class OrderConfirmActivity extends Activity implements View.OnClickListener {

    private Button btn_pay;
    private ImageButton btn_back;
    private TextView page_title;
    private Activity myActivity = this;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);

        btn_pay = (Button) findViewById(R.id.btn_pay);
        btn_back = (ImageButton) findViewById(R.id.btn_back);
        page_title = (TextView) findViewById(R.id.page_title);

        page_title.setText("确认订单");

        btn_pay.setOnClickListener(this);
        btn_back.setOnClickListener(this);

        intent = new Intent(this, MainActivity.class);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pay:
                new AlertDialog.Builder(this).setTitle("支付提示").setMessage("确认支付？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "支付成功", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
