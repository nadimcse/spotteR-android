package com.butterfly.spotter.android.adapter;

import java.util.ArrayList;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabWidget;

/** 
*
* @author Nadim
* @since  Dec 2, 2013
*
*/

public class TabsAdapter extends FragmentPagerAdapter implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {
	
    private final Context context;
    private final TabHost tabHost;
    private final ViewPager viewPager;
    private final ArrayList<TabInfo> tabs = new ArrayList<TabInfo>();

    static final class TabInfo
    {
        private final Class<?> clss;
        private final Bundle args;
        private String tag;	
        
        TabInfo(Class<?> _class, Bundle _args)
        {
            clss = _class;
            args = _args;
        }
    }

    static class DummyTabFactory implements TabHost.TabContentFactory
    {
        private final Context context;

        public DummyTabFactory(Context context)
        {
            this.context = context;
        }

        public View createTabContent(String tag)
        {
            View v = new View(context);
            v.setMinimumWidth(0);
            v.setMinimumHeight(0);
            return v;
        }
    }

    public TabsAdapter(FragmentActivity activity, TabHost tabHost, 
    		ViewPager pager)
    {
        super(activity.getSupportFragmentManager());
        this.context = activity;
        this.tabHost = tabHost;
        this.viewPager = pager;
        this.tabHost.setOnTabChangedListener(this);
        this.viewPager.setAdapter(this);
        this.viewPager.setOnPageChangeListener(this);
    }

    public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args)
    {
        tabSpec.setContent(new DummyTabFactory(context));

        TabInfo info = new TabInfo(clss, args);
        tabs.add(info);
        tabHost.addTab(tabSpec);
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return tabs.size();
    }

    @Override
    public Fragment getItem(int position)
    {
    	TabInfo info = tabs.get(position);
        return Fragment.instantiate(context, info.clss.getName(), info.args);
 
    }

    public void onTabChanged(String tabId)
    {
        int position = tabHost.getCurrentTab();
        viewPager.setCurrentItem(position);
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
    {
    }

    public void onPageSelected(int position)
    {
        // Unfortunately when TabHost changes the current tab, it kindly
        // also takes care of putting focus on it when not in touch mode.
        // The jerk.
        // This hack tries to prevent this from pulling focus out of our
        // ViewPager.
        TabWidget widget = tabHost.getTabWidget();
        int oldFocusability = widget.getDescendantFocusability();
        widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        tabHost.setCurrentTab(position);
        widget.setDescendantFocusability(oldFocusability);
    }

    public void onPageScrollStateChanged(int state)
    {
    }
    
  /*   public int getItemPosition(Object object) {
        return POSITION_NONE;
    }*/
    
   
}
