package app;

import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class HomePage {

    private HomePage() {
    }

    @SneakyThrows
    public static String getHtmlIndex() {
        Path htmlIndexFilePath = Paths.get(
                System.getProperty("user.dir"),
                "html",
                "index.html");

        try (Stream<String> lines = Files.lines(
                htmlIndexFilePath,
                StandardCharsets.UTF_8)
        ) {
            return lines.collect(joining("\n"));
        }
    }
}
