package name.alexkosarev.tutorial.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import name.alexkosarev.tutorial.Framework;
import name.alexkosarev.tutorial.repository.FrameworkRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/frameworks")
@RequiredArgsConstructor
public class FrameworkRestController {

    @NonNull
    private final FrameworkRepository frameworkRepository;

    @GetMapping
    public ResponseEntity<List<Framework>> getAllFrameworks() {
        return ResponseEntity.ok(this.frameworkRepository.findAllFrameworks());
    }

    @PostMapping
    public ResponseEntity<Framework> createFramework(String name, String language, String link) {
        var framework = new Framework(name, language, link);
        framework.setId(this.frameworkRepository.persistFramework(framework));
        return ResponseEntity.ok(framework);
    }
}
