package com.skill_branch.test.data.database;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.widget.Toast;


import com.skill_branch.test.R;
import com.skill_branch.test.data.Character;
import com.skill_branch.test.data.House;
import com.skill_branch.test.data.network.CharacterPojo;
import com.skill_branch.test.data.network.HousePojo;
import com.skill_branch.test.data.network.NetworkService;
import com.skill_branch.test.data.network.ServiceGenerator;
import com.skill_branch.test.utils.AppConfig;
import com.skill_branch.test.utils.ConstantManager;
import com.skill_branch.test.utils.GameOfThronesApplication;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

import static java.lang.System.in;


public class DataManager {
    private static DataManager sInstance = null;
    public static List<House> sHouses = new ArrayList<>();
    public static House otherHouse=new House("Other", null, null);

    private Context mContext;
    private NetworkService mNetworkService;


    public DataManager() {
        this.mContext = GameOfThronesApplication.getContext();
        this.mNetworkService = ServiceGenerator.createService(NetworkService.class);
//        this.mDaoSession = GameOfThronesApplication.getDaoSession();
    }


    public static DataManager getInstance() {
        if (sInstance == null) {
            sInstance = new DataManager();
        }
        return sInstance;
    }

    public Context getContext() {
        return mContext;
    }

    //region ====================== network
    public Call<HousePojo> getStarkHouse() { return mNetworkService.getStarkHouse();}

    public Call<HousePojo> getLannisterHouse() { return mNetworkService.getLannisterHouse();}

    public Call<HousePojo> getTargaryenHouse() { return mNetworkService.getTargaryenHouse();}

    public Call<CharacterPojo> getCharacter(String char_id) {
        return mNetworkService.getCharacter(char_id);
    }

    //endregion

    public static String parseURL_getID(String url){
        return url.substring(url.lastIndexOf("/")+1);
    }

    public House findHouse(final long id){
        if (!(id==(ConstantManager.STARK_ID)||
                id==(ConstantManager.LANNISTER_ID)||
                id==(ConstantManager.TARGARYEN_ID))){
            return otherHouse;
        }
        for (House h : sHouses){
            if (h.getId()==(id))
                return h;
        }
        Call<HousePojo> call;
        if (id==(ConstantManager.STARK_ID))
            call = this.getStarkHouse();
        else if (id==(ConstantManager.LANNISTER_ID))
            call = this.getLannisterHouse();
        else
            call = this.getTargaryenHouse();


        call.enqueue(new Callback<HousePojo>() {
            @Override
            public void onResponse(Call<HousePojo> call, Response<HousePojo> response) {
                if (response.code() == 200){
                    new House(response.body());
                }
            }

            @Override
            public void onFailure(Call<HousePojo> call, Throwable t) {
                if (id==(ConstantManager.STARK_ID))
                    House.isSparkLoaded=-1;
                else if (id==(ConstantManager.LANNISTER_ID))
                    House.isLannisterLoaded=-1;
                else
                    House.isTargaryenLoaded=-1;
                Toast.makeText(GameOfThronesApplication.getContext(),"Error while getting data from house!", Toast.LENGTH_LONG).show();
            }
        });

        return null;
    }

    public Character findCharacter(long id){
        for (House h : sHouses){
            for (Character c : h.getCharacters())
                if (c.getId().equals(id))
                    return c;
        }
        for (Character c : otherHouse.getCharacters())
            if (c.getId().equals(id))
                return c;
        Call<CharacterPojo> call = this.getCharacter(Long.toString(id));

        call.enqueue(new Callback<CharacterPojo>() {
            @Override
            public void onResponse(Call<CharacterPojo> call, Response<CharacterPojo> response) {
                if (response.code() == 200) {
                    new Character(response.body());
                }
            }

            @Override
            public void onFailure(Call<CharacterPojo> call, Throwable t) {
                Toast.makeText(GameOfThronesApplication.getContext(),"Error while getting data from character!", Toast.LENGTH_LONG).show();
            }

        });
        return null;

    }



}
