//    HelloCommon are basic JavaFX utilities
//
//    Copyright (C) 2015-2019 Adrián Romero Corchado.
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

package com.adr.hellocommon.dialog;

import com.adr.fonticon.FontAwesome;
import com.adr.fonticon.IconBuilder;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author adrian
 */
public class DialogException  {

    private final static Logger logger = Logger.getLogger(DialogException.class.getName());

    private ResourceBundle resources;    
    private BorderPane rootpane;
    private Label textmessage;
    private TextArea textexception;
    
    public DialogException() {
        resources = ResourceBundle.getBundle("com/adr/hellocommon/fxml/dialogexception");
        
        rootpane = new BorderPane();
        
        textmessage = new Label();
        textmessage.getStyleClass().add("dialog-label");
        textmessage.setWrapText(true);
        BorderPane.setAlignment(textmessage, Pos.CENTER);
        BorderPane.setMargin(textmessage, new Insets(0.0, 0.0, 10.0, 0.0));
        
        rootpane.setTop(textmessage);
        
        textexception = new TextArea();
        textexception.setEditable(false);
        textexception.setMinHeight(0.0);
        textexception.setPrefSize(400.0, 200.0);
        BorderPane.setAlignment(textexception, Pos.CENTER);
        
        rootpane.setCenter(textexception);
        
        // initialize();
    }  
    
    public Node getNode() {
        return rootpane;
    }
    
    public void setMessage(String message) {
        textmessage.setText(message);
    }
    
    public void setException(Throwable t) {
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        textexception.setText(sw.toString());
    }
    
    public Button createButtonDetails() {
        Button b = new Button(resources.getString("button.details"));
        b.setOnAction((ActionEvent event) ->  {
            if (textexception.isVisible()) {
                hideDetails(b);
            } else {
                showDetails(b); 
            }                
        });
        ButtonBar.setButtonData(b, ButtonBar.ButtonData.LEFT);
        hideDetails(b);    
        return b;
    }
    
    private void showDetails(Button b) {
        textexception.setVisible(true);
        textexception.setPrefSize(400.0, 200.0);        
        b.setGraphic(IconBuilder.create(FontAwesome.FA_MINUS).styleClass("icon-fill").build());               
    }
    
    private void hideDetails(Button b) {
        textexception.setVisible(false);
        textexception.setPrefSize(0.0, 0.0);        
        b.setGraphic(IconBuilder.create(FontAwesome.FA_PLUS).styleClass("icon-fill").build());          
    }
}
