//    HelloCommon are basic JavaFX utilities
//    Copyright (C) 2015-2017 Adrián Romero Corchado.
//    All Rights reserved.

package com.adr.hellocommon.dialog;

import com.adr.fonticon.FontAwesome;
import com.adr.fonticon.IconBuilder;
import com.adr.hellocommon.utils.FXMLUtil;
import com.adr.hellocommon.utils.ShowAnimation;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
public class DialogView {

    private final static Logger logger = Logger.getLogger(DialogView.class.getName());

    @FXML private URL url;
    @FXML private ResourceBundle resources;  
    
    @FXML private StackPane rootpane;
    @FXML private BorderPane header;
    @FXML private BorderPane bodydialog;
    @FXML private Button closebutton;
    @FXML private Label nodetitle;
    @FXML private StackPane nodecontent;
    
    private ButtonBar nodebuttons = null;
    private Label nodeindicator = null;
    private boolean master = false;
    
    private Consumer<ActionEvent> actionok = null;    
    private Consumer<ActionEvent> actiondispose = null;    
    private StackPane parent;
    
    public DialogView() {
        FXMLUtil.load(this, "/com/adr/hellocommon/fxml/dialogview.fxml", "com/adr/hellocommon/fxml/dialogview");
    }
    
    @FXML
    public void initialize() {
        rootpane.getProperties().put("DIALOG_VIEW", this);
        rootpane.setBackground(new Background(new BackgroundFill(Color.gray(0.5, 0.75), CornerRadii.EMPTY, Insets.EMPTY)));
        closebutton.setGraphic(IconBuilder.create(FontAwesome.FA_CLOSE).color(Color.BLACK).build());
        header.setVisible(false);        
    }    
    
    public Node getNode() {
        return rootpane;
    }
    
    public void setCSS(String css) {
        if (css != null) {
            rootpane.getStylesheets().add(getClass().getResource(css).toExternalForm());
        }
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
        
        if (nodeindicator == null) {
            nodeindicator = new Label();
            nodeindicator.getStyleClass().add("dialog-indicator");
            BorderPane.setAlignment(nodeindicator, Pos.TOP_CENTER);
            bodydialog.setLeft(nodeindicator);
        }
        nodeindicator.setGraphic(indicator);
    }
    
    public void setActionOK(Consumer<ActionEvent> actionok) {
        this.actionok = actionok;
    }  
    
    public void setActionDispose(Consumer<ActionEvent> actiondispose) {
        this.actiondispose = actiondispose;
    }

    public boolean isMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master = master;
    }
    
    public void addButtons(Button... buttons) {
        
        if (nodebuttons == null) {
            nodebuttons = new ButtonBar();
            nodebuttons.getStyleClass().add("dialog-buttonlist");
            BorderPane.setAlignment(nodebuttons, Pos.CENTER);
            bodydialog.setBottom(nodebuttons);            
        }
        nodebuttons.getButtons().addAll(buttons);
    }
    
    public Button createCloselButton() {
        Button cancel = new Button (resources.getString("button.Close"));
        cancel.setOnAction((ActionEvent event) -> {
            dispose();
        });
        ButtonBar.setButtonData(cancel, ButtonBar.ButtonData.CANCEL_CLOSE);   
        return cancel;
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
        parent.getChildren().add(rootpane);
        
        ShowAnimation.createAnimationFade(rootpane).playFromStart();
        ShowAnimation.createAnimationCurtain(bodydialog).playFromStart();
    }
    
    public void dispose() {
        
        if (actiondispose != null) {
            ActionEvent disposeevent = new ActionEvent();
            actiondispose.accept(disposeevent);
            if (disposeevent.isConsumed()) {
                return;
            }
        }
        
        ObservableList<Node> children = parent.getChildren();
        children.remove(rootpane);
        if (!children.isEmpty()) {
            children.get(children.size() - 1).setDisable(false);
        }
        parent = null;            
    }
    
    public boolean isShowing() {
        return parent != null;
    }
    
    public void doOK() {
        
        if (actionok != null) {
            ActionEvent okevent = new ActionEvent();
            actionok.accept(okevent);
            if (okevent.isConsumed()) {
                return;
            }
        }
        
        dispose();        
    }
    
    @FXML void onClose(ActionEvent event) {
        dispose();
    }
}
