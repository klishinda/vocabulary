package gq.learningEnglish.daos;

import gq.learningEnglish.SqlScripts;
import gq.learningEnglish.models.WordsForTesting;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

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
}
