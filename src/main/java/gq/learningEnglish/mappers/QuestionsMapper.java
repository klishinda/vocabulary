package gq.learningEnglish.mappers;

import gq.learningEnglish.models.Questions;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionsMapper implements RowMapper<Questions> {
    @Override
    public Questions mapRow(ResultSet rs, int rowNum) throws SQLException {
        Questions quiz = new Questions();
        quiz.setVocabularyId(rs.getInt("ID"));
        quiz.setRussianWord(rs.getString("RUS_WORD"));
        quiz.setEnglishWord(rs.getString("ENG_WORD"));
        return quiz;
    }
}
