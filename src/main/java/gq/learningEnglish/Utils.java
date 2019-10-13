package gq.learningEnglish;

import gq.learningEnglish.models.WordsForTesting;
import gq.learningEnglish.models.wordsQuestionnaire.Answer;
import gq.learningEnglish.models.wordsQuestionnaire.Question;

import java.util.List;
import java.util.Map;

public class Utils {
    public static void printList(List<String> list) {
        System.out.println("//======================");
        for (String str : list) {
            System.out.println(str);
        }
        System.out.println("======================//");
    }

    public static void printListQuestions(List<WordsForTesting> list) {
        System.out.println("//======================");
        for (WordsForTesting str : list) {
            System.out.println(str.getVocabularyId() + " | " + " | " + str.getAskingLanguage() + " | " + str.getRussianWord() + " | " + str.getEnglishWord() + " | " + str.getResult());
        }
        System.out.println("======================//");
    }

    public static void printMapQuestions(Map<Question, List<Answer>> map) {
        for (Map.Entry<Question, List<Answer>> entry : map.entrySet()) {
            System.out.println("Asking word: " + entry.getKey().getAskingWordId() + ", " + entry.getKey().getAskingWord() + ". " + entry.getKey().getAskingLanguage());
            for (Answer a : entry.getValue()) {
                System.out.println("    Answer: " + a.getVocabularyId() + ", " + a.getAnswerWordId() + ", " + a.getAnswerWord());
            }
        }
    }
}
