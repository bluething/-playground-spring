package io.github.bluething.playground.spring.versioning.hexagonarch.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RestResponse(@JsonProperty("data") Object payload) {
}
