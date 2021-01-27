package app;

import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;

import static app.HomePage.getHtmlIndex;

@Slf4j
public class Server {

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);
        app.get("/", ctx -> ctx.html(getHtmlIndex()));
        app.get("/api/calculate", SimpleCalculator::calculateHandler);
    }
    
}