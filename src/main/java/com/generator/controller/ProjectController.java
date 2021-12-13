package com.generator.controller;

import com.generator.domain.DownloadSampleProject;
import com.generator.dto.ProjectInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private DownloadSampleProject downloadSampleProject;

    @GetMapping("/v1/execute/{projectName}")
    public String execute_v1(@PathVariable("projectName") String projectName) throws IOException {
        downloadSampleProject.download_v1(projectName);
        return projectName;
    }

    @PostMapping("/v2/execute")
    public String execute_v2(@RequestBody ProjectInfoRequest projectInfoRequest) throws IOException {
        downloadSampleProject.download_v2(projectInfoRequest);
        return projectInfoRequest.getProjectName();
    }
}
