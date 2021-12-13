package com.generator.controller;

import com.generator.domain.DownloadSampleProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ProjectController {

    @Autowired
    private DownloadSampleProject downloadSampleProject;

    @GetMapping("/execute/{projectName}")
    public String execute(@PathVariable("projectName") String projectName) throws IOException {
        downloadSampleProject.download(projectName);
        return projectName;
    }
}
