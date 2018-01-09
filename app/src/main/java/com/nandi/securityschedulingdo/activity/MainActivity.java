package com.nandi.securityschedulingdo.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.nandi.securityschedulingdo.Constant;
import com.nandi.securityschedulingdo.R;
import com.nandi.securityschedulingdo.adapter.AreaAdapter;
import com.nandi.securityschedulingdo.bean.AreaName;
import com.nandi.securityschedulingdo.utils.JsonFormat;
import com.nandi.securityschedulingdo.utils.SharedUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    private MapView mMapView;
    private BitmapDescriptor mCurrentMarker;
    private BaiduMap mBaidumap;
    private Spinner areaName;
    private Spinner typePoint;
    private List<AreaName> areaNames;
    private int typeId;
    private int areaId;
    private ImageView search;
    private int level;
    private TextView cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        areaRequest();
        level = (int) SharedUtils.getShare(this, Constant.LEVEL, 1);
        areaId = (int) SharedUtils.getShare(this, Constant.AREA_ID, 0);
        initMapView();
        initOverLay();
//        mBaidumap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                System.out.println("marker = " + marker.getZIndex());
//                Bundle extraInfo = marker.getExtraInfo();
//                extraInfo.getInt("KEY");
//                System.out.println("extraInfo = " + extraInfo.getInt("KEY"));
//                return false;
//            }
//        });
    }

    private void areaRequest() {
        OkHttpUtils.get().url(getString(R.string.base_url) + "/area/selectAreaByParentId/1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println("response = " + response);
                        JSONObject js = null;
                        try {
                            js = new JSONObject(response);
                            JSONObject meta = new JSONObject(js.optString("meta"));
                            Boolean success = meta.optBoolean("success");
                            String message = meta.optString("message");
                            if (success) {
                                String data = js.optString("data");
                                areaNames = new ArrayList<>();
                                areaNames.add(new AreaName(0, 0, 0, 0, 0, "全部", 0, 0));
                                areaNames.addAll(JsonFormat.stringToList(data, AreaName.class));
                                AreaAdapter areaAdapter = new AreaAdapter(areaNames, MainActivity.this);
                                areaName.setAdapter(areaAdapter);
                                if (level > 1) {
                                    for (int i = 0; i < areaNames.size(); i++) {
                                        if (areaId == areaNames.get(i).getId()) {
                                          areaName.setSelection(i);
                                          areaName.setEnabled(false);
                                        }
                                    }
                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
    }

    private void initOverLay() {
        // TODO: 2018/1/8 打点
    }

    /**
     * 设置地图
     */
    private void initMapView() {
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);

        initListener();
        mBaidumap = mMapView.getMap();
        mBaidumap.setMyLocationEnabled(true);
        // 构造定位数据
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(10)
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(0)
                .latitude(29.56667)
                .longitude(106.45000)
                .build();

        // 设置定位数据
        mBaidumap.setMyLocationData(locData);

        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.mipmap.ic_launcher);
        MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.COMPASS, true, mCurrentMarker);
        mBaidumap.setMyLocationConfiguration(config);
        // 当不需要定位图层时关闭定位图层
        mBaidumap.setMyLocationEnabled(false);
    }

    private void initListener() {
        search = findViewById(R.id.search_point);
        cityName = findViewById(R.id.city_name);
        areaName = findViewById(R.id.area_name);
        typePoint = findViewById(R.id.type_point);
        areaName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    areaId = 1;
                } else {
                    areaId = areaNames.get(position).getId();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        typePoint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeId = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}
