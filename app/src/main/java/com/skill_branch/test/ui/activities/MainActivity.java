package com.skill_branch.test.ui.activities;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.skill_branch.test.R;
import com.skill_branch.test.data.Character;
import com.skill_branch.test.data.House;
import com.skill_branch.test.data.database.DataManager;
import com.skill_branch.test.ui.adapters.CharactersAdapter;
import com.skill_branch.test.ui.adapters.HousesAdapter;
import com.skill_branch.test.utils.ConstantManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout mNavigationDrawer;
    private Toolbar mToolbar;
    private ViewPager mPager;
    private HousesAdapter mPagerAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mNavigationDrawer = (DrawerLayout) findViewById(R.id.navigation_drawer);
        mPager = (ViewPager) findViewById(R.id.pager);

        setupPageViewer();
        setupToolbar();
        setupDrawer();


    }

    private void setupPageViewer() {
        LayoutInflater inflater = LayoutInflater.from(this);

        RecyclerView page;

        for (House iHouse : DataManager.sHouses){
            page = (RecyclerView) inflater.inflate(R.layout. house_pager, null);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            page.setLayoutManager(linearLayoutManager);
            page.setAdapter(new CharactersAdapter(iHouse.getCharacters(), new CharactersAdapter.ViewHolder.CustomClickListener() {
                @Override
                public void onItemClickListener(long item_id) {

                    Intent profileIntent = new Intent(MainActivity.this, CharacterActivity.class);
                    profileIntent.putExtra(ConstantManager.PARCELABLE_KEY, item_id);

                    startActivity(profileIntent);

                }
            }));
            iHouse.setPage(page);

        }

        mPagerAdapter = new HousesAdapter(DataManager.sHouses);
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(0);


    }

    /**
     * Обработка нажатия на меню - открытие бокового меню
     *
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mNavigationDrawer != null) {
                mNavigationDrawer.openDrawer(GravityCompat.START, true);
            }
        }


        return super.onOptionsItemSelected(item);

    }


    /**
     * Настройка параметров тулбара
     */
    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        //mAppbarLayoutParams = (AppBarLayout.LayoutParams) mCollapsingToolbar.getLayoutParams();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    /**
     * Настройка бокового меню
     */
    private void setupDrawer() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                mNavigationDrawer.closeDrawer(GravityCompat.START);

                switch (item.getItemId()) {
                    case R.id.lannister_house:
                        mPager.setCurrentItem(0);
                        break;
                    case R.id.stark_house:
                        mPager.setCurrentItem(1);
                        break;
                    case R.id.targarien_house:
                        mPager.setCurrentItem(2);
                        break;
                }

                return false;
            }
        });
    }


    /**
     * Обработчик нажатия на различные эленменты
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

    /**
     * Обработчик нажатия на клавиши для перехвата нажатия на кнопку "Назад"
     */
    @Override
    public void onBackPressed() {
        if (mNavigationDrawer != null)
            if (mNavigationDrawer.isDrawerOpen(GravityCompat.START)) {
                mNavigationDrawer.closeDrawer(GravityCompat.START, true);
                return;
            }

        super.onBackPressed();
    }


}


