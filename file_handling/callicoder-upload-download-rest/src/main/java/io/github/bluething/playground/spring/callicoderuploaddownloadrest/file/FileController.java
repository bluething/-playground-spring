package io.github.bluething.playground.spring.callicoderuploaddownloadrest.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@RestController
public class FileController {
    private final Storage storage;

    @Autowired
    public FileController(Storage storage) {
        this.storage = storage;
    }

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = storage.storeFile(file);
        String downloadFileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, downloadFileUri, file.getContentType(), file.getSize());
    }
}
