package com.legacy;

import com.legacy.analysis.JavaParserAnalyzer;

public class Main {
    public static void main(String[] args) throws Exception {
        LegacyCodebase codebase = new LegacyCodebase("src/main/java");
        codebase.addSourceFile("src/main/java/com/legacy/analysis/AnalysisPackage.java");
        codebase.analyze();
        // Print risk graph
        System.out.println(codebase.getRiskGraph().getRiskNodes("AnalysisPackage"));
        // Print dependency graph
        System.out.println(codebase.getDependencyGraph().getDependencies("AnalysisPackage"));
        // Print test methods
        System.out.println(codebase.getTestMethods());
    }
}
