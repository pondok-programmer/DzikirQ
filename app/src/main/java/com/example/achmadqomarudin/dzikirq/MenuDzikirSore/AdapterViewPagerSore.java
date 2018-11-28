package com.example.achmadqomarudin.dzikirq.MenuDzikirSore;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Achmad Qomarudin on 06/02/2018.
 */

public class AdapterViewPagerSore extends FragmentPagerAdapter {
    private List<Fragment> fragmentListSore = new ArrayList<>();
    private List<String> titleListSore = new ArrayList<>();

    public AdapterViewPagerSore(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentListSore.get(position);
    }

    @Override
    public int getCount() {
        return titleListSore.size();
    }
}
