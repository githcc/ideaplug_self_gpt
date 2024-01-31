package com.cc.plug.util.io;

import com.alibaba.fastjson2.JSON;
import com.cc.plug.data.D;
import com.cc.plug.entity.GlobalDataEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class PersistenceUtil {
    private static final String fileName = File.separator+"data.bin";
    public static void globalToFile(){
        removeFile();
        CompletableFuture.runAsync(() -> {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = JSON.toJSONString(D.globalDataEntity);
            File outputFile = new File(fileName);
            try {
                objectMapper.writeValue(outputFile, jsonString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public static GlobalDataEntity globalToObj(){
        if (isFileExists()){
            File inputFile = new File(fileName);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String jsonString = objectMapper.readTree(inputFile).toString();
                return objectMapper.readValue(jsonString, GlobalDataEntity.class);
            } catch (IOException e) {
                e.printStackTrace();
                return new GlobalDataEntity();
            }
        }
        return new GlobalDataEntity();
    }

    public static boolean isFileExists(){
        return Files.exists(Path.of(fileName));
    }

    public static void removeFile(){
        if (isFileExists()){
            new File(fileName).delete();
        }
    }
}
