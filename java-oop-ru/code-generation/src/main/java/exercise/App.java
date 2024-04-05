package exercise;

import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {

    public static void save(Path path, Car car) {
        Path fullPath = path.toAbsolutePath().normalize();
        String content = Car.serialize(car);
        try {
            Files.writeString(fullPath, content, StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static Car extract(Path path) {
        Path fullPath = path.toAbsolutePath().normalize();
        String pathStr = null;
        try {
            pathStr = Files.readString(fullPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Car car = Car.unserialize(pathStr);
        return car;
    }
}

// END
