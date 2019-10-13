package gq.learningEnglish.daos;

import gq.learningEnglish.SqlScripts;
import gq.learningEnglish.mappers.QuestionsMapper;
import gq.learningEnglish.models.RandomWordsMode;
import gq.learningEnglish.models.wordsQuestionnaire.Answer;
import gq.learningEnglish.models.wordsQuestionnaire.Question;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static gq.learningEnglish.Utils.printMapQuestions;

@Repository
public class PrepareQuestionsDao {
    private final NamedParameterJdbcTemplate jdbc;

    public PrepareQuestionsDao(NamedParameterJdbcTemplate jdbcOperations) {
        jdbc = jdbcOperations;
    }

    public Map<Question, List<Answer>> getRandomWords(int numberOfRandomWords, RandomWordsMode wordsMode) {
        int russianWordsNumber = 0;
        int englishWordsNumber = 0;

        if (wordsMode == RandomWordsMode.ABSOLUTE_RANDOM && (numberOfRandomWords & 1) == 1) {
            russianWordsNumber = (int) Math.floor(numberOfRandomWords/2);
            englishWordsNumber = (int) Math.floor(numberOfRandomWords/2);
            if ((numberOfRandomWords & 1) == 1) {
                if (Math.random() < 0.5) {
                    russianWordsNumber++;
                } else {
                    englishWordsNumber++;
                }
            }
        }

        if (wordsMode == RandomWordsMode.ENGLISH) {
            englishWordsNumber = numberOfRandomWords;
        }
        else if (wordsMode == RandomWordsMode.RUSSIAN) {
            russianWordsNumber = numberOfRandomWords;
        }

        final HashMap<String, Object> params = new HashMap<>();
        params.put("numberOfRussianWords", russianWordsNumber);
        params.put("numberOfEnglishWords", englishWordsNumber);
        //List<WordsForTesting> wordsList = jdbc.query(SqlScripts.GET_RANDOM_WORDS.getSql(), params, new QuestionsMapper());
        Map<Question, List<Answer>> wordsList = jdbc.query(SqlScripts.GET_RANDOM_WORDS.getSql(), params, new QuestionsMapper());
        printMapQuestions(wordsList);
        return wordsList;
    }
}
