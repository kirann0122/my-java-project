package com.legacy.deployment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Service responsible for deploying the migrated codebase to a production environment.
 * This implementation performs a simple file copy to a target directory that represents
 * the production environment. In a real-world scenario this could be replaced with
 * more sophisticated deployment logic (e.g., Docker, Kubernetes, CI/CD pipelines).
 */
public class DeploymentService {

    /**
     * Deploys the given artifact (e.g., a JAR file) to the specified target environment.
     *
     * @param artifactPath absolute path to the built artifact (e.g., target/LegacyLift.jar)
     * @param targetEnvironment identifier of the target environment (e.g., "prod", "staging")
     * @return true if deployment succeeded, false otherwise
     */
    public boolean deploy(String artifactPath, String targetEnvironment) {
        File artifact = new File(artifactPath);
        if (!artifact.exists() || !artifact.isFile()) {
            System.err.println("Deployment failed: artifact not found at " + artifactPath);
            return false;
        }

        // Determine the deployment directory based on the environment.
        // For demonstration purposes we map environments to directories under /opt/legacylift.
        String deploymentDirPath = "/opt/legacylift/" + targetEnvironment;
        File deploymentDir = new File(deploymentDirPath);
        if (!deploymentDir.exists()) {
            if (!deploymentDir.mkdirs()) {
                System.err.println("Deployment failed: could not create deployment directory " + deploymentDirPath);
                return false;
            }
        }

        File destination = new File(deploymentDir, artifact.getName());
        try {
            Files.copy(artifact.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Successfully deployed " + artifact.getName() + " to " + deploymentDirPath);
            return true;
        } catch (IOException e) {
            System.err.println("Deployment failed: " + e.getMessage());
            return false;
        }
    }
}
