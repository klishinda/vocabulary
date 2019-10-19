package gq.learningEnglish.daos;

import gq.learningEnglish.SqlScripts;
import gq.learningEnglish.models.Word;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class RussianWordsDao {
    private final NamedParameterJdbcTemplate jdbc;

    public RussianWordsDao(NamedParameterJdbcTemplate jdbcOperations) {
        jdbc = jdbcOperations;
    }

    public List<Word> getRussianWord(String word) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("word", word.toUpperCase());
        return jdbc.query(SqlScripts.GET_RUSSIAN_WORD_ID.getSql(), params, BeanPropertyRowMapper.newInstance((Word.class)));
    }

    public int addRussianWord(Word word) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("word", word.getWord().toUpperCase());
        params.put("description", word.getDescription());
        params.put("partOfSpeech", word.getPartOfSpeech());
        return jdbc.update(SqlScripts.ADD_RUSSIAN_WORD.getSql(), params);
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
