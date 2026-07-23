package com.legacy.analysis;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.utils.SourceRoot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * JavaParser analyzer for legacy code.
 */
public class JavaParserAnalyzer {
    private DependencyGraph dependencyGraph;
    private RiskGraph riskGraph;

    public JavaParserAnalyzer() {
        this.dependencyGraph = new DependencyGraph();
        this.riskGraph = new RiskGraph();
    }

    public void analyze(String filePath) throws FileNotFoundException {
        CompilationUnit cu = com.github.javaparser.JavaParser.parse(new File(filePath));
        cu.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(ClassOrInterfaceDeclaration n, Void arg) {
                super.visit(n, arg);
                System.out.println(n.getNameAsString());
                // Add risk node for each class
                riskGraph.addRiskNode(n.getNameAsString(), "high", "No test coverage");
                // Add dependencies for each class
                n.getMethods().forEach(method -> {
                    method.getNodes().forEach(node -> {
                        if (node instanceof MethodCallExpr) {
                            MethodCallExpr methodCallExpr = (MethodCallExpr) node;
                            String methodName = methodCallExpr.getNameAsString();
                            String className = methodCallExpr.getScope().map(Node::toString).orElse("");
                            dependencyGraph.addDependency(n.getNameAsString(), className);
                        }
                    });
                });
            }
        }, null);
    }

    public DependencyGraph getDependencyGraph() {
        return dependencyGraph;
    }

    public RiskGraph getRiskGraph() {
        return riskGraph;
    }
}
