package exercise;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

// BEGIN
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.CREATE);
    }

    // BEGIN
    @Test
    void testFileKV() {
        KeyValueStorage file = new FileKV("src/test/resources/file", Map.of("key", "value"));
        file.set("key2", "value2");
        Map<String, String> expected = Map.of("key", "value", "key2", "value2");
        assertThat(file.toMap()).isEqualTo(expected);

        file.unset("key2");
        Map<String, String> expectedUnset = Map.of("key", "value");
        assertThat(file.toMap()).isEqualTo(expectedUnset);

        assertThat(file.get("key2", "default")).isEqualTo("default");

        assertThat(file.toMap()).isEqualTo(expectedUnset);

    }

    // END
}
