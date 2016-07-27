package com.bush.android23_downloadmanager;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * 使用downloadManager进行大文件下载，视频下载的效率较高，且有进度条通知，较好
 */
public class MainActivity extends AppCompatActivity {
    private DownloadManager downloadManager;
    private String fileName=null;
    private long downLoadId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDownLoadManager();
    }

    private void initDownLoadManager() {
        downloadManager= (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        fileName="";
    }

    public void clickView(View view){
        switch (view.getId()){
            case R.id.btn_down:
                DownloadManager.Request request=new DownloadManager.Request(Uri.parse(""));
                request.setTitle("file is downing")
                        .setDescription(fileName)
                        .setDestinationInExternalFilesDir(this,
                                Environment.DIRECTORY_DOWNLOADS,fileName)
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                downLoadId=downloadManager.enqueue(request);
                break;
            case R.id.btn_naviation:
                downloadManager.remove(downLoadId);
                break;
            case R.id.btn_history:
                Intent intent=new Intent();
                intent.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
                startActivity(intent);
                break;
        }
    }
}
