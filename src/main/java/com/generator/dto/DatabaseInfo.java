package com.generator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DatabaseInfo {

    @Schema(defaultValue = "mongo/mysql")
    private String dbName;

    @Schema(defaultValue = "collections data if db mongo")
    private List<CollectionsInfo> collectionsInfos;

    @Schema(defaultValue = "tables data if db mysql")
    private List<TablesInfo> tablesInfos;
}
