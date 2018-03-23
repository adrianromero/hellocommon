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
    
    private static String defaultcss = null;
    
    private MessageUtils() {
    }
    
    public static void useDefaultCSS() {
        defaultcss = "/com/adr/hellocommon/style/dialog.css";
    }
    
    public static void showException(StackPane parent, String title, String message, Throwable t, Consumer<ActionEvent> actiondispose) {
        DialogView dialog = new DialogView();
        dialog.setCSS(defaultcss);
        dialog.setTitle(title);
        DialogException contentex = new DialogException();
        contentex.setMessage(message);
        contentex.setException(t);   
        dialog.setContent(contentex.getNode());     
        dialog.setIndicator(IconBuilder.create(FontAwesome.FA_TIMES_CIRCLE, 48.0).apply(new FillPaint(Color.web("#FF9999"))).apply(new Shine(Color.RED)).build());
        dialog.setActionDispose(actiondispose);
        dialog.addButtons(contentex.createButtonDetails(), dialog.createOKButton());
        dialog.show(parent);           
    }
    
    public static void showException(StackPane parent, String title, String message, Throwable t) {
        showException(parent, title, message, t, null);       
    }
    
    public static void showException(StackPane parent, String title, Throwable t, Consumer<ActionEvent> actiondispose) {
        showException(parent, title, t.getLocalizedMessage(), t, actiondispose);         
    }
    
    public static void showException(StackPane parent, String title, Throwable t) {
        showException(parent, title, t.getLocalizedMessage(), t, null);         
    }
    
    public static void showConfirm(StackPane parent, String title, String message, Consumer<ActionEvent> actionok) {
        DialogView dialog = new DialogView();
        dialog.setCSS(defaultcss);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setIndicator(IconBuilder.create(FontAwesome.FA_QUESTION_CIRCLE, 48.0).apply(new FillPaint(Color.web("#9999FF"))).apply(new Shine(Color.BLUE)).build());
        dialog.setActionOK(actionok);
        dialog.addButtons(dialog.createCancelButton(), dialog.createOKButton());
        dialog.show(parent);               
    }
    
    public static void showConfirm(StackPane parent, String title, Node content, Consumer<ActionEvent> actionok, Button... moreactions) {
        DialogView dialog = new DialogView();
        dialog.setCSS(defaultcss);
        dialog.setTitle(title);
        dialog.setContent(content);
        dialog.setActionOK(actionok);
        dialog.addButtons(moreactions);
        dialog.addButtons(dialog.createCancelButton(), dialog.createOKButton());
        dialog.show(parent);           
    }  
       
    public static void showError(StackPane parent, String title, String message, Consumer<ActionEvent> actiondispose) {
        DialogView dialog = new DialogView();
        dialog.setCSS(defaultcss);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setIndicator(IconBuilder.create(FontAwesome.FA_TIMES_CIRCLE, 48.0).apply(new FillPaint(Color.web("#FF9999"))).apply(new Shine(Color.RED)).build());
        dialog.addButtons(dialog.createOKButton());
        dialog.setActionDispose(actiondispose);
        dialog.show(parent);         
    }   
    
    public static void showError(StackPane parent, String title, String message) {
        showError(parent, title, message, null);       
    }     
    
    public static void showWarning(StackPane parent, String title, String message, Consumer<ActionEvent> actiondispose) {
        DialogView dialog = new DialogView();
        dialog.setCSS(defaultcss);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setIndicator(IconBuilder.create(FontAwesome.FA_WARNING, 48.0).apply(new FillPaint(Color.web("#FFFF99"))).apply(new Shine(Color.YELLOW)).build());
        dialog.addButtons(dialog.createOKButton());    
        dialog.setActionDispose(actiondispose);
        dialog.show(parent);      
    } 
    
    public static void showWarning(StackPane parent, String title, String message) {
        showWarning(parent, title, message, null);    
    } 
    
    public static void showInfo(StackPane parent, String title, String message, Consumer<ActionEvent> actiondispose) {
        DialogView dialog = new DialogView();
        dialog.setCSS(defaultcss);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setIndicator(IconBuilder.create(FontAwesome.FA_INFO_CIRCLE, 48.0).apply(new FillPaint(Color.web("#9999FF"))).apply(new Shine(Color.BLUE)).build());
        dialog.addButtons(dialog.createOKButton());  
        dialog.setActionDispose(actiondispose);
        dialog.show(parent);      
    } 
    
    public static void showInfo(StackPane parent, String title, String message) {
        showInfo(parent, title, message, null);    
    } 
    
    public static DialogView showSystemMessage(StackPane parent, String message) {
        DialogView dialog = new DialogView();
        dialog.setCSS(defaultcss);
        dialog.setMessage(message);
        dialog.show(parent);  
        return dialog;
    }   
    
    public static void setDialogRoot(StackPane parent, boolean value) {
        if (value) {
            parent.getProperties().put("DialogRoot", Boolean.TRUE);
        } else {
            parent.getProperties().remove("DialogRoot");
        }
    }

    public static boolean isDialogRoot(StackPane parent) {
        if (parent.hasProperties()) {
            Boolean value = (Boolean) parent.getProperties().get("DialogRoot");
            if (value != null) {
                return value;
            }
        }
        return false;
    } 
    
    public static StackPane getRoot(Node n) {
        if (n instanceof StackPane && MessageUtils.isDialogRoot((StackPane) n)) {
            return (StackPane) n;
        } else if (n.getParent() != null) {
            return MessageUtils.getRoot(n.getParent());
        } else {
            return null;
        }         
    }
    
    public static void disposeAllDialogs(StackPane parent) {
        
        if (parent.getChildren().size() > 0) {
            Node n = parent.getChildren().get(parent.getChildren().size() - 1);
            DialogView dialog = (DialogView) n.getProperties().get("DIALOG_VIEW");
            if (dialog != null && !dialog.isMaster()) {
                dialog.dispose();
                disposeAllDialogs(parent);
            }
        }
    }
}
