package com.example.achmadqomarudin.dzikirq;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.example.achmadqomarudin.dzikirq.MenuDzikirPagi.DzikirPagiActivity;
import com.example.achmadqomarudin.dzikirq.MenuDzikirSetelahShalat.DzikirSetelahShalatActivity;
import com.example.achmadqomarudin.dzikirq.MenuDzikirSore.DzikirSoreActivity;
import com.example.achmadqomarudin.dzikirq.MenuMoreApps.MoreAppsActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public RelativeLayout mDp, mDs, mDss, mMa;
    private Dialog MyDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setView();
        setOnClickView();
    }

    private void setView() {
        mDp = findViewById(R.id.dp);
        mDs = findViewById(R.id.ds);
        mDss = findViewById(R.id.dss);
        mMa = findViewById(R.id.ma);
    }

    private void setOnClickView() {
        mDp.setOnClickListener(this);
        mDs.setOnClickListener(this);
        mDss.setOnClickListener(this);
        mMa.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dp:
                sendActivity(getApplicationContext(), DzikirPagiActivity.class);
                break;
            case R.id.ds:
                sendActivity(getApplicationContext(), DzikirSoreActivity.class);
                break;
            case R.id.dss:
                sendActivity(getApplicationContext(), DzikirSetelahShalatActivity.class);
                break;
            case R.id.ma:
                sendActivity(getApplicationContext(), MoreAppsActivity.class);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                AboutDialog();
            break;
        }
        return true;
    }

    private void AboutDialog() {
        MyDialog = new Dialog(MainActivity.this);
        MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        MyDialog.setContentView(R.layout.custom_dialog);
        MyDialog.setTitle("My Custom Dialog");
        MyDialog.show();
    }

    private void sendActivity(Context context, Class cls) {
        Intent i = new Intent(context, cls);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
    }
}
