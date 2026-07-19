package com.legacy;

import com.legacy.analysis.JavaParserAnalyzer;

public class Main {
    public static void main(String[] args) throws Exception {
        JavaParserAnalyzer analyzer = new JavaParserAnalyzer();
        analyzer.analyze("src/main/java/com/legacy/analysis/AnalysisPackage.java");
        // Print risk graph
        System.out.println(analyzer.getRiskGraph().getRiskNodes("AnalysisPackage"));
    }
}
