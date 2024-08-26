package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    private List<CommentDTO> converteCommentsDTO(List<Comment> comments) {
        var dtoModel = new CommentDTO();
        List<CommentDTO> resultList = new ArrayList<>();
        for (var comment : comments) {
            dtoModel.setId(comment.getId());
            dtoModel.setBody(comment.getBody());
            resultList.add(dtoModel);
        }
        return resultList;
    }

    private PostDTO convertePostDTO(Post post, List<CommentDTO> commentsDTO) {
        var dtoModel = new PostDTO();
        dtoModel.setId(post.getId());
        dtoModel.setTitle(post.getTitle());
        dtoModel.setBody(post.getBody());
        dtoModel.setCommentsDTO(commentsDTO);
        return dtoModel;
    }

    @GetMapping("/{id}")
    public PostDTO getPostDTO(@PathVariable long id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        var comments = commentRepository.findByPostId(id);
        var commentsDTOList = converteCommentsDTO(comments);
        return convertePostDTO(post, commentsDTOList);
    }

    @GetMapping
    public List<PostDTO> getList() {
        var posts = postRepository.findAll();
        List<PostDTO> listDTOModels = new ArrayList<>();
        for (var post : posts) {
            var comments = commentRepository.findByPostId(post.getId());
            var commentsDTOList = converteCommentsDTO(comments);
            var dto = convertePostDTO(post, commentsDTOList);
            listDTOModels.add(dto);
        }
        return listDTOModels;
    }
}
// END
