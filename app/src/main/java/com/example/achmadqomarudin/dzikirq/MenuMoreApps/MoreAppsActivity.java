package com.example.achmadqomarudin.dzikirq.MenuMoreApps;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.achmadqomarudin.dzikirq.DetectConnection;
import com.example.achmadqomarudin.dzikirq.R;

public class MoreAppsActivity extends AppCompatActivity {

    WebView webView;
    SwipeRefreshLayout swipe;
    String url = "https://play.google.com/store/apps/collection/cluster?clp=igNAChkKEzY4MzcyOTE5OTE1Nzk1MjU1NjgQCBgDEiEKG2luLmZhZGhlbGwubGFwb3Jhbi5Qb25kb2tJVBABGAMYAQ%3D%3D:S:ANO1ljKm8Ec&gsr=CkOKA0AKGQoTNjgzNzI5MTk5MTU3OTUyNTU2OBAIGAMSIQobaW4uZmFkaGVsbC5sYXBvcmFuLlBvbmRva0lUEAEYAxgB:S:ANO1ljKD5j0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_apps);
        setTitle("More Apps");

        swipe = findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoadWeb();
            }
        });

        LoadWeb();
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void LoadWeb(){

        if (!DetectConnection.checkInternetConnection(this)) {
            new AlertDialog.Builder(this)
                    .setMessage("Tidak ada koneksi internet.")
                    .setCancelable(false)
                    .setPositiveButton("Muat Ulang", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            LoadWeb();
                        }
                    })
                    .setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .show();
        } else {

            webView = findViewById(R.id.webView);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAppCacheEnabled(true);
            webView.loadUrl(url);
            swipe.setRefreshing(true);
            webView.setWebViewClient(new WebViewClient() {

//                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//                    webView.loadUrl("file:///android_asset/error.html");
//                }

                public void onPageFinished(WebView view, String url) {
                    //Hide the SwipeReefreshLayout
                    swipe.setRefreshing(false);
                }
            });
        }
    }

    @Override
    protected void onPause() {
        overridePendingTransition(0, 0);
        super.onPause();
    }

    @Override
    public void onBackPressed(){
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }
}
