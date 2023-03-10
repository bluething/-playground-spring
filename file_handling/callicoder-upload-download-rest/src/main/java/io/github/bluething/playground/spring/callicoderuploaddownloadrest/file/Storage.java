package io.github.bluething.playground.spring.callicoderuploaddownloadrest.file;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface Storage {
    String storeFile(MultipartFile file) throws IOException;
    Resource loadFileAsResource(String fileName) throws MalformedURLException;
}