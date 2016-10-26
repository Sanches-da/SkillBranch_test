package com.skill_branch.test.data.database;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import com.skill_branch.test.R;
import com.skill_branch.test.data.network.HousePojo;
import com.skill_branch.test.utils.ConstantManager;
import com.skill_branch.test.utils.GameOfThronesApplication;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity(active = true, nameInDb = "HOUSES")
public class HouseModel {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    @Unique
    private long remote_id;
    @NotNull
    private String name;
    private String words;

    @ToMany(joinProperties = {@JoinProperty(name = "remote_id", referencedName = "house")})
    private List<CharacterModel> characters;

    @Transient
    private View mPage;


    public HouseModel(HousePojo pojo) {
        this.name = pojo.getName();
        this.remote_id = Long.parseLong(DataManager.parseURL_getID(pojo.getUrl()));
        this.words = pojo.getWords();
    }

    public HouseModel(long id) {
        Context cont = GameOfThronesApplication.getContext();
        this.id = id;
        if (id==(ConstantManager.STARK_ID))
            this.name = cont.getString(R.string.stark_house_name);
        else if (id==(ConstantManager.LANNISTER_ID))
            this.name = cont.getString(R.string.lannister_house_name);
        else
            this.name = cont.getString(R.string.targarien_house_name);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1728304228)
    public synchronized void resetCharacters() {
        characters = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 2122199077)
    public List<CharacterModel> getCharacters() {
        if (characters == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CharacterModelDao targetDao = daoSession.getCharacterModelDao();
            List<CharacterModel> charactersNew = targetDao._queryHouseModel_Characters(remote_id);
            synchronized (this) {
                if(characters == null) {
                    characters = charactersNew;
                }
            }
        }
        return characters;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2044046615)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHouseModelDao() : null;
    }

    /** Used for active entity operations. */
    @Generated(hash = 566542349)
    private transient HouseModelDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    public String getWords() {
        return this.words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public long getRemote_id() {
        return remote_id;
    }

    public void setRemote_id(long remote_id) {
        this.remote_id = remote_id;
    }

    public View getPage() {
        return mPage;
    }

    public void setPage(View page) {
        mPage = page;
    }

    public static Drawable getIcon(long id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Context cont = GameOfThronesApplication.getContext();
            if (id == (ConstantManager.STARK_ID)) {
                return cont.getDrawable(R.drawable.stark_icon);
            } else if (id == (ConstantManager.LANNISTER_ID)) {
                return cont.getDrawable(R.drawable.lanister_icon);
            } else if (id == (ConstantManager.TARGARYEN_ID)) {
                return cont.getDrawable(R.drawable.targarien_icon);
            } else {
                return null;
            }
        }else {
            return null;
        }
    }

    public static Drawable getLogo(long id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Context cont = GameOfThronesApplication.getContext();
            if (id == (ConstantManager.STARK_ID)) {
                return cont.getDrawable(R.drawable.stark);
            } else if (id == (ConstantManager.LANNISTER_ID)) {
                return cont.getDrawable(R.drawable.lannister);
            } else if (id == (ConstantManager.TARGARYEN_ID)) {
                return cont.getDrawable(R.drawable.targarien);
            } else {
                return cont.getDrawable(R.drawable.splash);
            }
        }else{
            return null;
        }


    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Generated(hash = 760576421)
    public HouseModel(Long id, long remote_id, @NotNull String name, String words) {
        this.id = id;
        this.remote_id = remote_id;
        this.name = name;
        this.words = words;
    }

    @Generated(hash = 1408099660)
    public HouseModel() {
    }
}
