package me.jaeyeop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import me.jaeyeop.dto.clazz.*;
import me.jaeyeop.dto.record.NoJsonPropertyRecord;
import me.jaeyeop.dto.record.WithJsonPropertyRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 인스턴스를 Reflection API 를 통해 생성하기 때문에 default constructor 가 필요하다.
 * <p>
 * Jackson 역직렬화의 기본 동작 방식
 * <p>
 * 1. 인스턴스 생성: default constructor
 * <p>
 * 2. 데이터 바인딩: getter 또는 setter
 */
@SpringBootTest
class AutoconfigureJacksonDeserializationTest {

    @Autowired
    ObjectMapper objectMapper;

    @DisplayName("@JsonProperty 가 설정되지 않은 record 의 역직렬화")
    @Test
    void deserializeNoJsonPropertyRecord() throws JsonProcessingException {
        var json = "{\"name\":\"jackson\",\"age\":25}";
        var person = objectMapper.readValue(json, NoJsonPropertyRecord.class);
        assertThat(person.toString()).contains("jackson");
    }

    @DisplayName("@JsonProperty 가 설정된 record 의 역직렬화")
    @Test
    void deserializeWithJsonPropertyRecord() throws JsonProcessingException {
        var json = "{\"name\":\"jackson\",\"age\":25}";
        var obj = objectMapper.readValue(json, WithJsonPropertyRecord.class);
        assertThat(obj.toString()).contains("jackson");
    }

    @DisplayName("기본 생성자와 getter 가 있는 class 의 직렬화")
    @Test
    void serializeWithDefaultConstructorAndGetterClass() throws JsonProcessingException {
        var json = "{\"name\":\"jackson\",\"age\":25}";
        var obj = objectMapper.readValue(json, WithDefaultConstructorAndGetterClass.class);
        assertThat(obj.toString()).contains("jackson");
    }

    @DisplayName("기본 생성자와 setter 가 있는 class 의 역직렬화")
    @Test
    void deserializeWithDefaultConstructorAndSetterClass() throws JsonProcessingException {
        var json = "{\"name\":\"jackson\",\"age\":25}";
        var obj = objectMapper.readValue(json, WithDefaultConstructorAndSetterClass.class);
        assertThat(obj.toString()).contains("jackson");
    }

    @DisplayName("기본 생성자는 없지만 getter 가 있는 class 의 역직렬화")
    @Test
    void deserializeNoDefaultConstructorAndWithGetterClass() {
        var json = "{\"name\":\"jackson\",\"age\":25}";
        assertThatThrownBy(() -> objectMapper.readValue(json, NoDefaultConstructorAndWithGetterClass.class)).isInstanceOf(InvalidDefinitionException.class);
    }

    @DisplayName("기본 생성자는 없지만 setter 가 있는 class 의 역직렬화")
    @Test
    void deserializeNoDefaultConstructorAndWithSetterClass() {
        var json = "{\"name\":\"jackson\",\"age\":25}";
        assertThatThrownBy(() -> objectMapper.readValue(json, NoDefaultConstructorAndWithSetterClass.class)).isInstanceOf(InvalidDefinitionException.class);
    }

    @DisplayName("기본 생성자는 있지만 getter 가 없는 class 의 역직렬화")
    @Test
    void deserializeWithDefaultConstructorAndNoGetterClass() throws JsonProcessingException {
        var json = "{\"name\":\"jackson\",\"age\":25}";
        var obj = objectMapper.readValue(json, WithDefaultConstructorClass.class);
        assertThat(obj.toString()).contains("null");
    }

    @DisplayName("기본 생성자는 있지만 setter 가 없는 class 의 역직렬화")
    @Test
    void deserializeWithDefaultConstructorAndNoSetterClass() throws JsonProcessingException {
        var json = "{\"name\":\"jackson\",\"age\":25}";
        var obj = objectMapper.readValue(json, WithDefaultConstructorClass.class);
        assertThat(obj.toString()).contains("null");
    }

    @DisplayName("기본 생성자와 getter 가 없는 class 의 역직렬화")
    @Test
    void deserializeNoDefaultConstructorAndGetterClass() {
        var json = "{\"name\":\"jackson\",\"age\":25}";
        assertThatThrownBy(() -> objectMapper.readValue(json, NoDefaultConstructorClass.class)).isInstanceOf(InvalidDefinitionException.class);
    }

    @DisplayName("기본 생성자와 setter 가 없는 class 의 역직렬화")
    @Test
    void deserializeNoDefaultConstructorAndSetterClass() {
        var json = "{\"name\":\"jackson\",\"age\":25}";
        assertThatThrownBy(() -> objectMapper.readValue(json, NoDefaultConstructorClass.class)).isInstanceOf(InvalidDefinitionException.class);
    }

}
