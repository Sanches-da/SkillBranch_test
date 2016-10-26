package com.skill_branch.test.ui.adapters;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.skill_branch.test.data.database.HouseModel;

import java.util.List;


public class HousesAdapter extends PagerAdapter {

    List<HouseModel> pages = null;

    public HousesAdapter(List<HouseModel> pages){
        this.pages = pages;
    }

    @Override
    public Object instantiateItem(View collection, int position){
        View v = pages.get(position).getPage();
        ((ViewPager) collection).addView(v, 0);
        return v;
    }

    @Override
    public void destroyItem(View collection, int position, Object view){
        ((ViewPager) collection).removeView((View) view);
    }

    @Override
    public int getCount(){
        return pages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object){
        return view.equals(object);
    }

    @Override
    public void finishUpdate(View arg0){
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1){
    }

    @Override
    public Parcelable saveState(){
        return null;
    }

    @Override
    public void startUpdate(View arg0){
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return pages.get(position).getName().toUpperCase();
    }
}
