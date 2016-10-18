package com.skill_branch.test.data;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.skill_branch.test.R;
import com.skill_branch.test.data.database.DataManager;
import com.skill_branch.test.data.network.HousePojo;
import com.skill_branch.test.utils.ConstantManager;
import com.skill_branch.test.utils.GameOfThronesApplication;

import java.util.ArrayList;
import java.util.List;

public class House {
    private long id;
    private String name;

    private String mWords;
    private List<Character> mCharacters = new ArrayList<>();
    private View mPage;
    private Drawable mIcon;
    private Drawable mLogo;

    public static int isSparkLoaded = 0;
    public static int isLannisterLoaded = 0;
    public static int isTargaryenLoaded = 0;


    public House(HousePojo pojo) {
        this.name = pojo.getName();
        this.id = Long.parseLong(DataManager.parseURL_getID(pojo.getUrl()));
        Context cont = GameOfThronesApplication.getContext();
        if (this.id == (ConstantManager.STARK_ID)) {
            this.mIcon = cont.getDrawable(R.drawable.stark_icon);
            this.mLogo = cont.getDrawable(R.drawable.stark);
            isSparkLoaded = 1;
        }else if (this.id == (ConstantManager.LANNISTER_ID)) {
            this.mIcon = cont.getDrawable(R.drawable.lanister_icon);
            this.mLogo = cont.getDrawable(R.drawable.lannister);
            isLannisterLoaded = 1;
        }else if (this.id == (ConstantManager.TARGARYEN_ID)) {
            this.mIcon = cont.getDrawable(R.drawable.targarien_icon);
            this.mLogo = cont.getDrawable(R.drawable.targarien);
            isTargaryenLoaded = 1;
        }else{
            this.mLogo = cont.getDrawable(R.drawable.splash);
        }
        this.mWords = pojo.getWords();

        DataManager.sHouses.add(this);
        DataManager dm = DataManager.getInstance();
        for (String mChar : pojo.getSwornMembers()) {
            String char_id = DataManager.parseURL_getID(mChar);
            dm.findCharacter(Long.parseLong(char_id));
        }
    }

    public House(String name, Drawable icon, Drawable logo) {
        this.name = name;
        this.mIcon = icon;
        this.mLogo = logo;
    }

    public String getWords() {
        return mWords;
    }

    public long getId() {
        return id;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public void setIcon(Drawable icon) {
        mIcon = icon;
    }

    public Drawable getLogo() {
        return mLogo;
    }

    public void setLogo(Drawable logo) {
        mLogo = logo;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Character> getCharacters() {
        return mCharacters;
    }

    public void setCharacters(List<Character> characters) {
        mCharacters = characters;
    }

    public View getPage() {
        return mPage;
    }

    public void setPage(View page) {
        mPage = page;
    }

    public void addCharacter(Character mChar) {
        this.mCharacters.add(mChar);
    }
}
