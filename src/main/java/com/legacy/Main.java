package com.legacy;

import com.legacy.analysis.CodeTransformer;

public class Main {
    public static void main(String[] args) throws Exception {
        CodeTransformer transformer = new CodeTransformer();
        transformer.autoMigrateSimplePatterns("src/main/java");
    }
}
