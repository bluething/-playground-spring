package io.github.bluething.playground.spring.callicoderuploaddownloadrest.file;

public record UploadFileResponse(String fileName,
                                 String fileDownloadUri,
                                 String fileType,
                                 long size) {
}
