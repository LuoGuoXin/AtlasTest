package mmc.atlastest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.taobao.atlas.dex.util.FileUtils;
import com.taobao.atlas.update.AtlasUpdater;
import com.taobao.atlas.update.model.UpdateInfo;

import java.io.File;

import mmc.librarybundle.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //打开远程bundle
    public void remote(View view){
        Intent intent = new Intent();
        intent.setClassName(view.getContext(), "mmc.remotebundle.RemoteActivity");
        startActivity(intent);
    }

    //打开本地bundle
    public void local(View view){
        Intent intent = new Intent();
        intent.setClassName(view.getContext(), "mmc.localbundle.LocalActivity");
        startActivity(intent);
    }

    //更新补丁
    public void update(View view){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
               update();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                Toast.makeText(MainActivity.this, "更新完成，请重启", Toast.LENGTH_LONG).show();
            }
        }.execute();
    }

    private void update(){
        File updateInfo = new File(getExternalCacheDir(), "update.json");
        if (!updateInfo.exists()) {
            showToast("更新信息不存在，请先 执行 buildTpatch.sh");
            return;
        }

        String jsonStr = new String(FileUtils.readFile(updateInfo));
        UpdateInfo info = JSON.parseObject(jsonStr, UpdateInfo.class);

        File patchFile = new File(getExternalCacheDir(), "patch-" + info.updateVersion + "@" + info.baseVersion + ".tpatch");
        try {
            AtlasUpdater.update(info, patchFile);
        } catch (Throwable e) {
            e.printStackTrace();
            showToast("更新失败, " + e.getMessage());
        }
    }

}
