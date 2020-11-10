package com.fresco.dbrestapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "FrescoTweet")
public class Userposts {
	@Id
	private String _id;
	private List<Post> posts;
	private List<String> subscribed;
}
