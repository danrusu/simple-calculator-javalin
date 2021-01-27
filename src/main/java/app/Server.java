package app;

import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;
import io.javalin.http.staticfiles.Location;
import lombok.extern.slf4j.Slf4j;

import static app.HomePage.getHtmlIndex;
import static java.lang.Integer.parseInt;

@Slf4j
public class Server {

    public static void main(final String[] args) {
        final Javalin app = Javalin
                .create(Server::configurationSetup)
                .start(getPort());

        setRoutes(app);
    }

    private static void configurationSetup(final JavalinConfig config) {
        config.addStaticFiles("html/css", Location.EXTERNAL);
        config.addStaticFiles("html/js", Location.EXTERNAL);
        config.requestLogger((ctx, ms) -> {
            log.info(ctx.method() + " " + ctx.fullUrl());
            log.info(ms + " ms");
        });
    }

    private static int getPort() {
        final String herokuPort = System.getenv("PORT");
        return herokuPort != null ?
                parseInt(herokuPort)
                : 7000;
    }

    private static void setRoutes(final Javalin app) {
        app.get("/", ctx -> ctx.html(getHtmlIndex()));
        app.get("/api/calculate", SimpleCalculator::calculateHandler);
    }

}