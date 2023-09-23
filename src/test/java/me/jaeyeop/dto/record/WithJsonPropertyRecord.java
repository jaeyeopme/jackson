package me.jaeyeop.dto.record;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WithJsonPropertyRecord(
        @JsonProperty("name") String name,
        @JsonProperty("age") int age
) {
}
