package com.legacy.analysis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

/**
 * Generates simple markdown documentation for the migrated codebase.
 *
 * <p>The documentation consists of one markdown file per class containing the
 * provided description and an index file that lists all generated documents.</p>
 */
public class DocumentationGenerator {

    /**
     * Generates markdown documentation files for the given classes.
     *
     * @param outputDirectory the directory where documentation files will be written.
     * @param classDescriptions a map where the key is the fully qualified class name
     *                          and the value is a short description of the class.
     * @throws IOException if an I/O error occurs while writing files.
     */
    public void generateDocumentation(String outputDirectory,
                                      Map<String, String> classDescriptions) throws IOException {
        Path outputDir = Path.of(outputDirectory);
        Files.createDirectories(outputDir);

        StringBuilder indexBuilder = new StringBuilder("# Migration Documentation Index\n\n");

        for (Map.Entry<String, String> entry : classDescriptions.entrySet()) {
            String className = entry.getKey();
            String description = entry.getValue();

            // Create a safe file name by replacing dots with underscores
            String fileName = className.replace('.', '_') + ".md";
            Path filePath = outputDir.resolve(fileName);

            StringBuilder content = new StringBuilder();
            content.append("# ").append(className).append("\n\n");
            content.append(description).append("\n");

            Files.writeString(filePath, content.toString(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.WRITE);

            indexBuilder.append("- [").append(className).append("](").append(fileName).append(")\n");
        }

        // Write the index file
        Path indexPath = outputDir.resolve("README.md");
        Files.writeString(indexPath, indexBuilder.toString(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE);
    }
}
