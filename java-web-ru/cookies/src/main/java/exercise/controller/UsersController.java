package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void create(Context ctx) {
        try {
            var firstName = StringUtils.capitalize(ctx.formParam("firstName"));
            var lastName = StringUtils.capitalize(ctx.formParam("lastName"));
            var email = ctx.formParam("email").toLowerCase().trim();
            var password = ctx.formParam("password");
            var token = Security.generateToken();
            var user = new User(firstName, lastName, email, password, token);
            UserRepository.save(user);
            ctx.cookie("token", token);
            var id = user.getId();
            ctx.redirect("/users/" + id);
        } catch (Exception e) {
            ctx.status(500).result("Ошибка при регистрации пользователя");
        }
    }

    public static void show(Context ctx) {
        try {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var user = UserRepository.find(id)
                    .orElseThrow(()->new NotFoundResponse("User not found"));
            var token = ctx.cookie("token");
            var userToken = user.getToken();
            if(token!=null&& token.equals(userToken)) {
                var page = new UserPage(user);
                ctx.render("users/show.jte", model("page", page));
            } else {
                ctx.redirect("/users/build");
            }
        } catch (Exception e) {
            ctx.status(500);
            ctx.result("Ошибка при получении данных пользователя");
        }
    }
    // END
}
