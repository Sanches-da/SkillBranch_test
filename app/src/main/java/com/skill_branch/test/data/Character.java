package com.skill_branch.test.data;


import com.skill_branch.test.data.database.DataManager;
import com.skill_branch.test.data.network.CharacterPojo;

import java.util.List;

public class Character {
    private long id;
    private String mName;
    private  String mBorn;
    private  String mDied;
    private String mTitles;
    private String mAliases;
    private String mLastSeason;
    private long mFather;
    private long mMother;
    private House mHouse;

    public void setHouse(House house) {
        mHouse = house;
    }

    public House getHouse() {

        return mHouse;
    }

    public Character(CharacterPojo pojo){
        this.id = Long.parseLong(DataManager.parseURL_getID(pojo.getUrl()));
        this.mName = pojo.getName();
        this.mBorn = pojo.getBorn();
        this.mDied = pojo.getDied();
        this.mTitles = "";
        for (String tit : pojo.getTitles()){
            this.mTitles = this.mTitles + (this.mTitles.isEmpty()?"":", ") + tit;
        }
        this.mAliases = "";
        for (String tit : pojo.getAliases()){
            this.mAliases = this.mAliases + (this.mAliases.isEmpty()?"":"\n ") + tit;
        }

        final List<String> tvSeries = pojo.getTvSeries();
        this.mLastSeason = (tvSeries.size()==0?"":tvSeries.get(tvSeries.size()-1));

        this.mFather=-1;
        if (!pojo.getFather().isEmpty()) {
            this.mFather = Long.parseLong(DataManager.parseURL_getID(pojo.getFather()));
            DataManager.getInstance().findCharacter(this.mFather);
        }
        this.mMother = -1;
        if (!pojo.getMother().isEmpty()) {
            this.mMother = Long.parseLong(DataManager.parseURL_getID(pojo.getMother()));
            DataManager.getInstance().findCharacter(this.mMother);
        }
        if (pojo.getAllegiances().size() > 0) {
            this.mHouse = DataManager.getInstance().findHouse(Long.parseLong(DataManager.parseURL_getID(pojo.getAllegiances().get(0))));
        }
        if (this.mHouse!=null){
            this.mHouse.addCharacter(this);
        }
    }

    public Character(String name) {
        this.mName = name;
    }

    public Character(String name, House house) {
        this.mName = name;
        this.mHouse = house;
    }

    public String getName() {
        return mName;
    }

    public Long getId() {
        return id;
    }

    public String getBorn() {
        return mBorn;
    }

    public String getDied() {
        return mDied;
    }

    public boolean isDied() {
        return !mDied.isEmpty();
    }

    public String getTitles() {
        return mTitles;
    }

    public String getAliases() {
        return mAliases;
    }

    public String getLastSeason() {
        return mLastSeason;
    }

    public Character getFather() {

        return (mFather==-1?null:DataManager.getInstance().findCharacter(mFather));
    }

    public Character getMother() {
        return (mMother==-1?null:DataManager.getInstance().findCharacter(mMother));
    }
}
