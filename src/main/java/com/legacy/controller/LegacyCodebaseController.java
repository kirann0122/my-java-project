package com.legacy.controller;

import com.legacy.analysis.JavaParserAnalyzer;
import com.legacy.analysis.RiskGraph;
import com.legacy.legacyCodebase.LegacyCodebase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
public class LegacyCodebaseController {

    private final JavaParserAnalyzer javaParserAnalyzer;

    @Autowired
    public LegacyCodebaseController(JavaParserAnalyzer javaParserAnalyzer) {
        this.javaParserAnalyzer = javaParserAnalyzer;
    }

    @GetMapping("/api/analyzeLegacyCodebase")
    public String analyzeLegacyCodebase() throws FileNotFoundException {
        javaParserAnalyzer.analyze("src/main/java");
        return "Legacy Codebase analyzed";
    }

    @GetMapping("/api/getRiskGraph")
    public RiskGraph getRiskGraph() {
        return javaParserAnalyzer.getRiskGraph();
    }
}
