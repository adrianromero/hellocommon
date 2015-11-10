//    HelloCommon are basic JavaFX utilities
//    Copyright (C) 2015 Adrián Romero Corchado.
//    All Rights reserved.

package com.adr.hellocommon.dialog;

import com.adr.fonticon.FontAwesome;
import com.adr.fonticon.IconBuilder;
import com.adr.hellocommon.utils.AbstractController;
import com.adr.hellocommon.utils.ShowAnimation;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author adrian
 */
public class DialogView extends StackPane implements AbstractController {

    private final static Logger logger = Logger.getLogger(DialogView.class.getName());

    @FXML private URL url;
    @FXML private ResourceBundle resources;  
    @FXML private BorderPane header;
    @FXML private BorderPane bodydialog;
    @FXML private Button closebutton;
    @FXML private Label nodetitle;
    @FXML private StackPane nodecontent;
    @FXML private Label nodeindicator;
    @FXML private ButtonBar nodebuttons;
    
    private Consumer<ActionEvent> actionok = null;    
    private StackPane parent;
    
    public DialogView() {
        load("/com/adr/hellocommon/fxml/dialogview.fxml", "com/adr/hellocommon/fxml/dialogview");
        setBackground(new Background(new BackgroundFill(Color.gray(0.5, 0.75), CornerRadii.EMPTY, Insets.EMPTY)));
        header.setVisible(false);
    }
    
    @FXML
    public void initialize() {
        closebutton.setGraphic(IconBuilder.create(FontAwesome.FA_CLOSE).color(Color.BLACK).build());
    }    
    
    public void setTitle(String title) {
        header.setVisible(true);
        nodetitle.setText(title);
    }
    
    public void setCloseButtonVisible(boolean visible) {
        closebutton.setVisible(visible);
    }
    
    public void setMessage(String message) {
        nodecontent.getChildren().add(new Label(message));
    }
    
    public void setContent(Node node) {
        nodecontent.getChildren().add(node);       
    }
    
    public void setIndicator(Node indicator) {
        nodeindicator.setGraphic(indicator);
    }
    
    public void setActionOK(Consumer<ActionEvent> actionok) {
        this.actionok = actionok;
    }
    
    public void addButtons(Button... buttons) {
        nodebuttons.getButtons().addAll(buttons);
    }
    
    public Button createCancelButton() {
        Button cancel = new Button (resources.getString("button.Cancel"));
        cancel.setOnAction((ActionEvent event) -> {
            dispose();
        });
        ButtonBar.setButtonData(cancel, ButtonBar.ButtonData.CANCEL_CLOSE);   
        return cancel;
    }
    
    public Button createOKButton() {
        Button ok = new Button (resources.getString("button.OK"));
        ok.setOnAction((ActionEvent event) -> {
            doOK();
        });
        ok.setDefaultButton(true);
        ButtonBar.setButtonData(ok, ButtonBar.ButtonData.OK_DONE);   
        return ok;
    }
    
    public void show(StackPane parent) {

        this.parent = parent;
        ObservableList<Node> children = parent.getChildren();
        if (!children.isEmpty()) {
            children.get(children.size() - 1).setDisable(true);
        }
        parent.getChildren().add(this);
        
        ShowAnimation.createAnimationFade(this).playFromStart();
        ShowAnimation.createAnimationCurtain(bodydialog).playFromStart();
    }
    
    public void dispose() {
        ObservableList<Node> children = parent.getChildren();
        if (children.isEmpty() || children.get(children.size() - 1) != this) {
            throw new RuntimeException("Cannot dispose a DialogView that is not the last node in an Stack Pane.");
        }
        children.remove(this);
        if (!children.isEmpty()) {
            children.get(children.size() - 1).setDisable(false);
        }
        parent = null;            
    }
    
    public void doOK() {
        if (actionok == null) {
            dispose();
        } else {
            ActionEvent okevent = new ActionEvent();
            actionok.accept(okevent);
            if (!okevent.isConsumed()) {
                dispose();
            }
        }        
    }
    
    @FXML void onClose(ActionEvent event) {
        dispose();
    }
}
