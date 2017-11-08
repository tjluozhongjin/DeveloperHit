package com.xiaojin.developerkit.Model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by luozhongjin on 04/11/2017.
 */

@Root(strict = false)
public class ZhuHuModel {



    @ElementList
    private ArrayList<Question> questions;

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    @Root(name = "question",strict = false)
    public static class Question{

        @Element(name = "title",required = false)
        private String title;

        @Element(name = "url",required = false)
        private String url;

        @Element(name = "upvotes",required = false)
        private String upvotes;

        @Element(name = "comment_count",required = false)
        private String comment_count;

        @Element(name = "user",required = false)
        private User user;


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage(){
            return null;
        }

        public String getUpvotes() {
            return upvotes;
        }

        public void setUpvotes(String upvotes) {
            this.upvotes = upvotes;
        }

        public String getComment_count() {
            return comment_count;
        }

        public void setComment_count(String comment_count) {
            this.comment_count = comment_count;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }

    @Root(name = "user",strict = false)
    public static class User{

        @Element(name = "name",required = false)
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
