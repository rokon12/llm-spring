package ca.bazlur.llmspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AssistantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldServeIndexPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index.html"));

        mockMvc.perform(get("/index.html"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Chat Assistant")));
    }

    @Test
    public void shouldServeStaticResources() throws Exception {
        mockMvc.perform(get("/index.html"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html"))
                .andExpect(content().string(containsString("Chat Assistant")))
                .andExpect(content().string(containsString("chat-button")))
                .andExpect(content().string(containsString("chat-container")));
    }

    @Test
    public void shouldHandleChatRequest() throws Exception {
        String testMessage = "Hello";
        mockMvc.perform(get("/assistant")
                .param("message", testMessage))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    public void shouldHandleEmptyMessage() throws Exception {
        mockMvc.perform(get("/assistant"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }
}
