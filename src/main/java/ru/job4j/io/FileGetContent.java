package ru.job4j.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Predicate;

public class FileGetContent {
    private final File file;
    private final Predicate<Character> predicate;

    public FileGetContent(File file, Predicate<Character> predicate) {
        this.file = file;
        this.predicate = predicate;
    }

    public String getContent() {
        StringBuilder output = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines()
                    .flatMap(x -> x.chars().mapToObj(i -> (char) i))
                    .filter(predicate)
                    .forEach(output::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
