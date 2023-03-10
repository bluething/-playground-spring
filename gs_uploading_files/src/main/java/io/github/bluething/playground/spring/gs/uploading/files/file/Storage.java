package io.github.bluething.playground.spring.gs.uploading.files.file;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface Storage {
    void init() throws IOException;

    void store(MultipartFile file) throws IOException;

    Stream<Path> loadAll() throws IOException;

    Path load(String filename);

    Resource loadAsResource(String filename) throws MalformedURLException;

    void deleteAll() throws IOException;
}
