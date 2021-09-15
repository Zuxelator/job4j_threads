package ru.job4j.io;

import java.io.*;

public class FileSaveContent {
    private final File file;

    public FileSaveContent(File file) {
        this.file = file;
    }

    public void saveContent(String content) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            out.print(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
