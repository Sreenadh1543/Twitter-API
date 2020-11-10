package com.fresco.dbrestapi.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post{
	private int postId;
	private String postBody;
	private String postDate;
}
