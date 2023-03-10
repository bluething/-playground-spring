package io.github.bluething.playground.spring.callicoderuploaddownloadrest.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService implements Storage {

    private final Path rootLocation;

    @Autowired
    public FileStorageService(StorageProperties storageProperties) {
        this.rootLocation = Paths.get(storageProperties.location())
                .toAbsolutePath()
                .normalize();
    }

    @Override
    public String storeFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path destination = this.rootLocation.resolve(fileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }

    @Override
    public Resource loadFileAsResource(String fileName) throws MalformedURLException {
        Path filePath = this.rootLocation.resolve(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists()) {
            return resource;
        }

        return null;
    }
}
