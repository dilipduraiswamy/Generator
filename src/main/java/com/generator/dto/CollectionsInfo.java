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
public class CollectionsInfo {

    @Schema(defaultValue = "collection name")
    private String collectionName;

    @Schema(defaultValue = "fields with name and type")
    private HashMap<String,Object> keys;

}
