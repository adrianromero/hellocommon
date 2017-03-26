//    HelloCommon are basic JavaFX utilities
//    Copyright (C) 2015-2017 Adrián Romero Corchado.
//    All Rights reserved.

package com.adr.hellocommon.utils;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author adrian
 */
public class FXMLUtil {
    
    public static final <T> T load(Object controller, String fxml) {  
        FXMLLoader loader = new FXMLLoader(controller.getClass().getResource(fxml));
        loader.setController(controller);
        
        try {
            return loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        } 
    }   
    
    public static final <T> T load(Object controller, String fxml, String resources) {        
        FXMLLoader loader = new FXMLLoader(controller.getClass().getResource(fxml));
        loader.setResources(ResourceBundle.getBundle(resources));
        loader.setController(controller);
        
        try {
            return loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }      
    }  
}
