package com.skill_branch.test.data.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NetworkService {


    @GET("houses/362")
    Call<HousePojo> getStarkHouse();

    @GET("houses/229")
    Call<HousePojo> getLannisterHouse();

    @GET("houses/378")
    Call<HousePojo> getTargaryenHouse();


    @GET("characters/{char_id}")
    Call<CharacterPojo> getCharacter(@Path("char_id") String char_id);

}
