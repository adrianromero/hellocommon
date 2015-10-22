//    HelloCommon are basic JavaFX utilities
//    Copyright (C) 2015 Adrián Romero Corchado.
//    All Rights reserved.

package com.adr.hellocommon.dialog;

import com.adr.fonticon.FontAwesome;
import com.adr.fonticon.IconBuilder;
import com.adr.fonticon.decorator.FillPaint;
import com.adr.fonticon.decorator.Shine;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author adrian
 */
public class MessageUtils {
    
    private MessageUtils() {
    }
    
    public static void showException(StackPane parent, String title, String message, Throwable t) {
        DialogView dialog = new DialogView();
        dialog.setTitle(title);
        DialogException contentex = new DialogException();
        contentex.setMessage(message);
        contentex.setException(t);   
        dialog.setContent(contentex);     
        dialog.setIndicator(IconBuilder.create(FontAwesome.FA_TIMES_CIRCLE, 48.0).apply(new FillPaint(Color.web("#FF9999"))).apply(new Shine(Color.RED)).build());
        dialog.addButtons(contentex.createButtonDetails(), dialog.createOKButton());
        dialog.show(parent);           
    }
    
    public static void showException(StackPane parent, String title, Throwable t) {
        showException(parent, title, t.getLocalizedMessage(), t);         
    }
    
    public static void showConfirm(StackPane parent, String title, String message, Consumer<ActionEvent> actionok) {
        DialogView dialog = new DialogView();
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setIndicator(IconBuilder.create(FontAwesome.FA_QUESTION_CIRCLE, 48.0).apply(new FillPaint(Color.web("#9999FF"))).apply(new Shine(Color.BLUE)).build());
        dialog.setActionOK(actionok);
        dialog.addButtons(dialog.createCancelButton(), dialog.createOKButton());
        dialog.show(parent);               
    }
    
    public static void showConfirm(StackPane parent, String title, Node content, Consumer<ActionEvent> actionok, Button... moreactions) {
        DialogView dialog = new DialogView();
        dialog.setTitle(title);
        dialog.setContent(content);
        dialog.setActionOK(actionok);
        dialog.addButtons(moreactions);
        dialog.addButtons(dialog.createCancelButton(), dialog.createOKButton());
        dialog.show(parent);           
    }  
    
    public static void showError(StackPane parent, String title, String message) {
        DialogView dialog = new DialogView();
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setIndicator(IconBuilder.create(FontAwesome.FA_TIMES_CIRCLE, 48.0).apply(new FillPaint(Color.web("#FF9999"))).apply(new Shine(Color.RED)).build());
        dialog.addButtons(dialog.createOKButton());
        dialog.show(parent);         
    }   
    
    public static void showWarning(StackPane parent, String title, String message) {
        DialogView dialog = new DialogView();
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setIndicator(IconBuilder.create(FontAwesome.FA_WARNING, 48.0).apply(new FillPaint(Color.web("#FFFF99"))).apply(new Shine(Color.YELLOW)).build());
        dialog.addButtons(dialog.createOKButton());        
        dialog.show(parent);      
    } 
    
    public static DialogView showSystemMessage(StackPane parent, String message) {
        DialogView dialog = new DialogView();
        dialog.setMessage(message);
        dialog.show(parent);  
        return dialog;
    }     
}
