package com.fresco.dbrestapi.dto.add;

public class PostRequestDto {
    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String postBody;
    private String user;
}
