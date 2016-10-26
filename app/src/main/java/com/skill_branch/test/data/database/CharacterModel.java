package com.skill_branch.test.data.database;


import com.skill_branch.test.data.network.CharacterPojo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import java.util.List;


@Entity(active = true, nameInDb = "CHARACTERS")
public class CharacterModel {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    @Unique
    private long remote_id;
    @NotNull
    private String name;
    private  String born;
    private  String died;
    private String titles;
    private String aliases;
    private String lastSeason;
    private long father;
    private long mother;
    private long house;

    public CharacterModel(CharacterPojo pojo){
        this.remote_id = Long.parseLong(DataManager.parseURL_getID(pojo.getUrl()));
        this.name = pojo.getName();
        this.born = pojo.getBorn();
        this.died = pojo.getDied();
        this.titles = "";
        for (String tit : pojo.getTitles()){
            this.titles = this.titles + (this.titles.isEmpty()?"":", ") + tit;
        }
        this.aliases = "";
        for (String tit : pojo.getAliases()){
            this.aliases = this.aliases + (this.aliases.isEmpty()?"":"\n ") + tit;
        }

        final List<String> tvSeries = pojo.getTvSeries();
        this.lastSeason = (tvSeries.size()==0?"":tvSeries.get(tvSeries.size()-1));

        if (!pojo.getFather().isEmpty()) {
            this.father = Long.parseLong(DataManager.parseURL_getID(pojo.getFather()));
        }else{
            this.father=-1;
        }
        if (!pojo.getMother().isEmpty()) {
            this.mother = Long.parseLong(DataManager.parseURL_getID(pojo.getMother()));
        }else{
            this.mother = -1;
        }

        if (pojo.getAllegiances().size() > 0) {
            this.house = Long.parseLong(DataManager.parseURL_getID(pojo.getAllegiances().get(0)));
        }
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
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 286595538)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCharacterModelDao() : null;
    }
    /** Used for active entity operations. */
    @Generated(hash = 1375777403)
    private transient CharacterModelDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    public long getHouse() {
        return this.house;
    }
    public void setHouse(long house) {
        this.house = house;
    }
    public long getMother() {
        return this.mother;
    }
    public void setMother(long mother) {
        this.mother = mother;
    }
    public long getFather() {
        return this.father;
    }
    public void setFather(long father) {
        this.father = father;
    }
    public String getLastSeason() {
        return this.lastSeason;
    }
    public void setLastSeason(String lastSeason) {
        this.lastSeason = lastSeason;
    }
    public String getAliases() {
        return this.aliases;
    }
    public void setAliases(String aliases) {
        this.aliases = aliases;
    }
    public String getTitles() {
        return this.titles;
    }
    public void setTitles(String titles) {
        this.titles = titles;
    }
    public String getDied() {
        return this.died;
    }
    public void setDied(String died) {
        this.died = died;
    }
    public String getBorn() {
        return this.born;
    }
    public void setBorn(String born) {
        this.born = born;
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
    public boolean isDied() {
        return !died.isEmpty();
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 836423340)
    public CharacterModel(Long id, long remote_id, @NotNull String name, String born, String died,
            String titles, String aliases, String lastSeason, long father, long mother, long house) {
        this.id = id;
        this.remote_id = remote_id;
        this.name = name;
        this.born = born;
        this.died = died;
        this.titles = titles;
        this.aliases = aliases;
        this.lastSeason = lastSeason;
        this.father = father;
        this.mother = mother;
        this.house = house;
    }
    @Generated(hash = 360334686)
    public CharacterModel() {
    }

}
