package com.legacy.controller;

import com.legacy.analysis.CharacterizationTestGenerator;
import com.legacy.analysis.CodeTransformer;
import com.legacy.analysis.DependencyGraph;
import com.legacy.analysis.RiskGraph;
import com.legacy.legacyCodebase.LegacyCodebase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LegacyCodebaseController {

    private final CharacterizationTestGenerator characterizationTestGenerator;
    private final CodeTransformer codeTransformer;
    private final DependencyGraph dependencyGraph;
    private final RiskGraph riskGraph;

    @Autowired
    public LegacyCodebaseController(CharacterizationTestGenerator characterizationTestGenerator, CodeTransformer codeTransformer, DependencyGraph dependencyGraph, RiskGraph riskGraph) {
        this.characterizationTestGenerator = characterizationTestGenerator;
        this.codeTransformer = codeTransformer;
        this.dependencyGraph = dependencyGraph;
        this.riskGraph = riskGraph;
    }

    @GetMapping("/legacy-codebase")
    public String getLegacyCodebase() {
        return "Legacy Codebase";
    }

    @PostMapping("/legacy-codebase/analyze")
    public String analyzeLegacyCodebase(@RequestBody LegacyCodebase legacyCodebase) {
        // Call analysis methods here
        return "Analysis completed";
    }

    @PostMapping("/legacy-codebase/transform")
    public String transformLegacyCodebase(@RequestBody LegacyCodebase legacyCodebase) {
        // Call transformation methods here
        return "Transformation completed";
    }
}
