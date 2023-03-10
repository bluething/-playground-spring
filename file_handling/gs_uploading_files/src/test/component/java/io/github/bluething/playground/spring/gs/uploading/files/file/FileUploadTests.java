package io.github.bluething.playground.spring.gs.uploading.files.file;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class FileUploadTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private Storage storage;

    @Test
    void shouldListAllFiles() throws Exception {
        given(this.storage.loadAll())
                .willReturn(Stream.of(Paths.get("first.txt"), Paths.get("second.txt")));
        this.mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(model().attribute("files", Matchers.contains("http://localhost/files/first.txt",
                        "http://localhost/files/second.txt")));
    }
}
