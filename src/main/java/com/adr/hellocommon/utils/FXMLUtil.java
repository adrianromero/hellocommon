//    HelloCommon are basic JavaFX utilities
//
//    Copyright (C) 2015-2018 Adrián Romero Corchado.
//
//    Licensed to the Apache Software Foundation (ASF) under one
//    or more contributor license agreements.  See the NOTICE file
//    distributed with this work for additional information
//    regarding copyright ownership.  The ASF licenses this file
//    to you under the Apache License, Version 2.0 (the
//    "License"); you may not use this file except in compliance
//    with the License.  You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
//    Unless required by applicable law or agreed to in writing,
//    software distributed under the License is distributed on an
//    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
//    KIND, either express or implied.  See the License for the
//    specific language governing permissions and limitations
//    under the License.

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
