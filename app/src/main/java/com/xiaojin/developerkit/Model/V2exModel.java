package com.xiaojin.developerkit.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by luozhongjin on 04/11/2017.
 */

public class V2exModel {

    @SerializedName("title")
    private String title;

    @SerializedName("url")
    private String url;

    @SerializedName("created")
    private String created;

    @SerializedName("last_touched")
    private String last_touched;

    @SerializedName("replies")
    private String replies;

    @SerializedName("member")
    private Member member;

    @SerializedName("node")
    private Node node;

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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getImage(){
        return member.getAvatar_large();
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public String getReplies() {
        return replies;
    }

    public void setReplies(String replies) {
        this.replies = replies;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLast_touched() {
        return last_touched;
    }

    public void setLast_touched(String last_touched) {
        this.last_touched = last_touched;
    }


    public static class Member {

        @SerializedName("username")
        private String username;

        @SerializedName("avatar_large")
        private String avatar_large;

        public String getAvatar_large() {
            return "http:" + avatar_large;
        }

        public void setAvatar_large(String avatar_large) {
            this.avatar_large = avatar_large;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public static class Node{
        @SerializedName("title")
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

}

