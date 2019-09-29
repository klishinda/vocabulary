package gq.learningEnglish.controllers;

import gq.learningEnglish.daos.HistoryDao;
import gq.learningEnglish.daos.PrepareQuestionsDao;
import gq.learningEnglish.models.AvailableLanguages;
import gq.learningEnglish.models.Questions;
import gq.learningEnglish.models.RandomWordsMode;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Controller
public class QuizController {
    private final PrepareQuestionsDao prepareQuestionsDao;
    private final HistoryDao historyDao;

    private List<Questions> quizWords;
    private String answer;
    private String tempResult;
    private Scanner scanner = new Scanner(System.in);

    public QuizController(PrepareQuestionsDao prepareQuestionsDao, HistoryDao historyDao) {
        this.prepareQuestionsDao = prepareQuestionsDao;
        this.historyDao = historyDao;
    }

    @Bean
    public void test() {
        RandomWordsMode wordsMode = RandomWordsMode.RUSSIAN;
        quizWords = prepareQuestionsDao.getRandomWords(6);
        System.out.println("Let's start! Write translation to the next words." + quizWords.size());
        for (int i = 0; i < quizWords.size(); i++) {
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
        int occurrences = Collections.frequency(quizWords, "bat");
        for (Questions q : quizWords) {
            historyDao.addHistoryRecord(q);
        }
        scanner.close();
    }

    private void askingRussianTranslation(int i) {
        //System.out.println(quizWords.get(i).getRussianWord());
        //answer = console.readLine(quizWords.get(i).getRussianWord() + ": ");
        System.out.println(quizWords.get(i).getRussianWord() + ": ");
        answer = scanner.nextLine().toUpperCase();
        if (answer.equals(quizWords.get(i).getEnglishWord())) {
            quizWords.get(i).setResult(true);
            tempResult = "CORRECT!";
        } else {
            quizWords.get(i).setResult(false);
            tempResult = "INCORRECT!";
        }
        quizWords.get(i).setAskingLanguage(AvailableLanguages.RUSSIAN);
        System.out.println("Your answer is " + answer + ". " + tempResult);
    }

    private void askingEnglishTranslation(int i) {
        quizWords.get(i).setAskingLanguage(AvailableLanguages.ENGLISH);
        if (answer.equals(quizWords.get(i).getRussianWord())) {
            quizWords.get(i).setResult(true);
            tempResult = "CORRECT!";
        } else {
            quizWords.get(i).setResult(false);
            tempResult = "INCORRECT!";
        }
    }
}
