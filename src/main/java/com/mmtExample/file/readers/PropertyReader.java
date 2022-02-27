package com.mmtExample.file.readers;

import org.apache.commons.logging.Log;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader implements IReader{
    public static final Logger logger = LogManager.getLogger(PropertyReader.class);
    public static Properties properties= new Properties();
    @Override
    public void read() {
        logger.info("Reading Property file");
        String file= "src/main/resources/config.properties";
        try{
            FileReader reader= new FileReader(file);
            properties.load(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static String getProperty(String propertyKey){
        return properties.getProperty(propertyKey);
    }

    public static void main(String [] args){
        PropertyReader propertyReader= new PropertyReader();
        propertyReader.read();
        System.out.println(getProperty("browser"));
    }
}
