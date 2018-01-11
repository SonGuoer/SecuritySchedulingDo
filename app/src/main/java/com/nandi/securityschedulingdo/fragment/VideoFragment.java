package com.nandi.securityschedulingdo.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.VideoView;

import com.nandi.securityschedulingdo.R;
import com.nandi.securityschedulingdo.bean.LocationPoint;
import com.nandi.securityschedulingdo.utils.FileUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import cn.jzvd.JZVideoPlayerStandard;
import okhttp3.Call;

/**
 * 视频播放
 * Created by qingsong on 2018/1/8.
 */

public class VideoFragment extends Fragment {

    private VideoView videoView;
    private JZVideoPlayerStandard JCVideoPlayer;
    private String fileName;
    private File file;
    private File fileDir;

    public static VideoFragment newInstance(String url) {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putString("URL", url);
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        fileName = getArguments().getString("URL");
        fileName = "5002322010050101.mp4";
        JCVideoPlayer = (JZVideoPlayerStandard) view.findViewById(R.id.videoplayer);
        fileDir = createFileDir("Video");
        file = new File(fileDir, fileName);
        long length = file.length();
        if (length >0) {
            Toast.makeText(getActivity(), "我已经有视频了，不用", Toast.LENGTH_SHORT).show();
            JCVideoPlayer.setUp(file.getAbsolutePath(), JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, fileName);
        } else {
            videoRequest();
        }
        return view;
    }

    private File createFileDir(String dir) {
        String path = Environment.getExternalStorageDirectory() + "/" + dir;
        boolean orExistsDir = FileUtil.createOrExistsDir(path);
        if (orExistsDir) {
            return new File(path);
        } else {
            return null;
        }
    }
    private void videoRequest() {

        String url = "http://183.230.182.149:19088/rend/down/video?path=d:/cmd/video/dis/5002322010050101.mp4";
        OkHttpUtils.get().url(url)
                .build()
                .execute(new FileCallBack(fileDir.getPath(), fileName) {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        System.out.println("视频下载失败" + e.getMessage());
                        Toast.makeText(getActivity(), "视频下载失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        Toast.makeText(getActivity(), "视频下载成功", Toast.LENGTH_SHORT).show();
                        JCVideoPlayer.setUp(file.getAbsolutePath(), JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, fileName);
                    }
                });
    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}