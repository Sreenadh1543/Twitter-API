package com.fresco.dbrestapi.controller;

import com.fresco.dbrestapi.dto.add.PostRequestDto;
import com.fresco.dbrestapi.dto.delete.DeleteRequest;
import com.fresco.dbrestapi.dto.get.User;
import com.fresco.dbrestapi.dto.search.SearchUser;
import com.fresco.dbrestapi.dto.subscriber.SubscriberRequest;
import com.fresco.dbrestapi.model.Post;
import com.fresco.dbrestapi.model.Userposts;
import com.fresco.dbrestapi.repo.UserpostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class ApiController {
    @Autowired
    UserpostsRepository postsRepo;

    @CrossOrigin
    @PostMapping(path = "/addpost",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String post(String user, String postBody) {
        Optional<Userposts> byId = postsRepo.findById(user);
        if (byId.isPresent()) {
            Userposts userpost = byId.get();
            List<Post> posts = userpost.getPosts();
            if (null != posts) {
                posts.add(new Post(new Random().nextInt(900) + 100,
                        postBody,
                        new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())));
            }
            userpost.setPosts(posts);
            postsRepo.save(userpost);
        } else {
            Userposts userPosts = new Userposts();
            userPosts.setPosts(Arrays.asList(
                    new Post(new Random().nextInt(900) + 100,
                            postBody,
                            new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()))));
            userPosts.set_id(user);
            postsRepo.insert(userPosts);
        }
        return "OK 200";
    }

    @CrossOrigin
    @PostMapping(path = "/getposts",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Optional<Userposts> getPosts(String user) {
        return postsRepo.findById(user);
    }

    @CrossOrigin
    @PostMapping(path = "/delpost",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String delPosts(String user,String postId) {
        Optional<Userposts> byId = postsRepo.findById(user);
        if (byId.isPresent() && null!= postId){
            Userposts userposts = byId.get();
            List<Post> posts = userposts.getPosts();
            if(null!=posts && posts.size()>0){
                Iterator<Post> postsToIterateAndRemove = posts.iterator();
                while (postsToIterateAndRemove.hasNext()){
                    Post post = postsToIterateAndRemove.next();
                    if(postId.equals(String.valueOf(post.getPostId()))){
                        postsToIterateAndRemove.remove();
                    }
                }
            }
            userposts.setPosts(posts);
            postsRepo.save(userposts);
        }else{
            return "NOK 500";
        }
        return "OK 200";
    }

    @CrossOrigin
    @PostMapping(value = "/searchuser",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Map<String, Boolean> searchUser(String user,String searchText) {

        Map<String, Boolean> subscriberMapping = new HashMap<>();

        List<Userposts> all = postsRepo.findAll();
        List<Userposts> matchedUsers = new ArrayList<>();

        for (Userposts userpost : all) {
            if (userpost.get_id().contains(searchText)) {
                matchedUsers.add(userpost);
            }
        }

        for (Userposts matchedUser : matchedUsers) {
            subscriberMapping.put(matchedUser.get_id(), isUserASubscriber(matchedUser, user));
        }


        return subscriberMapping;
    }

    private Boolean isUserASubscriber(Userposts matchedUser, String user) {
        if (null != matchedUser.getSubscribed() && matchedUser.getSubscribed().size() > 0) {
            return matchedUser.getSubscribed().contains(user);
        }
        return false;
    }

    @CrossOrigin
    @RequestMapping(value = "/subscriber",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String subscriber(String user,String subuser) {
        Userposts userposts = postsRepo.findById(user).get();
        List<String> subscribed = userposts.getSubscribed();
        if (null != subscribed) {
            subscribed.add(subuser);
        } else {
            List<String> strings = new ArrayList<>();
            strings.add(subuser);
            userposts.setSubscribed(strings);
        }

        postsRepo.save(userposts);
        return "OK 200";
    }
}
