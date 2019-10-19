package gq.learningEnglish.services;

import gq.learningEnglish.daos.PrepareQuestionsDao;
import gq.learningEnglish.models.RandomWordsMode;
import gq.learningEnglish.models.wordsQuestionnaire.Answer;
import gq.learningEnglish.models.wordsQuestionnaire.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Service
public class AskingVocabulary {
    private final PrepareQuestionsDao prepareQuestionsDao;

    private String answer;
    private Scanner scanner = new Scanner(System.in);

    public AskingVocabulary(PrepareQuestionsDao prepareQuestionsDao) {
        this.prepareQuestionsDao = prepareQuestionsDao;
    }
    public Map<Question, List<Answer>> quiz(RandomWordsMode wordsMode, int numberOfRandomWords) {
        Map<Question, List<Answer>> askingWords = prepareQuestionsDao.getRandomWords(numberOfRandomWords, wordsMode);
        System.out.println("Let's start! Write translation to the next words." + askingWords.size());

        String tempResult;
        int countCorrectAnswers = 0;
        int countAllWords = 0;
        // set only correct answers. All empty fields "result" means wrong answer
        for (Map.Entry<Question, List<Answer>> wordMap : askingWords.entrySet()) {
            Question questionWord = wordMap.getKey();
            System.out.println(questionWord.getAskingWord() + " " + questionWord.getDescription());
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
        return askingWords;
    }
}
