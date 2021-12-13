package com.generator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
class GeneratorApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void createFile() throws IOException {

		String projectName="Dilip-Test";
		String basePath="/Users/dilip.d/Documents/gen/";
		String filePath=basePath+projectName;
		Path path = Paths.get(filePath);
		if(!Files.exists(path))
		{
			Files.createDirectory(path);
		}
		System.out.println(Files.exists(path));
	}


}
