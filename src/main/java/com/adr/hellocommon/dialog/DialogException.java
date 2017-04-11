//    HelloCommon are basic JavaFX utilities
//    Copyright (C) 2015-2017 Adrián Romero Corchado.
//    All Rights reserved..

package com.adr.hellocommon.dialog;

import com.adr.fonticon.FontAwesome;
import com.adr.fonticon.IconBuilder;
import com.adr.hellocommon.utils.FXMLUtil;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    @FXML private URL url;
    @FXML private ResourceBundle resources;    
    @FXML private BorderPane rootpane;
    @FXML private Label textmessage;
    @FXML private TextArea textexception;
    
    public DialogException() {
        FXMLUtil.load(this, "/com/adr/hellocommon/fxml/dialogexception.fxml", "com/adr/hellocommon/fxml/dialogexception");
    }  
    
    @FXML
    public void initialize() {
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
        b.setGraphic(IconBuilder.create(FontAwesome.FA_MINUS).build());               
    }
    
    private void hideDetails(Button b) {
        textexception.setVisible(false);
        textexception.setPrefSize(0.0, 0.0);        
        b.setGraphic(IconBuilder.create(FontAwesome.FA_PLUS).build());          
    }
}
