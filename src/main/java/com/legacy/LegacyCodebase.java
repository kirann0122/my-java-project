package com.legacy;

import com.legacy.analysis.CharacterizationTestGenerator;
import com.legacy.analysis.JavaParserAnalyzer;
import com.legacy.analysis.RiskGraph;
import com.legacy.analysis.DependencyGraph;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a legacy codebase being analyzed.
 */
public class LegacyCodebase {
    private String rootDirectory;
    private List<String> sourceFiles;
    private JavaParserAnalyzer analyzer;
    private RiskGraph riskGraph;
    private DependencyGraph dependencyGraph;
    private CharacterizationTestGenerator testGenerator;

    public LegacyCodebase(String rootDirectory) {
        this.rootDirectory = rootDirectory;
        this.sourceFiles = new ArrayList<>();
        this.analyzer = new JavaParserAnalyzer();
        this.riskGraph = analyzer.getRiskGraph();
        this.dependencyGraph = analyzer.getDependencyGraph();
        this.testGenerator = new CharacterizationTestGenerator();
    }

    public void addSourceFile(String filePath) {
        sourceFiles.add(filePath);
    }

    public void analyze() throws Exception {
        for (String sourceFile : sourceFiles) {
            analyzer.analyze(sourceFile);
            testGenerator.generateTests(sourceFile);
        }
    }

    public void generateCharacterizationTestsForUntouchedLegacyClasses() throws Exception {
        testGenerator.generateCharacterizationTestsForUntouchedLegacyClasses(rootDirectory);
    }

    public RiskGraph getRiskGraph() {
        return riskGraph;
    }

    public DependencyGraph getDependencyGraph() {
        return dependencyGraph;
    }

    public List<String> getTestMethods() {
        return testGenerator.getTestMethods();
    }
}
