package com.mmtExample.file.manager;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;

public class DirectoryManager {
    private static final Logger logger= LogManager.getLogger(DirectoryManager.class);

    public static void clearDirectory(String... directoryNames){
        logger.info("Deleting directory before the run");
        for (String directory : directoryNames){
            File file = new File(directory);
            removeFiles(file);
        }

    }

    private static void removeFiles(File file){
        File[] files = file.listFiles();

        if(files!=null){
            for(File fileName : files){
                if(fileName.isDirectory()){
                    removeFiles(fileName);
                }
                fileName.delete();
            }
            }
        file.delete();
        }
}
