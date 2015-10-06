//    HelloCommon are basic JavaFX utilities
//    Copyright (C) 2015 Adrián Romero Corchado.
//    All Rights reserved..

package com.adr.hellocommon.dialog;

import com.adr.fonticon.FontAwesome;
import com.adr.fonticon.IconBuilder;
import com.adr.hellocommon.utils.AbstractController;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author adrian
 */
public class DialogException extends BorderPane implements AbstractController  {

    private final static Logger logger = Logger.getLogger(DialogException.class.getName());

    @FXML private URL url;
    @FXML private ResourceBundle resources;    
    @FXML private Label textmessage;
    @FXML private TextArea textexception;
    
    public DialogException() {
        load("/com/adr/hellocommon/fxml/dialogexception.fxml", "com/adr/hellocommon/fxml/dialogexception");
    }  
    
    @FXML
    public void initialize() {
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
        Button b = new Button();
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
        textexception.setPrefHeight(200.0);        
        b.setText(resources.getString("button.hidedetails"));
        b.setGraphic(IconBuilder.create(FontAwesome.FA_CHEVRON_CIRCLE_UP).build());               
    }
    
    private void hideDetails(Button b) {
        textexception.setVisible(false);
        textexception.setPrefHeight(0.0);        
        b.setText(resources.getString("button.showdetails"));
        b.setGraphic(IconBuilder.create(FontAwesome.FA_CHEVRON_CIRCLE_DOWN).build());          
    }
}
