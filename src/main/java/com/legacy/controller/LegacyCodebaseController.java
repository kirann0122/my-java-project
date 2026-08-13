package com.legacy.controller;

import com.legacy.analysis.CodeTransformer;
import com.legacy.migration.MigrationProgressTracker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LegacyCodebaseController {
    private CodeTransformer codeTransformer;

    public LegacyCodebaseController() {
        this.codeTransformer = new CodeTransformer();
    }

    @GetMapping("/migration-progress")
    public List<MigrationProgressTracker.MigrationProgress> getMigrationProgress() {
        return codeTransformer.getMigrationProgressTracker().getMigrationProgressList();
    }
}
