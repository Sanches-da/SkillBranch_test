package com.skill_branch.test.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.skill_branch.test.R;
import com.skill_branch.test.data.database.CharacterModel;
import com.skill_branch.test.data.database.DataManager;
import com.skill_branch.test.data.database.HouseModel;
import com.skill_branch.test.utils.ConstantManager;

public class CharacterActivity extends AppCompatActivity {
    private View mBaseView;
    private long mCharId;
    private CharacterModel mCharacter;
    private CharacterModel mCharFather;
    private CharacterModel mCharMother;
    private ImageView mImageView;
    private TextView mName;
    private TextView mWords;
    private TextView mBorn;
    private TextView mTitles;
    private TextView mAliases;
    private Button mFather;
    private Button mMother;
    private HouseModel mHouse;

    private DataManager dm;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        dm = DataManager.getInstance();

        mBaseView = findViewById(R.id.activity_character);
        mImageView = (ImageView) findViewById(R.id.char_image);
        mWords= (TextView) findViewById(R.id.char_words);

        mName= (TextView) findViewById(R.id.char_name);
        mBorn = (TextView) findViewById(R.id.char_born);
        mTitles = (TextView) findViewById(R.id.char_titles);
        mAliases = (TextView) findViewById(R.id.char_aliases);
        mFather = (Button) findViewById(R.id.char_father);
        mMother = (Button) findViewById(R.id.char_mother);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        mCharId = getIntent().getLongExtra(ConstantManager.PARCELABLE_KEY,0);
        mCharacter= dm.getCharacterById(mCharId);
        if (mCharacter == null){
            showSnackbar("Error: character not found");
            this.finish();
        }
        mHouse = dm.getHouseById(mCharacter.getHouse());
        if (mHouse==null){
            mHouse = new HouseModel(0);
        }

        mImageView.setImageDrawable(HouseModel.getLogo(mCharacter.getHouse()));
        mWords.setText(mHouse.getWords());
        mName.setText(mCharacter.getName());
        mBorn.setText(mCharacter.getBorn());
        mTitles.setText(mCharacter.getTitles());
        mAliases.setText(mCharacter.getAliases());


        long mParent = mCharacter.getFather();
        if (mParent <= 0){
            mFather.setVisibility(View.INVISIBLE);
            mCharFather = null;
        } else {
            mCharFather = dm.getCharacterById(mParent);
            if (mCharFather != null) {
                mFather.setText(mCharFather.getName());
                mFather.setVisibility(View.VISIBLE);
            }else{
                mFather.setVisibility(View.INVISIBLE);
            }

        }

        mParent = mCharacter.getMother();
        if (mParent <= 0){
            mMother.setVisibility(View.INVISIBLE);
            mCharMother = null;
        } else {
            mCharMother = dm.getCharacterById(mParent);
            if (mCharMother != null) {
                mMother.setText(mCharMother.getName());
                mMother.setVisibility(View.VISIBLE);
            }else{
                mMother.setVisibility(View.INVISIBLE);
            }
        }


        if (mCharacter.isDied()) {
            showSnackbar("Died: "+mCharacter.getDied()+"\n Last season: "+mCharacter.getLastSeason());
        }
    }

    public void clickFather(View view){
        if (mCharFather == null) return;
        Intent profileIntent = new Intent(CharacterActivity.this, CharacterActivity.class);
        profileIntent.putExtra(ConstantManager.PARCELABLE_KEY, mCharFather.getRemote_id());

        startActivity(profileIntent);
    }

    public void clickMother(View view){
        if (mCharMother == null) return;
        Intent profileIntent = new Intent(CharacterActivity.this, CharacterActivity.class);
        profileIntent.putExtra(ConstantManager.PARCELABLE_KEY, mCharMother.getRemote_id());

        startActivity(profileIntent);
    }
    private void showSnackbar(String message) {
        Snackbar.make(mBaseView, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mHouse = null;
        mCharFather = null;
        mCharMother = null;
        this.finish();
    }
}
