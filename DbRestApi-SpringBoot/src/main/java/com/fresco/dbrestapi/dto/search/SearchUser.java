package com.fresco.dbrestapi.dto.search;

public class SearchUser {

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    private String user;
    private String searchText;


}
