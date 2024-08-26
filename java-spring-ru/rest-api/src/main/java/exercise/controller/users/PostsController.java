package exercise.controller.users;

import exercise.Data;
import exercise.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// BEGIN

@RestController
@RequestMapping("/api")
public class PostsController {
    List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public List<Post> getUsersPosts(@PathVariable int id) {
        List<Post> postUsers = posts.stream().filter(p -> p.getUserId() == id).toList();
        return postUsers;
    }

    @PostMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createUsersPosts(@PathVariable int id, @RequestBody Post dataPost) {
        if (dataPost.getSlug() == null || dataPost.getTitle() == null || dataPost.getBody() == null) {
            throw new IllegalArgumentException("Slug, title and body must be provided");
        }
        dataPost.setUserId(id);
        posts.add(dataPost);
        return dataPost;
    }
}


// END
