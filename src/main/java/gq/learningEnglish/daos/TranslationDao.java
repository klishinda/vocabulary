package gq.learningEnglish.daos;

import gq.learningEnglish.SqlScripts;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

import static gq.learningEnglish.Utils.printList;

@Repository
public class TranslationDao {
    private final NamedParameterJdbcTemplate jdbc;

    public TranslationDao(NamedParameterJdbcTemplate jdbcOperations) {
        jdbc = jdbcOperations;
    }

    public List<String> getRussianTranslation(String russianWordWord) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("rusWord", russianWordWord.toUpperCase());
        List<String> wordsList = jdbc.queryForList(SqlScripts.GET_RUSSIAN_WORD_TRANSLATION.getSql(), params, String.class);
        printList(wordsList);
        return wordsList;
    }

    public List<String> getEnglishTranslation(String englishWord) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("engWord", englishWord.toUpperCase());
        List<String> wordsList = jdbc.queryForList(SqlScripts.GET_ENGLISH_WORD_TRANSLATION.getSql(), params, String.class);
        printList(wordsList);
        return wordsList;
    }
}
