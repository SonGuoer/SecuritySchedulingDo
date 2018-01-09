package com.nandi.securityschedulingdo.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nandi.securityschedulingdo.Constant;
import com.nandi.securityschedulingdo.utils.AutoComplete;
import com.nandi.securityschedulingdo.utils.DESUtil;
import com.nandi.securityschedulingdo.R;
import com.nandi.securityschedulingdo.utils.SharedUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView autoTv;
    private Button loginBtn;
    private EditText password;
    private Context context;
    private String passwordDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        initView();
    }

    private void initView() {
        autoTv = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.email_sign_in_button);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNotNull()) {
                    try {
                        loginRequest();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        AutoComplete.initAutoComplete(context, "history", autoTv);
    }

    private boolean isNotNull() {
        if (TextUtils.isEmpty(autoTv.getText().toString().trim())) {
            autoTv.setError("请输入用户名！");
            return false;
        }
        if (TextUtils.isEmpty(password.getText().toString().trim())) {
            password.setError("请输入密码！");
            return false;
        }
        try {
           String str  = new StringBuilder(password.getText().toString().trim()).reverse().toString();
            passwordDes = DESUtil.encryption(str, "ycmcwin2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private void loginRequest() throws Exception {
        OkHttpUtils.post().url(getString(R.string.base_url) + "/system/login")
                .addParams("username", autoTv.getText().toString().trim())
                .addParams("password", passwordDes)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println("response = " + response);
                        try {
                            JSONObject js = new JSONObject(response);
                            JSONObject meta = new JSONObject(js.optString("meta"));
                            Boolean success = meta.optBoolean("success");
                            String message = meta.optString("message");
                            if (success) {
                                JSONObject data = new JSONObject(js.optString("data"));
                                String nickname = data.optString("nickname");
                                int isManager = data.optInt("is_manager");
                                int industryType = data.optInt("industry_type");
                                int areaId = data.optInt("areaid");
                                SharedUtils.putShare(context, Constant.LEVEL,isManager);
                                SharedUtils.putShare(context, Constant.AREA_ID,areaId);
                                Intent intent = new Intent(context, MainActivity.class);
                                startActivity(intent);
                                finish();
                                AutoComplete.saveHistory(context, "history", autoTv);
                            } else {
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
