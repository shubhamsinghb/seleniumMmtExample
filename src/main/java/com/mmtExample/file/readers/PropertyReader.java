package com.mmtExample.file.readers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader implements IReader{
    public static Properties properties= new Properties();
    @Override
    public void read() {
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
