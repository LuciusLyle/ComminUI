package com.lq.comnui.viewpage;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


/**
 *  fragment切换 
 *  1:FragmentPagerAdapter:会保留页面的状态，并不会完全销毁掉
 *  2:支持fragment懒加载 
 */
public class TabFMPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private ArrayList<String> titles;
    
    public TabFMPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragments = fragments;
    }
    
    public void setTabName( ArrayList<String> titles){
        this.titles = titles;
    }


    
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null && titles.size() > position) {
            return titles.get(position);
        } else {
            return "";
        }
    }

}
