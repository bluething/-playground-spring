package io.github.bluething.playground.spring.gs.uploading.files.file;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
class FileUploadController {
    private final Storage storage;

    @GetMapping("/")
    String listUploadFiles(Model model) throws IOException {
        model.addAttribute("files", storage.loadAll().map(
                path -> MvcUriComponentsBuilder
                        .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                        .build().toUri().toString())
                .collect(Collectors.toList()
        ));
        return "uploadForm";
    }

    @GetMapping("/files/{fileName:.+}")
    @ResponseBody
    ResponseEntity<Resource> serveFile(@PathVariable String fileName) throws MalformedURLException {
       Resource file = storage.loadAsResource(fileName);
       return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    String handleFileUpload(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        storage.store(file);
        redirectAttributes.addFlashAttribute("message", "Your succesfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}
