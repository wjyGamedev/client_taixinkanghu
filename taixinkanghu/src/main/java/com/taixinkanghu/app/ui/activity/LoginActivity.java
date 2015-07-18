package com.taixinkanghu.app.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.taixinkanghu.R;

/**
 * Created by Administrator on 2015/7/17.
 */
public class LoginActivity extends Activity{

    private ImageButton btn_back;
    private TextView page_title;
    private TextView btn_protocol;
    private Button btn_verification_id;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_back = (ImageButton) findViewById(R.id.btn_back);
        page_title = (TextView) findViewById(R.id.page_title);
        btn_protocol = (TextView) findViewById(R.id.btn_protocol);
        btn_verification_id = (Button) findViewById(R.id.btn_verification_id);
        btn_login = (Button) findViewById(R.id.btn_login);

        page_title.setText(R.string.login_title);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_protocol.append(Html.fromHtml("<a href=>" + "《用户协议》" + "</a> "));
        btn_protocol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, AgreementActivity.class));
            }
        });

        btn_verification_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"短信已发出",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
