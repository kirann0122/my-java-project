package com.legacy.controller;

import com.legacy.analysis.JavaParserAnalyzer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LegacyCodebaseController {

    private final JavaParserAnalyzer javaParserAnalyzer;

    public LegacyCodebaseController(JavaParserAnalyzer javaParserAnalyzer) {
        this.javaParserAnalyzer = javaParserAnalyzer;
    }

    @GetMapping("/analyze")
    @PreAuthorize("hasRole('ADMIN')")
    public String analyze() {
        javaParserAnalyzer.analyze("src/main/java");
        return "Analysis completed";
    }
}
