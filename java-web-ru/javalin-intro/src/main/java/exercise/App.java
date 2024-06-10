package exercise;

// BEGIN

import io.javalin.Javalin;
// END

public final class App {

    public static Javalin getApp() {

        // BEGIN
        var app = Javalin.create(confing -> {
            confing.bundledPlugins.enableDevLogging();
        });

        app.get("/welcome", cxt -> cxt.result("Welcome to Hexlet!"));
        return app;
        // END
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
