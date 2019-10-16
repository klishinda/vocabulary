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

    private String answer;
    private Scanner scanner = new Scanner(System.in);

    public QuizController(PrepareQuestionsDao prepareQuestionsDao, HistoryDao historyDao) {
        this.prepareQuestionsDao = prepareQuestionsDao;
        this.historyDao = historyDao;
    }

    @Bean
    public void startTest() {
        RandomWordsMode wordsMode = RandomWordsMode.RUSSIAN;
        Map<Question, List<Answer>> askingWords = prepareQuestionsDao.getRandomWords(6, wordsMode);
        System.out.println("Let's start! Write translation to the next words." + askingWords.size());

        String tempResult;
        int countCorrectAnswers = 0;
        int countAllWords = 0;
        // set only correct answers. All empty fields "result" means wrong answer
        for (Map.Entry<Question, List<Answer>> wordMap : askingWords.entrySet()) {
            Question questionWord = wordMap.getKey();
            System.out.println(questionWord.getAskingWord());
            for (int i = 0; i < wordMap.getValue().size(); i++) {
                countAllWords++;
                System.out.println("Your answer: ");
                answer = scanner.nextLine().toUpperCase();
                if (wordMap.getValue().stream().anyMatch(s -> s.getAnswerWord().equals(answer) && !s.isResult())) {
                    wordMap.getValue().stream().filter(s -> s.getAnswerWord().equals(answer)).forEach(ss -> ss.setResult(true));
                    tempResult = "CORRECT!";
                    countCorrectAnswers++;
                } else {
                    tempResult = "INCORRECT!";
                }
                System.out.println("Your answer is " + answer + ". " + tempResult);
            }
        }
        System.out.println("----------RESULTS----------");
        System.out.println("Correct answers: "+countCorrectAnswers+"/"+countAllWords);
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
