package com.skill_branch.test.data.network;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CharacterPojo {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("culture")
    @Expose
    private String culture;
    @SerializedName("born")
    @Expose
    private String born;
    @SerializedName("died")
    @Expose
    private String died;
    @SerializedName("titles")
    @Expose
    private List<String> titles = new ArrayList<String>();
    @SerializedName("aliases")
    @Expose
    private List<String> aliases = new ArrayList<String>();
    @SerializedName("father")
    @Expose
    private String father;
    @SerializedName("mother")
    @Expose
    private String mother;
    @SerializedName("spouse")
    @Expose
    private String spouse;
    @SerializedName("allegiances")
    @Expose
    private List<String> allegiances = new ArrayList<String>();
    @SerializedName("books")
    @Expose
    private List<String> books = new ArrayList<String>();
    @SerializedName("povBooks")
    @Expose
    private List<Object> povBooks = new ArrayList<Object>();
    @SerializedName("tvSeries")
    @Expose
    private List<String> tvSeries = new ArrayList<String>();
    @SerializedName("playedBy")
    @Expose
    private List<String> playedBy = new ArrayList<String>();

    /**
     * No args constructor for use in serialization
     *
     */
    public CharacterPojo() {
    }

    /**
     *
     * @param books
     * @param born
     * @param aliases
     * @param url
     * @param father
     * @param mother
     * @param died
     * @param spouse
     * @param tvSeries
     * @param name
     * @param allegiances
     * @param povBooks
     * @param playedBy
     * @param gender
     * @param titles
     * @param culture
     */
    public CharacterPojo(String url, String name, String gender, String culture, String born, String died, List<String> titles, List<String> aliases, String father, String mother, String spouse, List<String> allegiances, List<String> books, List<Object> povBooks, List<String> tvSeries, List<String> playedBy) {
        this.url = url;
        this.name = name;
        this.gender = gender;
        this.culture = culture;
        this.born = born;
        this.died = died;
        this.titles = titles;
        this.aliases = aliases;
        this.father = father;
        this.mother = mother;
        this.spouse = spouse;
        this.allegiances = allegiances;
        this.books = books;
        this.povBooks = povBooks;
        this.tvSeries = tvSeries;
        this.playedBy = playedBy;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     * The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     *
     * @return
     * The culture
     */
    public String getCulture() {
        return culture;
    }

    /**
     *
     * @param culture
     * The culture
     */
    public void setCulture(String culture) {
        this.culture = culture;
    }

    /**
     *
     * @return
     * The born
     */
    public String getBorn() {
        return born;
    }

    /**
     *
     * @param born
     * The born
     */
    public void setBorn(String born) {
        this.born = born;
    }

    /**
     *
     * @return
     * The died
     */
    public String getDied() {
        return died;
    }

    /**
     *
     * @param died
     * The died
     */
    public void setDied(String died) {
        this.died = died;
    }

    /**
     *
     * @return
     * The titles
     */
    public List<String> getTitles() {
        return titles;
    }

    /**
     *
     * @param titles
     * The titles
     */
    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    /**
     *
     * @return
     * The aliases
     */
    public List<String> getAliases() {
        return aliases;
    }

    /**
     *
     * @param aliases
     * The aliases
     */
    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    /**
     *
     * @return
     * The father
     */
    public String getFather() {
        return father;
    }

    /**
     *
     * @param father
     * The father
     */
    public void setFather(String father) {
        this.father = father;
    }

    /**
     *
     * @return
     * The mother
     */
    public String getMother() {
        return mother;
    }

    /**
     *
     * @param mother
     * The mother
     */
    public void setMother(String mother) {
        this.mother = mother;
    }

    /**
     *
     * @return
     * The spouse
     */
    public String getSpouse() {
        return spouse;
    }

    /**
     *
     * @param spouse
     * The spouse
     */
    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    /**
     *
     * @return
     * The allegiances
     */
    public List<String> getAllegiances() {
        return allegiances;
    }

    /**
     *
     * @param allegiances
     * The allegiances
     */
    public void setAllegiances(List<String> allegiances) {
        this.allegiances = allegiances;
    }

    /**
     *
     * @return
     * The books
     */
    public List<String> getBooks() {
        return books;
    }

    /**
     *
     * @param books
     * The books
     */
    public void setBooks(List<String> books) {
        this.books = books;
    }

    /**
     *
     * @return
     * The povBooks
     */
    public List<Object> getPovBooks() {
        return povBooks;
    }

    /**
     *
     * @param povBooks
     * The povBooks
     */
    public void setPovBooks(List<Object> povBooks) {
        this.povBooks = povBooks;
    }

    /**
     *
     * @return
     * The tvSeries
     */
    public List<String> getTvSeries() {
        return tvSeries;
    }

    /**
     *
     * @param tvSeries
     * The tvSeries
     */
    public void setTvSeries(List<String> tvSeries) {
        this.tvSeries = tvSeries;
    }

    /**
     *
     * @return
     * The playedBy
     */
    public List<String> getPlayedBy() {
        return playedBy;
    }

    /**
     *
     * @param playedBy
     * The playedBy
     */
    public void setPlayedBy(List<String> playedBy) {
        this.playedBy = playedBy;
    }

}