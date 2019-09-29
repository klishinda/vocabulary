package gq.learningEnglish.daos;

import gq.learningEnglish.SqlScripts;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class RussianWordsDao {

    private final NamedParameterJdbcTemplate jdbc;

    public RussianWordsDao(NamedParameterJdbcTemplate jdbcOperations) {
        jdbc = jdbcOperations;
    }

    public void updateRussianWord (int wordId, String newWord) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("rusWord", newWord.toUpperCase());
        params.put("rusWordId", wordId);
        jdbc.update(SqlScripts.UPDATE_RUSSIAN_WORD.getSql(), params);
    }

    public void removeRussianWord(int russianWordId) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("wordId", russianWordId);
        jdbc.update(SqlScripts.REMOVE_RUSSIAN_WORD.getSql(), params);
    }
}
