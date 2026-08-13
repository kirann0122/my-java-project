package com.legacy.controller;

import com.legacy.analysis.AnalysisPackage;
import com.legacy.analysis.CharacterizationTestGenerator;
import com.legacy.analysis.CodeTransformer;
import com.legacy.analysis.DependencyGraph;
import com.legacy.analysis.JavaParserAnalyzer;
import com.legacy.analysis.RiskGraph;
import com.legacy.legacyCodebase.LegacyCodebase;
import com.legacy.migration.MigrationPackage;
import com.legacy.migration.MigrationProgressTracker;
import com.legacy.reporting.RiskHeatmapGenerator;
import com.legacy.reporting.RiskReporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/api")
public class LegacyCodebaseController {

    private final JavaParserAnalyzer javaParserAnalyzer;
    private final CharacterizationTestGenerator characterizationTestGenerator;
    private final CodeTransformer codeTransformer;
    private final RiskHeatmapGenerator riskHeatmapGenerator;

    @Autowired
    public LegacyCodebaseController(JavaParserAnalyzer javaParserAnalyzer, CharacterizationTestGenerator characterizationTestGenerator, CodeTransformer codeTransformer, RiskHeatmapGenerator riskHeatmapGenerator) {
        this.javaParserAnalyzer = javaParserAnalyzer;
        this.characterizationTestGenerator = characterizationTestGenerator;
        this.codeTransformer = codeTransformer;
        this.riskHeatmapGenerator = riskHeatmapGenerator;
    }

    @PostMapping("/analyze")
    public ResponseEntity<String> analyzeCodebase(@Valid @RequestBody String filePath) {
        try {
            javaParserAnalyzer.analyze(filePath);
            return new ResponseEntity<>("Codebase analyzed successfully", HttpStatus.OK);
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>("File not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error analyzing codebase: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/generate-tests")
    public ResponseEntity<String> generateCharacterizationTests(@Valid @RequestBody String rootDirectory) {
        try {
            characterizationTestGenerator.generateCharacterizationTestsForUntouchedLegacyClasses(rootDirectory);
            return new ResponseEntity<>("Characterization tests generated successfully", HttpStatus.OK);
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>("File not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error generating characterization tests: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/transform-code")
    public ResponseEntity<String> transformCode(@Valid @RequestBody String filePath) {
        try {
            codeTransformer.getMigrationProgressTracker().trackProgress();
            return new ResponseEntity<>("Code transformed successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error transforming code: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/risk-heatmap")
    public ResponseEntity<String> visualizeRiskHeatmap() {
        try {
            riskHeatmapGenerator.generateHeatmap();
            return new ResponseEntity<>("Risk heatmap generated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error generating risk heatmap: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
