package com.lq.comnui.viewpage;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * @author
 * @version 1.0
 * @date 2020/8/25
 *  fragment切换 
 *  1:FragmentStatePagerAdapter:会完全销毁滑动过去的item,当需要初始化的时候，会重新初始化页面
 *  2:支持fragment懒加载 
 */
public class TabFMStatePagerAdapter extends FragmentStatePagerAdapter {


    private List<Fragment> fragments;
    private ArrayList<String> titles;
    
    public TabFMStatePagerAdapter(FragmentManager fm, List<Fragment> fragments) {
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
