package name.alexkosarev.tutorial.controller;

import name.alexkosarev.tutorial.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TestConfig.class)
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class FrameworkRestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllFrameworks_ReturnsFrameworksList() throws Exception {
        // when
        this.mockMvc.perform(get("/api/frameworks"))
                // then
                .andExpect(status().isOk());
    }

    @Test
    public void createFramework_ReturnsCreatedFramework() throws Exception {
        // when
        this.mockMvc.perform(post("/api/frameworks")
                .param("name", "Testcontainers Java")
                .param("language", "java")
                .param("link", "https://testcontainers.org"))
                .andExpect(status().isOk());
    }
}
