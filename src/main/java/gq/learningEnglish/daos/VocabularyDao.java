package gq.learningEnglish.daos;

import gq.learningEnglish.SqlScripts;
import gq.learningEnglish.models.Word;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

import static gq.learningEnglish.Utils.printList;

@Repository
public class VocabularyDao {
    private final NamedParameterJdbcTemplate jdbc;

    public VocabularyDao(NamedParameterJdbcTemplate jdbcOperations) {
        jdbc = jdbcOperations;
    }

    public int addVocabularyRecord(Word russianWord, Word englishWord, int userId) {
        final HashMap<String, Object> vocabularyParams = new HashMap<>();
        vocabularyParams.put("userId", userId);
        vocabularyParams.put("rusId", russianWord.getId());
        vocabularyParams.put("engId", englishWord.getId());
        return jdbc.update(SqlScripts.ADD_VOCABULARY_PAIR.getSql(), vocabularyParams);
    }

    public void removeVocabularyRecord(int vocabularyId) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("vocabularyId", vocabularyId);
        jdbc.update(SqlScripts.REMOVE_VOCABULARY_PAIR.getSql(), params);
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
