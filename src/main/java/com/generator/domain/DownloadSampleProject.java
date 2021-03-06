package com.generator.domain;

import com.generator.dto.ProjectInfoRequest;
import com.generator.util.CreateControllerUtil;
import com.generator.util.MoveFileUtil;
import com.generator.util.UnZipFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class DownloadSampleProject {

    @Value("${spring.initializer.base.url}")
    private String baseUrl;

    @Value("${generator.base.file.path}")
    private String baseFilePath;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UnZipFileUtil unZipper;

    @Autowired
    MoveFileUtil moveFileUtil;

    @Autowired
    CreateControllerUtil createControllerUtil;

    public String download_v2(ProjectInfoRequest projectInfoRequest) throws IOException {
        String finalUri=baseUrl;
        finalUri=addParameterToUrl("type",projectInfoRequest.getTypeOfProject(),finalUri);
        finalUri=addParameterToUrl("language",projectInfoRequest.getLanguage(),finalUri);
        finalUri=addParameterToUrl("bootVersion",projectInfoRequest.getBootVersion(),finalUri);
        finalUri=addParameterToUrl("baseDir",getBaseDirName(projectInfoRequest.getProjectName()),finalUri);
        finalUri=addParameterToUrl("groupId",projectInfoRequest.getGroupId(),finalUri);
        finalUri=addParameterToUrl("name",projectInfoRequest.getProjectName(),finalUri);
        finalUri=addParameterToUrl("packaging",projectInfoRequest.getPackaging(),finalUri);
        finalUri=addParameterToUrl("artifactId",projectInfoRequest.getArtifactId(),finalUri);
        finalUri=addParameterToUrl("javaVersion",projectInfoRequest.getJavaVersion(),finalUri);
        finalUri=addParameterToUrl("dependencies","devtools,web",finalUri);
        finalUri=addParameterToUrl("packageName","com.example",finalUri);
        System.out.println(finalUri);
        String projectName=getGeneratedProject(projectInfoRequest.getProjectName(), finalUri);

        File file = new File(projectName+"/src/main/java/com/example/controller");
        boolean dirCreated = file.mkdir();

        createControllerUtil.createController("DefaultController",projectInfoRequest.getGroupId()+".controller",projectName+"/src/main/java/com/example/controller");
        return "";
    }

    public String download_v1(String projectName) throws IOException {
        String type="maven-project";
        String language="java";
        String bootVersion="2.6.1";
        String baseDir=getBaseDirName(projectName);
        String groupId="com.example";
        String artifactId=projectName;
        String name=projectName;
        String packaging="jar";
        String javaVersion="11";
        String finalUri=baseUrl;
        finalUri=addParameterToUrl("type",type,finalUri);
        finalUri=addParameterToUrl("language",language,finalUri);
        finalUri=addParameterToUrl("bootVersion",bootVersion,finalUri);
        finalUri=addParameterToUrl("baseDir",baseDir,finalUri);
        finalUri=addParameterToUrl("groupId",groupId,finalUri);
        finalUri=addParameterToUrl("name",name,finalUri);
        finalUri=addParameterToUrl("packaging",packaging,finalUri);
        finalUri=addParameterToUrl("artifactId",artifactId,finalUri);
        finalUri=addParameterToUrl("javaVersion",javaVersion,finalUri);

        return getGeneratedProject(projectName, finalUri);
    }

    private String getGeneratedProject(String projectName, String finalUri) throws IOException {
        //download temp zip file from spring initializer
        File file = restTemplate.execute(finalUri, HttpMethod.GET, null, clientHttpResponse -> {
            File ret = File.createTempFile(projectName, ".zip");
            StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(ret));
            return ret;
        });

        //move temp zip file to specified folder
        boolean isMoved=moveFileUtil.moveFile(file.getAbsolutePath(),baseFilePath+"/"+ projectName +".zip");

        if(isMoved){
            //unzip the moved zip file
            unZipper.unzipFolder(baseFilePath+"/"+ projectName +".zip",baseFilePath+"/");

            //delete the zip file to clean up memory since we already extracted zip file
            Files.delete(Paths.get(baseFilePath+"/"+ projectName +".zip"));
        }
        return baseFilePath+"/"+projectName;
    }

    public String addParameterToUrl(String key,String value,String uri)
    {
        String resultUrl="";
        if(uri.contains("?"))
        {
            resultUrl=uri.concat("&"+key+"="+value);
        }else {
            resultUrl=uri.concat("?"+key+"="+value);
        }
        return resultUrl;
    }

    
    public String getBaseDirName(String projectName){
        String resultProjectName=projectName;
        if(projectName.contains(" "))
        {
            String finalStr="";
            String[] splittedSpaceNames=projectName.split(" ");
            for (String splitName:splittedSpaceNames) {
                    char splitChars[]=splitName.toCharArray();
                    for(int i=0;i<splitChars.length;i++)
                    {
                        if(i==0)
                            finalStr=finalStr.concat(String.valueOf(splitChars[i]).toUpperCase());
                        else
                            finalStr=finalStr.concat(String.valueOf(splitChars[i]).toLowerCase());
                    }

            }
            return finalStr;
        }
        return resultProjectName;
    }





}
