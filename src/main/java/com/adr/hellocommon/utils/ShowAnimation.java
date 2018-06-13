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

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 *
 * @author adrian
 */
public class ShowAnimation {
 
    private final Node node;
    private final BooleanProperty displayed;
    private final Animation animation;
    
    public ShowAnimation(Node node, Animation animation) {
        this.node = node;
        this.animation = animation;
        this.animation.setOnFinished(this::animationFinished);
        
        this.displayed = new SimpleBooleanProperty(node.isVisible());
        this.displayed.addListener(this::changeDisplayed);                
    }
    
    public static ShowAnimation createShowFade(Node n) {
        return new ShowAnimation(n, createAnimationFade(n));       
    }
      
    public static ShowAnimation createShowCurtain(Node n) {
        return new ShowAnimation(n, createAnimationCurtain(n));       
    }
    
    public static ShowAnimation createShowNotification(Node n) {
        return new ShowAnimation(n, createAnimationNotification(n));       
    }
      
    private void animationFinished(ActionEvent event) {
        if (animation.getCurrentTime().equals(Duration.ZERO)) {
            node.setVisible(false);
        }
    }
    
    private void changeDisplayed(ObservableValue<? extends Boolean> ov, Boolean oldvalue, Boolean newvalue) {
        if (newvalue) {
            animation.setRate(1.0);
            if (animation.getStatus() != Animation.Status.RUNNING) {
                animation.setRate(1.0);
                node.setVisible(true);
                animation.playFromStart();
            }
        } else {
            animation.setRate(-1.0);
            if (animation.getStatus() != Animation.Status.RUNNING) {
                node.setVisible(true);
                animation.playFrom(animation.getCycleDuration());
            }            
        }      
    }
    
    public BooleanProperty displayedProperty() {
        return displayed;
    }
    
    public boolean isDisplayed() {
        return displayed.get();
    }
    
    public void setDisplayed(boolean value) {
        displayed.set(value);
    }

    public static Animation createAnimationNotification(Node n) {
        TranslateTransition s1 = new TranslateTransition(Duration.millis(300), n);
        s1.setInterpolator(Interpolator.EASE_OUT);
        s1.setFromY(100.0);
        s1.setToY(0.0);   
        
        FadeTransition s2 = new FadeTransition(Duration.millis(300), n);
        s2.setInterpolator(Interpolator.EASE_BOTH);
        s2.setFromValue(0.0);
        s2.setToValue(1.0);
        
        return new ParallelTransition(s1, s2);
    }
    
    public static Animation createAnimationFade(Node n) {

        FadeTransition s2 = new FadeTransition(Duration.millis(300), n);
        s2.setInterpolator(Interpolator.EASE_BOTH);
        s2.setFromValue(0.0);
        s2.setToValue(1.0);
        return s2;
    }    
    
    public static Animation createAnimationScale(Node n) {

        n.setCache(true);
        n.setCacheHint(CacheHint.SCALE);
        
        ScaleTransition s3 = new ScaleTransition(Duration.millis(300), n);
        s3.setInterpolator(Interpolator.EASE_OUT);
        s3.setFromX(0.4);
        s3.setFromY(0.4);
        s3.setToX(1.0);
        s3.setToY(1.0);
        
        return new ParallelTransition(s3);
    }  
    
    public static Animation createAnimationCurtain(Node n) {

        TranslateTransition s1 = new TranslateTransition(Duration.millis(300), n);
        s1.setInterpolator(Interpolator.EASE_OUT);
        s1.setFromY(200.0);
        s1.setToY(0.0);        

        FadeTransition s2 = new FadeTransition(Duration.millis(300), n);
        s2.setInterpolator(Interpolator.EASE_BOTH);
        s2.setFromValue(0.0);
        s2.setToValue(1.0);
        
        ScaleTransition s3 = new ScaleTransition(Duration.millis(300), n);
        s3.setInterpolator(Interpolator.EASE_OUT);
        s3.setFromX(0.4);
        s3.setFromY(0.4);
        s3.setToX(1.0);
        s3.setToY(1.0);
        
        return new ParallelTransition(s1, s2, s3);
    }        
}
