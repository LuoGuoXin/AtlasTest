package mmc.librarybundle;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by jingtian on 2017/6/1.
 */

public class BaseActivity extends Activity {

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}
