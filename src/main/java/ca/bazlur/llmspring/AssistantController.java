package ca.bazlur.llmspring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AssistantController {
  private final Assistant assistant;

  @GetMapping("/")
  public String home() {
    return "redirect:/index.html";
  }

  @GetMapping("/assistant")
  @ResponseBody
  @CrossOrigin(origins = "*")
  public String assistant(
      @RequestParam(value = "message", defaultValue = "What is the current time?") String message) {
    return assistant.chat(message);
  }
}
