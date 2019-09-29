package gq.learningEnglish;

import gq.learningEnglish.models.Questions;

import java.util.List;

public class Utils {
    public static void printList(List<String> list) {
        System.out.println("//======================");
        for (String str : list) {
            System.out.println(str);
        }
        System.out.println("======================//");
    }

    public static void printListQuestions(List<Questions> list) {
        System.out.println("//======================");
        for (Questions str : list) {
            System.out.println(str.getVocabularyId() + " | " + str.getUserId() + " | " + str.getAskingLanguage() + " | " + str.getRussianWord() + " | " + str.getEnglishWord() + " | " + str.getResult());
        }
        System.out.println("======================//");
    }
}
