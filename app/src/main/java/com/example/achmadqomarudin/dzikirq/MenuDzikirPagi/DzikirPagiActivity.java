package com.example.achmadqomarudin.dzikirq.MenuDzikirPagi;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.achmadqomarudin.dzikirq.R;

public class DzikirPagiActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private TextView[] mDots;
    private SliderAdapterPagi sliderAdapterPagi;
    private Button mBtnNext, mBtnPrevious;
    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dzikir_pagi);
        setTitle("Dzikir Pagi");

        setView();

        sliderAdapterPagi = new SliderAdapterPagi(this, getLayoutInflater());
        mSlideViewPager.setAdapter(sliderAdapterPagi);

        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(slideViewPager);

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage + 1);
            }
        });

        mBtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });
    }

    private void setView() {
        mSlideViewPager = findViewById(R.id.slideViewPager);
        mDotLayout = findViewById(R.id.dotsLayout);
        mBtnNext = findViewById(R.id.nextBtn);
        mBtnPrevious = findViewById(R.id.previousBtn);
    }

    public void addDotsIndicator(int position) {

        mDots = new TextView[20];
        mDotLayout.removeAllViews();

        for (int i = 0; i<mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(android.R.color.darker_gray));

            mDotLayout.addView(mDots[i]);
        }

        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(android.R.color.white));
        }
    }

    ViewPager.OnPageChangeListener slideViewPager = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDotsIndicator(position);

            mCurrentPage = position;

            if (position == 0) {
                //page posisi pertama
                mBtnNext.setEnabled(true);
                mBtnPrevious.setEnabled(false);
                mBtnNext.setVisibility(View.VISIBLE);
                mBtnPrevious.setVisibility(View.VISIBLE);

            } else if (position == mDots.length - 1) {
                //page posisi terakhir
                mBtnNext.setEnabled(true);
                mBtnPrevious.setEnabled(true);
                mBtnNext.setVisibility(View.VISIBLE);
                mBtnPrevious.setVisibility(View.VISIBLE);

            } else {
                //page posisi tengah/belum berakhir
                mBtnNext.setEnabled(true);
                mBtnPrevious.setEnabled(true);
                mBtnNext.setVisibility(View.VISIBLE);
                mBtnPrevious.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onPause() {
        overridePendingTransition(0, 0);
        super.onPause();
    }
}
