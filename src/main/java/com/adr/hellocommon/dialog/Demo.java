//    HelloCommon are basic JavaFX utilities
//
//    Copyright (C) 2018-2019 Adrián Romero Corchado.
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
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author adrian
 */
public class Demo extends Application {

    @Override
    public void start(Stage stage) {

        MessageUtils.useDefaultCSS();

        HBox flow = new HBox();
        flow.setSpacing(6.0);
        flow.setPadding(new Insets(6));

        flow.getChildren().addAll(
                showConfirm(),
                showInfo(),
                showWarning(),
                showError(),
                showException(),
                showLoading()
        );

        FlowPane flowdialogs = new FlowPane();
        flowdialogs.setVgap(6);
        flowdialogs.setHgap(6);
        flowdialogs.setPadding(new Insets(6));

        flowdialogs.getChildren().addAll(
                createBox(MessageUtils.createWarning("Title", "Warning message", null)),
                createBox(MessageUtils.createError("Title", "Error message", null)),
                createBox(MessageUtils.createConfirm("Title", "Confirm message", null)),
                createBox(MessageUtils.createInfo("Title", "Info message", null)),
                createBox(MessageUtils.createSystemMessage("System message")),
                createBox(MessageUtils.createException("Title", "Long exception message", new NullPointerException(), null)));

        VBox container = new VBox(flow, flowdialogs);
        StackPane root = new StackPane(container);
        MessageUtils.setDialogRoot(root, true);
        Scene scene = new Scene(root);

        stage.setWidth(800.0);
        stage.setHeight(600.0);
        stage.setTitle("HelloCommon Demo");
        stage.setScene(scene);
        stage.show();
    }

    private Node createBox(DialogView dialog) {
        dialog.setActionClose(ev -> ev.consume());

        StackPane container1 = new StackPane(dialog.getBodyDialog());
        container1.setPrefSize(250.0, 200.0);
        container1.setMinSize(250.0, 200.0);
        container1.setMaxSize(250.0, 200.0);
        return container1;
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
            loading.show(MessageUtils.getRoot(b));
            new Timeline(new KeyFrame(Duration.millis(10000.0), handler -> {
                loading.dispose();
            })).play();
        });
        return b;
    }

    private DialogView createLoading() {

        DialogView dialog = new DialogView();
        dialog.setMaster(true);
        dialog.setAnimate(false);
        dialog.setContent(createLoadingNode());
        return dialog;
    }

    private Node createLoadingNode() {

        Circle c0 = new Circle(65);
        c0.setFill(Color.TRANSPARENT);
        c0.setStrokeWidth(0.0);

        Circle c1 = new Circle(50);
        c1.setFill(Color.TRANSPARENT);
        c1.setStrokeType(StrokeType.INSIDE);
        c1.setStrokeLineCap(StrokeLineCap.ROUND);
        c1.setStrokeWidth(8.0);
        c1.setStroke(Color.BLUE);
        c1.getStrokeDashArray().addAll(78.54); // 50 * 2 * 3.1416 / 4
        c1.setCache(true);
        c1.setCacheHint(CacheHint.ROTATE);
        setRotate(c1, true, 440.0, 10);

        Circle c2 = new Circle(40);
        c2.setFill(Color.TRANSPARENT);
        c2.setStrokeType(StrokeType.INSIDE);
        c2.setStrokeLineCap(StrokeLineCap.ROUND);
        c2.setStrokeWidth(8.0);
        c2.setStroke(Color.BLUE);
        c2.getStrokeDashArray().addAll(41.89); // 40 * 2 * 3.1416 / 6
        c2.setCache(true);
        c2.setCacheHint(CacheHint.ROTATE);
        setRotate(c2, true, 360.0, 14);

        Circle c11 = new Circle(30);
        c11.setFill(Color.TRANSPARENT);
        c11.setStrokeType(StrokeType.INSIDE);
        c11.setStrokeLineCap(StrokeLineCap.BUTT);
        c11.setStrokeWidth(8.0);
        c11.setStroke(Color.BLUE);

        return new Group(c0, c11, c1, c2);
    }

    private void setRotate(Shape s, boolean reverse, double angle, int duration) {
        RotateTransition r = new RotateTransition(Duration.seconds(duration), s);
        r.setAutoReverse(reverse);
        r.setDelay(Duration.ZERO);
        r.setRate(3.0);
        r.setCycleCount(RotateTransition.INDEFINITE);
        r.setByAngle(angle);
        r.play();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
