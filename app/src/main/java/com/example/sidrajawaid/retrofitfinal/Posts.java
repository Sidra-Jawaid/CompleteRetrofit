
package com.example.sidrajawaid.retrofitfinal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Posts implements Serializable
{
    @SerializedName("userId")
    @Expose
    private int userid;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
@Expose
private String title;
    @SerializedName("body")
    @Expose
    private String body;

    public Posts() {
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getUserId() {
        return userid;
    }

    public void setUserId(Integer userId) {
        this.userid = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", userId=" + userid +
                ", id=" + id +
                '}';
    }

}
