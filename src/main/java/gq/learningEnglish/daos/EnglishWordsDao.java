package gq.learningEnglish.daos;

import gq.learningEnglish.SqlScripts;
import gq.learningEnglish.mappers.WordsMapper;
import gq.learningEnglish.models.Word;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class EnglishWordsDao {
    private final NamedParameterJdbcTemplate jdbc;

    public EnglishWordsDao(NamedParameterJdbcTemplate jdbcOperations) {
        jdbc = jdbcOperations;
    }

    public List<Word> getEnglishWord(String word) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("word", word.toUpperCase());
        return jdbc.query(SqlScripts.GET_ENGLISH_WORD_ID.getSql(), params, new WordsMapper());
    }

    public int addEnglishWord(Word word) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("word", word.getWord().toUpperCase());
        params.put("description", word.getDescription());
        params.put("partOfSpeech", word.getPartOfSpeech());
        return jdbc.update(SqlScripts.ADD_ENGLISH_WORD.getSql(), params);
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
