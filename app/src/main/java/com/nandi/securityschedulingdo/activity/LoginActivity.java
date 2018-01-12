package com.nandi.securityschedulingdo.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nandi.securityschedulingdo.Constant;
import com.nandi.securityschedulingdo.utils.AutoComplete;
import com.nandi.securityschedulingdo.utils.DESUtil;
import com.nandi.securityschedulingdo.R;
import com.nandi.securityschedulingdo.utils.InputUtil;
import com.nandi.securityschedulingdo.utils.SharedUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

public class LoginActivity extends AppCompatActivity {

    private EditText autoTv;
    private Button loginBtn;
    private EditText password;
    private Context context;
    private String passwordDes;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;

        initView();
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CALL_PHONE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
        }
    }

    private void initView() {
        progressDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("正在登录...");
        autoTv = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.email_sign_in_button);
        if (SharedUtils.containsShare(context, Constant.USERNAME)) {
            autoTv.setText((String) SharedUtils.getShare(context, Constant.USERNAME, " "));
        }
        setHintSize(autoTv, "请输入账号");
        setHintSize(password, "请输入密码");
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
    }

    /**
     * 设置EditText的hint字体大小
     *
     * @param et  控件
     * @param str 提示内容
     */
    private void setHintSize(EditText et, String str) {
        SpannableString ss = new SpannableString(str);
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(15, true);
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        et.setHint(new SpannedString(ss));
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
            String str = new StringBuilder(password.getText().toString().trim()).reverse().toString();
            passwordDes = DESUtil.encryption(str, "ycmcwin2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();
            if (InputUtil.isShouldHideInput(v, ev)) {
                InputUtil.hideSoftInput(v.getWindowToken(), context);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private void loginRequest() throws Exception {
        progressDialog.show();
        OkHttpUtils.post().url(getString(R.string.base_url) + "/system/login")
                .addParams("username", autoTv.getText().toString().trim())
                .addParams("password", passwordDes)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        progressDialog.dismiss();
                        try {
                            JSONObject js = new JSONObject(response);
                            JSONObject meta = new JSONObject(js.optString("meta"));
                            Boolean success = meta.optBoolean("success");
                            String message = meta.optString("message");
                            if (success) {
                                JSONObject data = new JSONObject(js.optString("data"));
                                int isManager = data.optInt("is_manager");
                                int areaId = data.optInt("areaid");
                                SharedUtils.putShare(context, Constant.LEVEL, isManager);
                                SharedUtils.putShare(context, Constant.AREA_ID, areaId);
                                SharedUtils.putShare(context, Constant.USERNAME, autoTv.getText().toString().trim());
                                Intent intent = new Intent(context, MainActivity.class);
                                startActivity(intent);
                                finish();
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
