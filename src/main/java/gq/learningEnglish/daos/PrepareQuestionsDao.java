package gq.learningEnglish.daos;

import gq.learningEnglish.SqlScripts;
import gq.learningEnglish.mappers.QuestionsMapper;
import gq.learningEnglish.models.Questions;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

import static gq.learningEnglish.Utils.printListQuestions;

@Repository
public class PrepareQuestionsDao {
    private final NamedParameterJdbcTemplate jdbc;

    public PrepareQuestionsDao(NamedParameterJdbcTemplate jdbcOperations) {
        jdbc = jdbcOperations;
    }

    public List<Questions> getRandomWords(int numberOfRandomWords) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("numberOfRandomRecords", numberOfRandomWords);
        List<Questions> wordsList = jdbc.query(SqlScripts.GET_RANDOM_WORDS.getSql(), params, new QuestionsMapper());
        printListQuestions(wordsList);
        return wordsList;
    }
}
