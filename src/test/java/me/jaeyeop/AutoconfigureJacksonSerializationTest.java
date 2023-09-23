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
 * 인스턴스를 생성하는 것이 아니기 때문에 Reflection API 를 사용하지 않는다.
 * <p>
 * Jackson 직렬화의 기본 동작 방식
 * <p>
 * 1. 인스턴스 생성: getter
 * <p>
 * 2. 데이터 바인딩: getter
 */
@SpringBootTest
class AutoconfigureJacksonSerializationTest {

    @Autowired
    ObjectMapper objectMapper;

    /**
     * jackson-databind 2.12 이후 버전부터 {@link com.fasterxml.jackson.databind.jdk14.JDK14Util Record 타입을 정식 지원}한다.
     * <p>
     * 이전 버전의 경우 record style 의 getter 를 찾지못하여 {@link InvalidDefinitionException} 이 발생한다.
     */
    @DisplayName("@JsonProperty 가 설정되지 않은 record 의 직렬화")
    @Test
    void serializeNoJsonPropertyRecord() throws JsonProcessingException {
        var obj = new NoJsonPropertyRecord("jackson", 25);
        var json = objectMapper.writeValueAsString(obj);
        assertThat(json).contains("jackson");
    }

    @DisplayName("@JsonProperty 가 설정된 record 의 직렬화")
    @Test
    void serializeWithJsonPropertyRecord() throws JsonProcessingException {
        var obj = new WithJsonPropertyRecord("jackson", 25);
        var json = objectMapper.writeValueAsString(obj);
        assertThat(json).contains("jackson");
    }

    @DisplayName("기본 생성자와 getter 가 있는 class 의 직렬화")
    @Test
    void serializeWithDefaultConstructorAndGetterClass() throws JsonProcessingException {
        var obj = new WithDefaultConstructorAndGetterClass("jackson", 25);
        var json = objectMapper.writeValueAsString(obj);
        assertThat(json).contains("jackson");
    }

    @DisplayName("기본 생성자와 setter 가 있는 class 의 직렬화")
    @Test
    void serializeWithDefaultConstructorAndSetterClass() {
        var obj = new WithDefaultConstructorAndSetterClass("jackson", 25);
        assertThatThrownBy(() -> objectMapper.writeValueAsString(obj)).isInstanceOf(InvalidDefinitionException.class);
    }

    @DisplayName("기본 생성자는 없지만 getter 가 있는 class 의 직렬화")
    @Test
    void serializeNoDefaultConstructorAndWithGetterClass() throws JsonProcessingException {
        var obj = new NoDefaultConstructorAndWithGetterClass("jackson", 25);
        var json = objectMapper.writeValueAsString(obj);
        assertThat(json).contains("jackson");
    }

    @DisplayName("기본 생성자는 없지만 setter 가 있는 class 의 직렬화")
    @Test
    void serializeNoDefaultConstructorAndWithSetterClass() {
        var obj = new NoDefaultConstructorAndWithSetterClass("jackson", 25);
        assertThatThrownBy(() -> objectMapper.writeValueAsString(obj)).isInstanceOf(InvalidDefinitionException.class);
    }

    @DisplayName("기본 생성자는 있지만 getter 가 없는 class 의 직렬화")
    @Test
    void serializeWithDefaultConstructorAndNoGetterClass() {
        var obj = new WithDefaultConstructorClass("jackson", 25);
        assertThatThrownBy(() -> objectMapper.writeValueAsString(obj)).isInstanceOf(InvalidDefinitionException.class);
    }

    @DisplayName("기본 생성자는 있지만 setter 가 없는 class 의 직렬화")
    @Test
    void serializeWithDefaultConstructorAndNoSetterClass() {
        var obj = new WithDefaultConstructorClass("jackson", 25);
        assertThatThrownBy(() -> objectMapper.writeValueAsString(obj)).isInstanceOf(InvalidDefinitionException.class);
    }

    @DisplayName("기본 생성자와 getter 가 없는 class 의 직렬화")
    @Test
    void serializeNoDefaultConstructorAndGetterClass() {
        var obj = new NoDefaultConstructorClass("jackson", 25);
        assertThatThrownBy(() -> objectMapper.writeValueAsString(obj)).isInstanceOf(InvalidDefinitionException.class);
    }

    @DisplayName("기본 생성자와 setter 가 없는 class 의 직렬화")
    @Test
    void serializeNoDefaultConstructorAndSetterClass() {
        var obj = new NoDefaultConstructorClass("jackson", 25);
        assertThatThrownBy(() -> objectMapper.writeValueAsString(obj)).isInstanceOf(InvalidDefinitionException.class);
    }

}
