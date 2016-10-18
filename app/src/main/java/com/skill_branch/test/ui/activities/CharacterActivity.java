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
import com.skill_branch.test.data.Character;
import com.skill_branch.test.data.House;
import com.skill_branch.test.data.database.DataManager;
import com.skill_branch.test.utils.ConstantManager;
import com.skill_branch.test.utils.GameOfThronesApplication;

public class CharacterActivity extends AppCompatActivity {
    private View mBaseView;
    private long mCharId;
    private Character mCharacter;
    private ImageView mImageView;
    private TextView mName;
    private TextView mWords;
    private TextView mBorn;
    private TextView mTitles;
    private TextView mAliases;
    private Button mFather;
    private Button mMother;
    private House mHouse;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        mCharId = getIntent().getLongExtra(ConstantManager.PARCELABLE_KEY,0);
        mCharacter= DataManager.getInstance().findCharacter(mCharId);
        mHouse = mCharacter.getHouse();

        mBaseView = findViewById(R.id.activity_character);
        mImageView = (ImageView) findViewById(R.id.char_image);
        mWords= (TextView) findViewById(R.id.char_words);
        if (mHouse==null||mHouse.getLogo()==null) {
            mImageView.setImageDrawable(GameOfThronesApplication.getContext().getDrawable(R.drawable.splash));
        }else{
            mImageView.setImageDrawable(mHouse.getLogo());
            mWords.setText(mHouse.getWords());
        }

        mName= (TextView) findViewById(R.id.char_name);
        mName.setText(mCharacter.getName());
        mBorn = (TextView) findViewById(R.id.char_born);
        mBorn.setText(mCharacter.getBorn());
        mTitles = (TextView) findViewById(R.id.char_titles);
        mTitles.setText(mCharacter.getTitles());
        mAliases = (TextView) findViewById(R.id.char_aliases);
        mAliases.setText(mCharacter.getAliases());
        mFather = (Button) findViewById(R.id.char_father);
        Character mParent = mCharacter.getFather();
        if (mParent == null){
            mFather.setVisibility(View.INVISIBLE);
        } else {
            mFather.setVisibility(View.VISIBLE);
            mFather.setText(mParent.getName());
        }

        mMother = (Button) findViewById(R.id.char_mother);
        mParent = mCharacter.getFather();
        if (mParent == null){
            mMother.setVisibility(View.INVISIBLE);
        } else {
            mMother.setVisibility(View.VISIBLE);
            mMother.setText(mParent.getName());
        }


        if (mCharacter.isDied()) {
            showSnackbar("Died: "+mCharacter.getDied()+"\n Last season: "+mCharacter.getLastSeason());
        }
    }

    public void clickFather(View view){
        Character mParent = mCharacter.getFather();
        if (mParent == null) return;
        Intent profileIntent = new Intent(CharacterActivity.this, CharacterActivity.class);
        profileIntent.putExtra(ConstantManager.PARCELABLE_KEY, mParent.getId());

        startActivity(profileIntent);
    }

    public void clickMother(View view){
        Character mParent = mCharacter.getMother();
        if (mParent == null) return;
        Intent profileIntent = new Intent(CharacterActivity.this, CharacterActivity.class);
        profileIntent.putExtra(ConstantManager.PARCELABLE_KEY, mParent.getId());

        startActivity(profileIntent);
    }
    private void showSnackbar(String message) {
        Snackbar.make(mBaseView, message, Snackbar.LENGTH_LONG).show();
    }
}
