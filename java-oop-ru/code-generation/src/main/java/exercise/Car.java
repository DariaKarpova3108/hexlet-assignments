package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

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
    public static String serialize(Car file) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(file);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public static Car unserialize(String content) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(content, Car.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // END
}
