package mmc.localbundle;

import android.os.Bundle;

import mmc.librarybundle.BaseActivity;

public class LocalActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
    }
}
