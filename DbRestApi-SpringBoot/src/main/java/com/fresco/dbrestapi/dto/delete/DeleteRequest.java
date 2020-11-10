package com.fresco.dbrestapi.dto.delete;

public class DeleteRequest {

    private String user;
    private String postId;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
