package mmc.librarybundle;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Created by jingtian on 2017/6/1.
 */

public class BaseActivity extends Activity {

    public void showToast(final String msg) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });
    }

}
