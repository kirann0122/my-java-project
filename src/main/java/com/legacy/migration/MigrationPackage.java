package com.legacy.migration;

import com.legacy.analysis.LLMTransformer;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.utils.SourceRoot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class MigrationPackage {
    private LLMTransformer llmTransformer;

    public MigrationPackage() {
        this.llmTransformer = new LLMTransformer();
    }

    public void migrateCode(String filePath) throws FileNotFoundException, IOException {
        CompilationUnit cu = com.github.javaparser.JavaParser.parse(new File(filePath));
        cu.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(ClassOrInterfaceDeclaration n, Void arg) {
                super.visit(n, arg);
                n.getMethods().forEach(method -> {
                    method.getNodes().forEach(node -> {
                        if (node instanceof MethodCallExpr) {
                            MethodCallExpr methodCallExpr = (MethodCallExpr) node;
                            String methodName = methodCallExpr.getNameAsString();
                            String className = methodCallExpr.getScope().map(Node::toString).orElse("");
                            try {
                                String transformedCode = llmTransformer.transformCode(methodCallExpr.toString());
                                // Replace original code with transformed code
                                methodCallExpr.replace(transformedCode);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                });
            }
        }, null);

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(cu.toString());
        }
    }
}
