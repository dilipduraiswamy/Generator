package com.generator.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Component
public class CreateControllerUtil {


    public boolean createController(String controllerName,String basepackage,String outputPath) throws IOException {
        if(!controllerName.isEmpty()) {

            List<String> lines =getDefaultControllerData(basepackage,controllerName);
            Files.write(Paths.get(outputPath+"/"+controllerName+".java"), lines,
                    StandardCharsets.UTF_8);
            return true;
        }
        else
            return false;
    }


    private List<String> getDefaultControllerData(String basepackage,String controllerName){
       return Arrays.asList("package "+basepackage+";",
                "",
                "import org.springframework.web.bind.annotation.GetMapping;",
                "import org.springframework.web.bind.annotation.RestController;",
                "",
                "@RestController",
                "public class "+controllerName+" {",
                "",
                "   @GetMapping(\"/\")",
                "   private String printDefault()",
                "    {",
                "        return \"Default Message.....\";",
                "    }",
                "}"

        );
    }
}
