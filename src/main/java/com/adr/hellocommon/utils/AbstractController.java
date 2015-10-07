//    HelloCommon are basic JavaFX utilities
//    Copyright (C) 2015 Adrián Romero Corchado.
//    All Rights reserved.

package com.adr.hellocommon.utils;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author adrian
 */
public interface AbstractController {
    
    public default void load(String fxml) {  
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        loader.setController(this);
        loader.setRoot(this);
        
        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        } 
    }   
    
    public default void load(String fxml, String resources) {        
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        loader.setResources(ResourceBundle.getBundle(resources));
        loader.setController(this);
        loader.setRoot(this);
        
        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }      
    }  
}
