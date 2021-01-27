package app;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import lombok.extern.slf4j.Slf4j;

import static app.HomePage.getHtmlIndex;
import static java.lang.Integer.parseInt;

@Slf4j
public class Server {

    public static void main(String[] args) {
        String port = System.getenv("PORT");
        int htmlPort = port != null ? parseInt(port) : 7000;

        Javalin app = Javalin.create(config -> {
                    config.addStaticFiles("html/css", Location.EXTERNAL);
                    config.addStaticFiles("html/js", Location.EXTERNAL);
                }
        ).start(htmlPort);
        app.get("/", ctx -> ctx.html(getHtmlIndex()));
        app.get("/api/calculate", SimpleCalculator::calculateHandler);
    }

}