package com.generator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectInfoRequest {

    @Schema(defaultValue = "Name With Space / Without space")
    private String projectName;

    @Schema(defaultValue = "Project Description")
    private String description;

    @Schema(defaultValue = "Spring Boot Version")
    private String bootVersion;

    @Schema(defaultValue = "Maven/Gradle")
    private String typeOfProject;

    @Schema(defaultValue = "Java")
    private String language;

    @Schema(defaultValue = "GroupId")
    private String groupId;

    @Schema(defaultValue = "ArtifactId")
    private String artifactId;

    @Schema(defaultValue = "war/jar")
    private String packaging;

    @Schema(defaultValue = "Java Version")
    private String javaVersion;
}
