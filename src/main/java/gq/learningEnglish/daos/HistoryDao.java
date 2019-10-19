package gq.learningEnglish.daos;

import gq.learningEnglish.SqlScripts;
import gq.learningEnglish.models.WordsForTesting;
import gq.learningEnglish.models.wordsQuestionnaire.Answer;
import gq.learningEnglish.models.wordsQuestionnaire.Question;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class HistoryDao {
    private final NamedParameterJdbcTemplate jdbc;

    public HistoryDao(NamedParameterJdbcTemplate jdbcOperations) {
        jdbc = jdbcOperations;
    }

    public void addHistoryRecord(WordsForTesting wordsForTesting) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("userId", 101);
        params.put("vocabularyId", wordsForTesting.getVocabularyId());
        params.put("lang", wordsForTesting.getAskingLanguage().name());
        params.put("result", wordsForTesting.getResult());
        jdbc.update(SqlScripts.ADD_HISTORY_RESULT.getSql(), params);
    }

    public void addHistoryRecords(Map<Question, List<Answer>> results) {
        final HashMap<String, Object> params = new HashMap<>();
        for (Map.Entry<Question, List<Answer>> entry : results.entrySet()) {
            params.put("userId", 101);
            params.put("lang", entry.getKey().getAskingLanguage().name());
            for (Answer a: entry.getValue()) {
                if (a.isResult()) {
                    params.put("result", true);
                }
                else {
                    params.put("result", false);
                }
                params.put("vocabularyId", a.getVocabularyId());
                jdbc.update(SqlScripts.ADD_HISTORY_RESULT.getSql(), params);
            }
        }
    }
}
