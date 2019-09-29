package gq.learningEnglish.daos;

import gq.learningEnglish.SqlScripts;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class EnglishWordsDao {
    private final NamedParameterJdbcTemplate jdbc;

    public EnglishWordsDao(NamedParameterJdbcTemplate jdbcOperations) {
        jdbc = jdbcOperations;
    }

    public void updateEnglishWord (int wordId, String newWord) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("engWord", newWord.toUpperCase());
        params.put("engWordId", wordId);
        jdbc.update(SqlScripts.UPDATE_ENGLISH_WORD.getSql(), params);
    }

    public void removeEnglishWord(int englishWordId) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("wordId", englishWordId);
        jdbc.update(SqlScripts.REMOVE_ENGLISH_WORD.getSql(), params);
    }
}
