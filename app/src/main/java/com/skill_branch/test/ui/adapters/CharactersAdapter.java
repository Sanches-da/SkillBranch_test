package com.skill_branch.test.ui.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.skill_branch.test.R;
import com.skill_branch.test.data.Character;
import com.skill_branch.test.data.House;

import java.util.List;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.ViewHolder> {
    private List<Character> mCharacters;
    static private Context mContext;
    private ViewHolder.CustomClickListener mCustomClickListener;


    @Override
    public long getItemId(int position) {
        return mCharacters.get(position).getId();
    }

    public CharactersAdapter(List<Character> characters, ViewHolder.CustomClickListener clickListener) {
        mCharacters = characters;
        this.mCustomClickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.house_character_item, parent, false);

        mContext = parent.getContext();
        return new ViewHolder(convertView, this.mCustomClickListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Character character = mCharacters.get(position);

        holder.mFullName.setText(character.getName());
        holder.mInfo.setText(character.getTitles()+(character.getFather()==null?"":"Father: "+character.getFather().getName())+(character.getMother()==null?"":"Mother: "+character.getMother().getName()));
        House house = character.getHouse();
        if (house!=null) {
            holder.mImage.setImageDrawable(house.getIcon());
        }
        holder.mItemId = character.getId();


    }

    @Override
    public int getItemCount() {
        return mCharacters.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected long mItemId;
        protected ImageView mImage;
        protected TextView mFullName;
        protected TextView mInfo;

        private CustomClickListener mListener;



        public ViewHolder(View itemView, CustomClickListener clickListener) {
            super(itemView);

            mImage = (ImageView) itemView.findViewById(R.id.item_image);
            mFullName = (TextView) itemView.findViewById(R.id.item_name);
            mInfo = (TextView) itemView.findViewById(R.id.item_info);

            this.mListener = clickListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            if (mListener != null) {
                mListener.onItemClickListener(this.mItemId);
            }


        }

        public interface CustomClickListener {
            void onItemClickListener(long item_id);
        }
    }

}
