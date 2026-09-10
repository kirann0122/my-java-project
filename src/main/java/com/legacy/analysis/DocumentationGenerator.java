package com.legacy.analysis;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Generates simple documentation for a legacy codebase by extracting class,
 * field, and method signatures using JavaParser.
 *
 * The documentation is written to a plain‑text file (e.g., Markdown) at the
 * location specified by {@code outputFilePath}.
 */
public class DocumentationGenerator {

    private final StringBuilder documentationBuilder = new StringBuilder();

    /**
     * Generates documentation for all Java source files under {@code rootDirectory}
     * and writes the result to {@code outputFilePath}.
     *
     * @param rootDirectory   the directory containing the legacy source code
     * @param outputFilePath  the file path where the generated documentation will be saved
     * @throws FileNotFoundException if a source file cannot be read
     * @throws IOException           if writing the documentation fails
     */
    public void generateDocumentation(String rootDirectory, String outputFilePath)
            throws FileNotFoundException, IOException {
        documentationBuilder.setLength(0);
        traverseAndParse(new File(rootDirectory));
        Path outPath = Path.of(outputFilePath);
        Files.createDirectories(outPath.getParent());
        Files.writeString(outPath, documentationBuilder.toString());
    }

    private void traverseAndParse(File directory) throws FileNotFoundException {
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                traverseAndParse(file);
            } else if (file.getName().endsWith(".java")) {
                parseFile(file);
            }
        }
    }

    private void parseFile(File javaFile) throws FileNotFoundException {
        CompilationUnit cu;
        try {
            cu = JavaParser.parse(javaFile);
        } catch (ParseProblemException e) {
            // Skip files that cannot be parsed; they are likely not valid Java source.
            return;
        }

        cu.findAll(ClassOrInterfaceDeclaration.class).forEach(this::documentClass);
        cu.findAll(EnumDeclaration.class).forEach(this::documentEnum);
    }

    private void documentClass(ClassOrInterfaceDeclaration clazz) {
        documentationBuilder.append("## ").append(clazz.isInterface() ? "Interface" : "Class")
                .append(": ").append(clazz.getNameAsString()).append('\n');

        // Extends / Implements
        if (!clazz.getExtendedTypes().isEmpty()) {
            documentationBuilder.append("- Extends: ");
            documentationBuilder.append(String.join(", ",
                    clazz.getExtendedTypes().stream()
                            .map(t -> t.getNameAsString())
                            .toList()));
            documentationBuilder.append('\n');
        }
        if (!clazz.getImplementedTypes().isEmpty()) {
            documentationBuilder.append("- Implements: ");
            documentationBuilder.append(String.join(", ",
                    clazz.getImplementedTypes().stream()
                            .map(t -> t.getNameAsString())
                            .toList()));
            documentationBuilder.append('\n');
        }

        // Fields
        List<FieldDeclaration> fields = clazz.getFields();
        if (!fields.isEmpty()) {
            documentationBuilder.append("\n### Fields\n");
            for (FieldDeclaration field : fields) {
                String type = field.getElementType().asString();
                field.getVariables().forEach(var -> {
                    documentationBuilder.append("- ")
                            .append(type).append(' ')
                            .append(var.getNameAsString()).append('\n');
                });
            }
        }

        // Methods
        List<MethodDeclaration> methods = clazz.getMethods();
        if (!methods.isEmpty()) {
            documentationBuilder.append("\n### Methods\n");
            for (MethodDeclaration method : methods) {
                documentationBuilder.append("- ")
                        .append(method.getDeclarationAsString(false, false, true))
                        .append('\n');
            }
        }

        documentationBuilder.append("\n---\n\n");
    }

    private void documentEnum(EnumDeclaration enumDecl) {
        documentationBuilder.append("## Enum: ").append(enumDecl.getNameAsString()).append('\n');

        // Enum constants
        if (!enumDecl.getEntries().isEmpty()) {
            documentationBuilder.append("\n### Constants\n");
            enumDecl.getEntries().forEach(entry ->
                    documentationBuilder.append("- ").append(entry.getNameAsString()).append('\n'));
        }

        // Methods inside enum
        List<MethodDeclaration> methods = enumDecl.getMethods();
        if (!methods.isEmpty()) {
            documentationBuilder.append("\n### Methods\n");
            for (MethodDeclaration method : methods) {
                documentationBuilder.append("- ")
                        .append(method.getDeclarationAsString(false, false, true))
                        .append('\n');
            }
        }

        documentationBuilder.append("\n---\n\n");
    }

    /**
     * Returns the documentation generated so far as a string.
     *
     * @return the current documentation content
     */
    public String getDocumentation() {
        return documentationBuilder.toString();
    }
}
