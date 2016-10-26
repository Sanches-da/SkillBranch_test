package com.skill_branch.test.data.database;

import android.content.Context;

import com.skill_branch.test.data.network.AsyncLoad;
import com.skill_branch.test.data.network.CharacterPojo;
import com.skill_branch.test.data.network.HousePojo;
import com.skill_branch.test.data.network.NetworkService;
import com.skill_branch.test.data.network.ServiceGenerator;
import com.skill_branch.test.utils.GameOfThronesApplication;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


public class DataManager{
    private static DataManager sInstance = null;
    private DaoSession mDaoSession;

    private Context mContext;
    private NetworkService mNetworkService;

    private List<HouseModel> mHouses;


    public DataManager() {
        this.mContext = GameOfThronesApplication.getContext();
        this.mNetworkService = ServiceGenerator.createService(NetworkService.class);
        this.mDaoSession = GameOfThronesApplication.getDaoSession();
        this.mHouses = new ArrayList<>();
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

    public DaoSession getDaoSession() {
        return mDaoSession;
    }


    //region ====================== network
    public Call<HousePojo> getStarkHouse() { return mNetworkService.getStarkHouse();}

    public Call<HousePojo> getLannisterHouse() { return mNetworkService.getLannisterHouse();}

    public Call<HousePojo> getTargaryenHouse() { return mNetworkService.getTargaryenHouse();}

    public Call<CharacterPojo> getCharacter(String char_id) {
        return mNetworkService.getCharacter(char_id);
    }

    public AsyncLoad LoadDataFromNet(){
        AsyncLoad loader = new AsyncLoad(getContext());
        loader.startLoading();
        return loader;
    }

    //endregion

    //region ======================= DB =======================\

    public static String parseURL_getID(String url){
        return url.substring(url.lastIndexOf("/")+1);
    }

    public List<HouseModel> getHouses(){
        if (mHouses.size() == 0) {
            try {
                return mDaoSession.queryBuilder(HouseModel.class)
                        .orderAsc(HouseModelDao.Properties.Remote_id)
                        .build()
                        .list();

            } catch (Exception e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }else{
            return mHouses;
        }

    }

    public HouseModel getHouseById(long id){
        try {
            List<HouseModel> list  = mDaoSession.queryBuilder(HouseModel.class)
                    .where(HouseModelDao.Properties.Remote_id.eq(id))
                    .build()
                    .list();

            if (list.size()>0){
                return list.get(0);
            }else {
                return null;
            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<CharacterModel> getCharacters(long houseId){
        try {
            return mDaoSession.queryBuilder(CharacterModel.class)
                    .where(CharacterModelDao.Properties.House.eq(houseId))
                    .orderAsc(CharacterModelDao.Properties.Name)
                    .build()
                    .list();

        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    public CharacterModel getCharacterById(long charId){
        try {
            List<CharacterModel> list = mDaoSession.queryBuilder(CharacterModel.class)
                    .where(CharacterModelDao.Properties.Remote_id.eq(charId))
                    .build()
                    .list();

            if (list.size()>0){
                return list.get(0);
            }else{
                return null;
            }


        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

//endregion ======================= DB =======================


}
