package ru.serjeypyzin.firstTask;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/*
* Написать функцию, создающую резервную копию всех файлов
* в директории(без поддиректорий) во вновь созданную папку ./backup
* */
public class Main {
    public static void main(String[] args) {
        backupFiles(".");
    }

    public static void backupFiles (String path){
        File currentDirectory = new File(path);
        String pathToBackup = path + "/backup";
        File backupDirectory = new File(pathToBackup);

        if (!backupDirectory.exists()){
            backupDirectory.mkdir();
        }

        Arrays.stream(currentDirectory.listFiles())
                .filter(File::isFile)
                .forEach(file -> {
                    try {
                        Files.copy(file.toPath(), Path.of(pathToBackup, file.getName()));
                    } catch (IOException e){
                        throw new RuntimeException();
                    }
                });
    }
}
