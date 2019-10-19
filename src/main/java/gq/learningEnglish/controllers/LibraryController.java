package gq.learningEnglish.controllers;

import gq.learningEnglish.daos.*;
import gq.learningEnglish.models.Word;
import gq.learningEnglish.services.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
public class LibraryController {
    private final VocabularyDao vocabularyDao;
    private final VocabularyService vocabularyService;

    @Autowired
    public LibraryController(VocabularyDao vocabularyDao, VocabularyService vocabularyService) {
        this.vocabularyDao = vocabularyDao;
        this.vocabularyService = vocabularyService;
    }

    @Bean
    public void start() {
        Word engWord = new Word("WHIT", null, (byte) 101);
        Word rusWord = new Word("БЕЛЫЙ", null, (byte) 102);
        int newId = vocabularyService.addWordPair(rusWord, engWord, 101);
        //dao.removeVocabularyRecord(newId);
        //dao.updateRussianWord(10001, "DDD");
        //dao.updateEnglishWord(20003, "TTT");
        vocabularyDao.getEnglishTranslation("WHITE");
        vocabularyDao.getRussianTranslation("ДЕЛАТЬ");
        //prepareQuestionsDao.getRandomWords(6, RandomWordsMode.RUSSIAN);
    }
}
