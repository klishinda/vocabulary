package gq.learningEnglish.controllers;

import gq.learningEnglish.daos.*;
import gq.learningEnglish.models.RandomWordsMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
public class LibraryController {
    private final EnglishWordsDao englishDao;
    private final RussianWordsDao russianDao;
    private final TranslationDao translationDao;
    private final VocabularyDao vocabularyDao;
    private final PrepareQuestionsDao prepareQuestionsDao;

    @Autowired
    public LibraryController(EnglishWordsDao englishDao, RussianWordsDao russianDao, TranslationDao translationDao, VocabularyDao vocabularyDao, PrepareQuestionsDao prepareQuestionsDao) {
        this.englishDao = englishDao;
        this.russianDao = russianDao;
        this.translationDao = translationDao;
        this.vocabularyDao = vocabularyDao;
        this.prepareQuestionsDao = prepareQuestionsDao;
    }

    @Bean
    public void start() {
        int newId = vocabularyDao.addWordPair("Белый", "Whi", 101);
        //dao.removeWordPair(newId);
        //dao.updateRussianWord(10001, "DDD");
        //dao.updateEnglishWord(20003, "TTT");
        translationDao.getEnglishTranslation("WHITE");
        translationDao.getRussianTranslation("ДЕЛАТЬ");
        //prepareQuestionsDao.getRandomWords(6, RandomWordsMode.RUSSIAN);
    }
}
