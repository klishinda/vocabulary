package gq.learningEnglish.mappers;

import gq.learningEnglish.models.AvailableLanguages;
import gq.learningEnglish.models.wordsQuestionnaire.Answer;
import gq.learningEnglish.models.wordsQuestionnaire.Question;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionsMapper implements ResultSetExtractor<Map<Question, List<Answer>>> {
    @Override
    public Map<Question, List<Answer>> extractData(ResultSet rs) throws SQLException {
        Map<Question, List<Answer>> map = new HashMap<>();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ ");
        while (rs.next()) {
            System.out.println(rs.getString("asking_word"));

            Question question = new Question(AvailableLanguages.valueOf(rs.getString("asking_language")),
                    rs.getInt("asking_word_id"),
                    rs.getString("asking_word"));
            Answer answer = new Answer(rs.getInt("vocabulary_id"),
                    rs.getInt("answer_word_id"),
                    rs.getString("answer_word"));

            List<Answer> itemsList = map.get(question);

            if (itemsList == null) {
                itemsList = new ArrayList<>();
                itemsList.add(answer);
                map.put(question, itemsList);
            } else {
                itemsList.add(answer);
            }
        }
        return map;
    }
}
