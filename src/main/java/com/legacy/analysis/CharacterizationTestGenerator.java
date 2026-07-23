package com.legacy.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.utils.SourceRoot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates safety-net characterization tests for legacy code.
 */
public class CharacterizationTestGenerator {
    private List<String> testMethods;

    public CharacterizationTestGenerator() {
        this.testMethods = new ArrayList<>();
    }

    public void generateTests(String filePath) throws FileNotFoundException {
        CompilationUnit cu = com.github.javaparser.JavaParser.parse(new File(filePath));
        cu.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(ClassOrInterfaceDeclaration n, Void arg) {
                super.visit(n, arg);
                n.getMethods().forEach(method -> {
                    testMethods.add(generateTestMethod(n, method));
                });
            }
        }, null);
    }

    private String generateTestMethod(ClassOrInterfaceDeclaration classDeclaration, MethodDeclaration method) {
        String methodName = method.getNameAsString();
        String className = classDeclaration.getNameAsString();
        String testMethod = "public void test" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1) + "() {\n";
        testMethod += "    " + className + " instance = new " + className + "();\n";
        testMethod += "    instance." + methodName + "();\n";
        testMethod += "}";
        return testMethod;
    }

    public List<String> getTestMethods() {
        return testMethods;
    }
}
