package com.generator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TablesInfo {

    @Schema(defaultValue = "table name")
    private String tableName;

    @Schema(defaultValue = "key and value with field name and type")
    private HashMap<String,Object> keys;

}
