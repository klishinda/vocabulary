package gq.learningEnglish.controllers;

import gq.learningEnglish.daos.HistoryDao;
import gq.learningEnglish.daos.PrepareQuestionsDao;
import gq.learningEnglish.models.RandomWordsMode;
import gq.learningEnglish.models.wordsQuestionnaire.Answer;
import gq.learningEnglish.models.wordsQuestionnaire.Question;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Controller
public class QuizController {
    private final PrepareQuestionsDao prepareQuestionsDao;
    private final HistoryDao historyDao;

    private Map<Question, List<Answer>> askingWords;
    private String answer;
    private String tempResult;
    private Scanner scanner = new Scanner(System.in);

    public QuizController(PrepareQuestionsDao prepareQuestionsDao, HistoryDao historyDao) {
        this.prepareQuestionsDao = prepareQuestionsDao;
        this.historyDao = historyDao;
    }

    @Bean
    public void startTest() {
        RandomWordsMode wordsMode = RandomWordsMode.RUSSIAN;
        askingWords = prepareQuestionsDao.getRandomWords(6, wordsMode);
        System.out.println("Let's start! Write translation to the next words." + askingWords.size());
        /*for (int i = 0; i < askingWords.size(); i++) {
            if (wordsMode == RandomWordsMode.RUSSIAN) {
                askingRussianTranslation(i);
            } else if (wordsMode == RandomWordsMode.ENGLISH) {
                askingEnglishTranslation(i);
            } else if (wordsMode == RandomWordsMode.ABSOLUTE_RANDOM) {
                if(Math.random() < 0.5) {
                    askingRussianTranslation(i);
                } else {
                    askingEnglishTranslation(i);
                }
            }
        }
        int occurrences = Collections.frequency(askingWords, "bat");
        for (WordsForTesting q : askingWords) {
            historyDao.addHistoryRecord(q);
        }
        scanner.close();*/
    }

    /*private void askingRussianTranslation(int i) {
        System.out.println(askingWords.get(i).getRussianWord() + ": ");
        answer = scanner.nextLine().toUpperCase();
        if (answer.equals(askingWords.get(i).getEnglishWord())) {
            askingWords.get(i).setResult(true);
            tempResult = "CORRECT!";
        } else {
            askingWords.get(i).setResult(false);
            tempResult = "INCORRECT!";
        }
        askingWords.get(i).setAskingLanguage(AvailableLanguages.RUSSIAN);
        System.out.println("Your answer is " + answer + ". " + tempResult);
    }

    private void askingEnglishTranslation(int i) {
        askingWords.get(i).setAskingLanguage(AvailableLanguages.ENGLISH);
        if (answer.equals(askingWords.get(i).getRussianWord())) {
            askingWords.get(i).setResult(true);
            tempResult = "CORRECT!";
        } else {
            askingWords.get(i).setResult(false);
            tempResult = "INCORRECT!";
        }
    }*/
}
