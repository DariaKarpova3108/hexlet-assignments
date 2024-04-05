package exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN
@AllArgsConstructor
@Getter
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    @SneakyThrows
    public static String serialize(Car file) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(file);
    }

    @SneakyThrows
    public static Car unserialize(String content) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Car.class);
    }
    // END
}
