package com.legacy;

import com.legacy.analysis.CodeTransformer;

public class Main {
    public static void main(String[] args) throws Exception {
        CodeTransformer codeTransformer = new CodeTransformer();
        codeTransformer.transformCodeInDirectory("src/main/java");
    }
}
