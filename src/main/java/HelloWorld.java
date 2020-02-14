import io.javalin.Javalin;
import io.javalin.http.Handler;

public class HelloWorld {

    static Handler apiHandler = ctx -> {
        System.out.println("api path");
        ctx.result("api");
    };
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);
        app.get("/", ctx -> ctx.result("Hello World"));
        app.get("/api", apiHandler);
    }
}