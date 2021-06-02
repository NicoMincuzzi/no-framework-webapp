package com.nicomincuzzi.frameworkless.utils;

import java.util.ArrayList;
import java.util.List;

public class StringHandler {

    private static StringHandler instance;

    private StringHandler() {
    }

    public static synchronized StringHandler getInstance() {
        if (instance == null) {
            instance = new StringHandler();
        }
        return instance;
    }

    public List<String> getListInputWords(String listWords) {
        List<String> words = new ArrayList<>();

        while (!listWords.isEmpty()) {
            if (listWords.contains(",")) {
                words.add(listWords.substring(listWords.lastIndexOf(",") + 2));
                listWords = listWords.substring(0, listWords.lastIndexOf(","));
            } else {
                words.add(listWords);
                listWords = "";
            }
        }
        return words;
    }

    public String removeLastComma(String listWords) {
        return listWords.substring(0, listWords.lastIndexOf(","));
    }

}
