package exercise;

import lombok.SneakyThrows;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {
    @SneakyThrows
    public static void save(Path path, Car car) {
        Path fullPath = path.toAbsolutePath().normalize();
        String content = Car.serialize(car);
        Files.writeString(fullPath, content, StandardOpenOption.WRITE);
    }

    @SneakyThrows
    public static Car extract(Path path) {
        Path fullPath = path.toAbsolutePath().normalize();
        String pathStr = Files.readString(fullPath);
        Car car = Car.unserialize(pathStr);
        return car;
    }
}

// END
