package com.xiaojin.developerkit.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by luozhongjin on 04/11/2017.
 */

public class GankModel {

    @SerializedName("results")
    private ArrayList<Result> resultArrayList;


    public ArrayList<Result> getResultArrayList() {
        return resultArrayList;
    }

    public void setResultArrayList(ArrayList<Result> resultArrayList) {
        this.resultArrayList = resultArrayList;
    }

    public static class Result{

        @SerializedName("type")
        private String type;


        @SerializedName("desc")
        private String title;

        @SerializedName("url")
        private String url;

        @SerializedName("publishedAt")
        private String publishedAt;

        @SerializedName("who")
        private String who;


        public String getTitle() {
            if(title == null&&title==""){
                return "None";
            }
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            if(url==null&&url==""){
                return "http://gank.io";
            }
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getType() {
            if (type==null&&type==""){
                return "None";
            }
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getWho() {
            if(who==null&&who==""){
                return "None";
            }
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public String getPublishedAt() {
            if(publishedAt==null&&publishedAt==""){
                return "None";
            }
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }
    }



}



