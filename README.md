HelloCommon [![Release](https://jitpack.io/v/adrianromero/hellocommon.svg)](https://jitpack.io/#adrianromero/hellocommon)
============

HelloCommon is a JavaFX library for displaying internal dialogs

![Screenshot](https://i.imgur.com/KOXgvZW.png)

Install
=======

To install the library add: 
 
   ```gradle
   repositories { 
        jcenter()
        maven { url "https://jitpack.io" }
   }
   dependencies {
         compile 'com.github.adrianromero:hellocommon:1.2.20'
   }
   ```  

Usage
=====

Go to the [Demo.java](blob/master/src/main/java/com/adr/hellocommon/dialog/Demo.java) class file to see how to intialize and use HelloCommon.

Initialize
----------

It is needed an *StackPane* as parent for internal dialogs. Usually the *Scene* root.

    ```Java
    MessageUtils.setDialogRoot(root, true);
    ```

Information dialog
------------------

```java
    MessageUtils.showInfo(root, "Title", "Information message.");
```

![Information dialog](https://i.imgur.com/DDCqF3M.png)

License
=======

HelloCommon is licensed under the Apache License, Version 2.0, January 2004.
