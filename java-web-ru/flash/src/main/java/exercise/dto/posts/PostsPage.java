package exercise.dto.posts;

import exercise.dto.BasePage;
import exercise.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

// BEGIN
@AllArgsConstructor
@Getter
public class PostsPage extends BasePage {
    private List<Post> posts;
}
// END
