package com.skill_branch.test.data.network;


import android.content.AsyncTaskLoader;
import android.content.Context;

import com.skill_branch.test.data.database.CharacterModel;
import com.skill_branch.test.data.database.CharacterModelDao;
import com.skill_branch.test.data.database.DaoSession;
import com.skill_branch.test.data.database.DataManager;
import com.skill_branch.test.data.database.HouseModel;
import com.skill_branch.test.data.database.HouseModelDao;
import com.skill_branch.test.utils.ConstantManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class AsyncLoad  extends AsyncTaskLoader<List<HouseModel>>{
    private DataManager dm;
    private boolean loadFinished;


    public AsyncLoad(Context context) {
        super(context);
        dm = DataManager.getInstance();
        loadFinished = false;
        forceLoad();
    }

    @Override
    public List<HouseModel> loadInBackground() {

        List<HouseModel> houses = new ArrayList<>();
        List<CharacterModel> characters = new ArrayList<>();
        List<Long> tmpIds = new ArrayList<>();

        getHouse(ConstantManager.LANNISTER_ID, houses, tmpIds);
        getHouse(ConstantManager.STARK_ID, houses, tmpIds);
        getHouse(ConstantManager.TARGARYEN_ID, houses, tmpIds);

        for (long id: tmpIds) {
            getCharacter(id, characters);
        }

        DaoSession daoSession = dm.getDaoSession();
        HouseModelDao daoHouse = daoSession.getHouseModelDao();
        CharacterModelDao daoCharacter  = daoSession.getCharacterModelDao();

        daoHouse.insertOrReplaceInTx(houses);
        daoCharacter.insertOrReplaceInTx(characters);

        loadFinished = true;
        return houses;
    }

    void getHouse(long id, List<HouseModel> houses, List<Long> char_ids){
        Call<HousePojo> call;
        if (id==(ConstantManager.STARK_ID))
            call = dm.getStarkHouse();
        else if (id==(ConstantManager.LANNISTER_ID))
            call = dm.getLannisterHouse();
        else
            call = dm.getTargaryenHouse();

        try {
            Response<HousePojo> resp = call.execute();
            if (resp.code() == 200){
                HousePojo hp = resp.body();
                HouseModel house = new HouseModel(hp);
//                house.setId(houses.size());
                houses.add(house);
                for (String mChar : hp.getSwornMembers()) {
                    String char_id = DataManager.parseURL_getID(mChar);
                    char_ids.add(Long.parseLong(char_id));
                }

            }else{
                houses.add(new HouseModel(id));
            }

        } catch (IOException e) {
            e.printStackTrace();
            houses.add(new HouseModel(id));
        }
    }

    void getCharacter(long id, List<CharacterModel> chars){
        Call<CharacterPojo> call = dm.getCharacter(Long.toString(id));

        try {
            Response<CharacterPojo> resp = call.execute();
            if (resp.code() == 200){
                CharacterPojo cp = resp.body();
                CharacterModel tmp = new CharacterModel(cp);
//                tmp.setId(chars.size());
                chars.add(tmp);
                if (cp.getMother()!=""){
                    String char_id = DataManager.parseURL_getID(cp.getMother());
                    getCharacter(Long.parseLong(char_id), chars);
                }
                if (cp.getFather()!=""){
                    String char_id = DataManager.parseURL_getID(cp.getFather());
                    getCharacter(Long.parseLong(char_id), chars);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean isLoadFinished() {
        return loadFinished;
    }
}
