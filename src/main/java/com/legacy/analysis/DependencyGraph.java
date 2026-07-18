package com.legacy.analysis;

import java.util.*;

/**
 * Represents a graph of code dependencies.
 */
public class DependencyGraph {
    private Map<String, List<String>> dependencies;

    public DependencyGraph() {
        this.dependencies = new HashMap<>();
    }

    /**
     * Adds a dependency to the graph.
     * 
     * @param className the class that depends on another class
     * @param dependency the class that is depended on
     */
    public void addDependency(String className, String dependency) {
        dependencies.computeIfAbsent(className, k -> new ArrayList<>()).add(dependency);
    }

    /**
     * Gets the dependencies for a given class.
     * 
     * @param className the class to get dependencies for
     * @return a list of dependencies for the class
     */
    public List<String> getDependencies(String className) {
        return dependencies.getOrDefault(className, Collections.emptyList());
    }

    /**
     * Removes a dependency from the graph.
     * 
     * @param className the class that depends on another class
     * @param dependency the class that is depended on
     */
    public void removeDependency(String className, String dependency) {
        dependencies.computeIfPresent(className, (k, v) -> {
            v.remove(dependency);
            return v.isEmpty() ? null : v;
        });
    }
}
