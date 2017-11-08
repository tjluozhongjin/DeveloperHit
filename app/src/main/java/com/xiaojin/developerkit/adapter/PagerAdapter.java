package com.xiaojin.developerkit.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xiaojin.developerkit.ui.fragment.GankFragment;
import com.xiaojin.developerkit.ui.fragment.GitHubFragment;
import com.xiaojin.developerkit.ui.fragment.V2exFragment;
import com.xiaojin.developerkit.ui.fragment.ZhiHuFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/26.
 */
public class PagerAdapter extends FragmentPagerAdapter {
    private final String[] title={"GitHub","V2EX","Gank","ZhiHu"};
    private List<Fragment>fragments=new ArrayList<Fragment>();
    public PagerAdapter(FragmentManager fm) {
        super(fm);
//        fragments.add(new TodayFragment());
//        fragments.add(new InterestFragment());
//        fragments.add(new SafetyFragment());
//        fragments.add(new SportFragment());
        fragments.add(new GitHubFragment());
        fragments.add(new V2exFragment());
        fragments.add(new GankFragment());
        fragments.add(new ZhiHuFragment());
//        fragments.add(new TestFragment());
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return 4;
    }
}
