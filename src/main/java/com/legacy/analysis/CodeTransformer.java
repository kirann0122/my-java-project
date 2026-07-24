package com.legacy.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.utils.SourceRoot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Applies AI-assisted transformations to legacy code.
 */
public class CodeTransformer {
    private LLMTransformer llmTransformer;

    public CodeTransformer() {
        this.llmTransformer = new LLMTransformer();
    }

    public void transformCode(String filePath) throws Exception {
        CompilationUnit cu = com.github.javaparser.JavaParser.parse(new File(filePath));
        cu.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(ClassOrInterfaceDeclaration n, Void arg) {
                super.visit(n, arg);
                n.getMethods().forEach(method -> {
                    try {
                        transformMethod(method);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }, null);
    }

    private void transformMethod(MethodDeclaration method) throws Exception {
        String originalCode = method.toString();
        String transformedCode = llmTransformer.transformCode(originalCode);
        method.replace(originalCode, transformedCode);
    }

    public void transformCodeInDirectory(String directoryPath) throws Exception {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    transformCodeInDirectory(file.getAbsolutePath());
                } else if (file.getName().endsWith(".java")) {
                    transformCode(file.getAbsolutePath());
                }
            }
        }
    }

    public void autoMigrateSimplePatterns(String directoryPath) throws Exception {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    autoMigrateSimplePatterns(file.getAbsolutePath());
                } else if (file.getName().endsWith(".java")) {
                    autoMigrateSimplePatternInFile(file.getAbsolutePath());
                }
            }
        }
    }

    private void autoMigrateSimplePatternInFile(String filePath) throws Exception {
        CompilationUnit cu = com.github.javaparser.JavaParser.parse(new File(filePath));
        cu.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(ClassOrInterfaceDeclaration n, Void arg) {
                super.visit(n, arg);
                n.getMethods().forEach(method -> {
                    try {
                        autoMigrateSimplePatternInMethod(method);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }, null);
    }

    private void autoMigrateSimplePatternInMethod(MethodDeclaration method) throws Exception {
        // Check for simple patterns like XML bean configs and migrate them to modern Spring Boot annotations
        if (method.getNameAsString().contains("xml")) {
            String originalCode = method.toString();
            String transformedCode = llmTransformer.transformCode(originalCode);
            method.replace(originalCode, transformedCode);
        }
    }
}
