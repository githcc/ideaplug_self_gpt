package com.cc.plug.util.io;

import com.cc.plug.action.SelectAction;
import com.cc.plug.data.D;
import com.cc.plug.entity.GlobalDataEntity;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

import static com.cc.plug.data.F.PERSISTENCE_FILE_NAME;

public class PersistenceUtil {
    private static final String fileName = PERSISTENCE_FILE_NAME;
    public static void globalToFile(){
        removeFile();
        CompletableFuture.runAsync(() -> {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(D.globalDataEntity);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static GlobalDataEntity globalToObj(){
        if (isFileExists()){
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                GlobalDataEntity globalDataEntityFile = (GlobalDataEntity) ois.readObject();
                SelectAction.selectAction.setText(globalDataEntityFile.getPromptsCheck());
                return copyGlobalDataEntity(globalDataEntityFile);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new GlobalDataEntity();
    }

    private static boolean isFileExists(){
        return Files.exists(Path.of(fileName));
    }

    private static void removeFile(){
        if (isFileExists()){
            new File(fileName).delete();
        }
    }

    private static GlobalDataEntity copyGlobalDataEntity(GlobalDataEntity globalDataEntity){
        GlobalDataEntity dataEntity = new GlobalDataEntity();
        dataEntity.setPromptsCheck(globalDataEntity.getPromptsCheck());
        dataEntity.setKey(globalDataEntity.getKey());
        dataEntity.setSharePrompts(globalDataEntity.isSharePrompts());
        dataEntity.setShareConversations(globalDataEntity.isShareConversations());

        dataEntity.setPromptsList(globalDataEntity.getPromptsList());
        dataEntity.setPromptsListBak(globalDataEntity.getPromptsListBak());
        dataEntity.setPromptsCheck(globalDataEntity.getPromptsCheck());
        return dataEntity;
    }
}
