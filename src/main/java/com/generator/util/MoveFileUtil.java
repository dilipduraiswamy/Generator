package com.generator.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class MoveFileUtil {

    public boolean moveFile(String from,String to){
        try {
            Path temp = Files.move
                    (Paths.get(from),
                            Paths.get(to));

            if(temp != null)
            {
                return true;
            }
            else
            {
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
