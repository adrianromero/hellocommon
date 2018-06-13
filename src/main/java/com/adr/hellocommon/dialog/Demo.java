//    HelloCommon are basic JavaFX utilities
//
//    Copyright (C) 2018 Adrián Romero Corchado.
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

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author adrian
 */
public class Demo extends Application {

    @Override
    public void start(Stage stage) {

        FlowPane flow = new FlowPane();
        flow.setVgap(6);
        flow.setHgap(6);
        flow.setPadding(new Insets(6));

        flow.getChildren().addAll(
                showConfirm(),
                showInfo(),
                showWarning(),
                showError(),
                showException(),
                showLoading()
        );

        StackPane root = new StackPane();
        root.getStyleClass().add("maincontainer");
        root.getChildren().add(flow);

        MessageUtils.useDefaultCSS();
        MessageUtils.setDialogRoot(root, true);
        Scene scene = new Scene(root);

        stage.setWidth(800.0);
        stage.setHeight(600.0);
        stage.setTitle("HelloCommon Demo");
        stage.setScene(scene);
        stage.show();
    }

    private Button showConfirm() {
        Button b = new Button("showConfirm");
        b.setOnAction(ev -> {
            MessageUtils.showConfirm(MessageUtils.getRoot(b), "Title", "Confirmation message", action -> {
            });
        });
        return b;
    }

    private Button showError() {
        Button b = new Button("showError");
        b.setOnAction(ev -> {
            MessageUtils.showError(MessageUtils.getRoot(b), "Title", "Error message.");
        });
        return b;
    }

    private Button showWarning() {
        Button b = new Button("showWarning");
        b.setOnAction(ev -> {
            MessageUtils.showWarning(MessageUtils.getRoot(b), "Title", "Warning message.");
        });
        return b;
    }

    private Button showInfo() {
        Button b = new Button("showInfo");
        b.setOnAction(ev -> {
            MessageUtils.showInfo(MessageUtils.getRoot(b), "Title", "Information message.");
        });
        return b;
    }

    private Button showException() {
        Button b = new Button("showException");
        b.setOnAction(ev -> {
            MessageUtils.showException(MessageUtils.getRoot(b), "Title", new NullPointerException("Long exception message"));
        });
        return b;
    }
    
    private Button showLoading() {
        Button b = new Button("showLoading");
        b.setOnAction(ev -> {
            DialogView loading = createLoading();
            loading.setCSS("/com/adr/hellocommon/style/dialog.css");
            loading.setCSS("/com/adr/hellocommon/style/demo.css");
            loading.show(MessageUtils.getRoot(b), false);
            new Timeline(new KeyFrame(Duration.millis(10000.0), handler -> {
                loading.dispose();
            })).play();
        });
        return b;
    }    
    
    private DialogView createLoading() {

        ProgressBar p = new ProgressBar();
        p.getStyleClass().add("loading-bar");

        DialogView dialog = new DialogView();
        dialog.setMaster(true);
        dialog.setContent(p);
        return dialog;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
