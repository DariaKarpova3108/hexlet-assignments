package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void showPost(Context ctx) {
        try {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var post = PostRepository.find(id)
                    .orElseThrow(() -> new NotFoundResponse("Post with id: " + id + "not found"));
            var page = new PostPage(post);
            ctx.render("posts/show.jte", model("page", page));
        } catch (NotFoundResponse e) {
            ctx.status(404).result("Page not found");
        }
    }

    public static void showAllPosts(Context ctx) {
        var page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        var sliceOfPosts = PostRepository.findAll(page, 5);
        var postPage = new PostsPage(sliceOfPosts, page);
        ctx.render("posts/index.jte", model("page", postPage));
    }
}
// END

