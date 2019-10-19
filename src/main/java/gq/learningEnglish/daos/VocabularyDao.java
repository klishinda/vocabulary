package gq.learningEnglish.daos;

import gq.learningEnglish.SqlScripts;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class VocabularyDao {
    private final NamedParameterJdbcTemplate jdbc;

    public VocabularyDao(NamedParameterJdbcTemplate jdbcOperations) {
        jdbc = jdbcOperations;
    }

    public int addWordPair(String russianWord, String englishWord, int userId) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("rusWord", russianWord.toUpperCase());
        params.put("engWord", englishWord.toUpperCase());
        Integer russianWordId = DataAccessUtils.singleResult(jdbc.queryForList(SqlScripts.GET_RUSSIAN_WORD_ID.getSql(), params, Integer.class));
        Integer englishWordId = DataAccessUtils.singleResult(jdbc.queryForList(SqlScripts.GET_ENGLISH_WORD_ID.getSql(), params, Integer.class));
        System.out.println("rusId: "+russianWordId+", engId: "+englishWordId);
        if (russianWordId==null) {
            russianWordId = jdbc.update(SqlScripts.ADD_RUSSIAN_WORD.getSql(), params);
        }
        if (englishWordId==null) {
            englishWordId = jdbc.update(SqlScripts.ADD_ENGLISH_WORD.getSql(), params);
        }
        final HashMap<String, Object> vocabularyParams = new HashMap<>();
        vocabularyParams.put("userId", userId);
        vocabularyParams.put("rusId", russianWordId);
        vocabularyParams.put("engId", englishWordId);
        return jdbc.update(SqlScripts.ADD_VOCABULARY_PAIR.getSql(), vocabularyParams);
    }

    public void removeWordPair(int vocabularyId) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("vocabularyId", vocabularyId);
        jdbc.update(SqlScripts.REMOVE_VOCABULARY_PAIR.getSql(), params);
    }
}
