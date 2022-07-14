
package com.example.bicepgamers.Util;

import com.example.bicepgamers.Application.Main;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

//makes a copy of the original databse and puts it in a file.
public class TestUtils {
    private  static final File BG_SRC = new File("src/main/assets/db/BG.script");

    //return copy file of the database
    public static File copyDB() throws IOException{
        final File target = File.createTempFile("temp-db",".script");
        Files.copy(BG_SRC,target);
        Main.setDBPathName(target.getAbsolutePath().replace(".script",""));
        return target;
    }
}


