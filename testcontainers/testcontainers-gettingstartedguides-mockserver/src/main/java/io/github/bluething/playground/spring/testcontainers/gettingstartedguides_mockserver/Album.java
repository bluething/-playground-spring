package io.github.bluething.playground.spring.testcontainers.gettingstartedguides_mockserver;

import java.util.List;

public record Album(Long albumId, List<Photo> photos) { }
record Photo(Long id, String title, String url, String thumbnailUrl) { }
