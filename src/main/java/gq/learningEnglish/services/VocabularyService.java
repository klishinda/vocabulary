package gq.learningEnglish.services;

import gq.learningEnglish.daos.EnglishWordsDao;
import gq.learningEnglish.daos.PrepareQuestionsDao;
import gq.learningEnglish.daos.RussianWordsDao;
import gq.learningEnglish.daos.VocabularyDao;
import gq.learningEnglish.models.RandomWordsMode;
import gq.learningEnglish.models.Word;
import gq.learningEnglish.models.wordsQuestionnaire.Answer;
import gq.learningEnglish.models.wordsQuestionnaire.Question;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Service
public class VocabularyService {
    private final PrepareQuestionsDao prepareQuestionsDao;
    private final RussianWordsDao russianWordsDao;
    private final EnglishWordsDao englishWordsDao;
    private final VocabularyDao vocabularyDao;

    private String answer;
    private Scanner scanner = new Scanner(System.in);

    public VocabularyService(PrepareQuestionsDao prepareQuestionsDao, RussianWordsDao russianWordsDao, EnglishWordsDao englishWordsDao, VocabularyDao vocabularyDao) {
        this.prepareQuestionsDao = prepareQuestionsDao;
        this.russianWordsDao = russianWordsDao;
        this.englishWordsDao = englishWordsDao;
        this.vocabularyDao = vocabularyDao;
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

    public int addWordPair(Word russianWord, Word englishWord, int userId) {
        List<Word> russianWords = russianWordsDao.getRussianWord(russianWord.getWord());
        List<Word> englishWords = englishWordsDao.getEnglishWord(englishWord.getWord());
        checkWords(russianWords, englishWords, russianWord, englishWord);

        System.out.println("rusId: "+russianWord.getId()+", engId: "+englishWord.getId());
        return vocabularyDao.addVocabularyRecord(russianWord, englishWord, userId);

    }

    private void checkWords(List<Word> russianWords, List<Word> englishWords, Word russianWord, Word englishWord) {
        if (russianWords.size() == 0) {
            russianWord.setId((long) russianWordsDao.addRussianWord(russianWord));
        }
        else if (russianWords.size() == 1) {
            russianWord.setId(DataAccessUtils.singleResult(russianWords).getId());
        }
        if (englishWords.size() == 0) {
            englishWord.setId((long) englishWordsDao.addEnglishWord(englishWord));
        }
        else if (englishWords.size() == 1) {
            englishWord.setId(DataAccessUtils.singleResult(englishWords).getId());
        }
    }
}
