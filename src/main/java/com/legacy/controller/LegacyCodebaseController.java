package com.legacy.controller;

import com.legacy.analysis.JavaParserAnalyzer;
import com.legacy.analysis.RiskGraph;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LegacyCodebaseController {
    private JavaParserAnalyzer javaParserAnalyzer;

    public LegacyCodebaseController() {
        this.javaParserAnalyzer = new JavaParserAnalyzer();
    }

    @GetMapping("/risk-graph")
    public RiskGraph getRiskGraph() {
        return javaParserAnalyzer.getRiskGraph();
    }
}
