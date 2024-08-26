package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/comments")
public class CommentsController {
    /*
    GET /comments — список всех комментариев
    GET /comments/{id} – просмотр конкретного комментария
    POST /comments – создание нового комментария. При успешном создании возвращается статус 201
    PUT /comments/{id} – обновление комментария
    DELETE /comments/{id} – удаление комментария
    * */

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@RequestBody Comment comment) {
        commentRepository.save(comment);
        return comment;
    }

    @GetMapping
    public List<Comment> getList() {
        return commentRepository.findAll();
    }


    @GetMapping("/{id}")
    public Comment getComment(@PathVariable Long id) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found comment with id: " + id));
        return comment;
    }

    @PutMapping("/{id}")
    public Comment update(@PathVariable Long id, @RequestBody Comment data) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found comment with id: " + id));
        comment.setBody(data.getBody());
        comment.setCreatedAt(data.getCreatedAt());
        return data;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        commentRepository.deleteById(id);
        commentRepository.deleteByPostId(id);
    }

}

// END
