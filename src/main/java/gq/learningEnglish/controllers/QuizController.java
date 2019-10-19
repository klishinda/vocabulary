package gq.learningEnglish.controllers;

import gq.learningEnglish.daos.HistoryDao;
import gq.learningEnglish.models.RandomWordsMode;
import gq.learningEnglish.models.wordsQuestionnaire.Answer;
import gq.learningEnglish.models.wordsQuestionnaire.Question;
import gq.learningEnglish.services.AskingVocabulary;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class QuizController {
    private final AskingVocabulary askingVocabulary;
    private final HistoryDao historyDao;

    public QuizController(AskingVocabulary askingVocabulary, HistoryDao historyDao) {
        this.askingVocabulary = askingVocabulary;
        this.historyDao = historyDao;
    }

    @Bean
    public void startTest() {
        RandomWordsMode wordsMode = RandomWordsMode.ABSOLUTE_RANDOM;
        int randomWords = 5;
        Map<Question, List<Answer>> quizResults = askingVocabulary.quiz(wordsMode, randomWords);
        historyDao.addHistoryRecords(quizResults);
    }
}
