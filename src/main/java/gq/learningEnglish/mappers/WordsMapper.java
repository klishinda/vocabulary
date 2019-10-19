package gq.learningEnglish.mappers;

import gq.learningEnglish.models.Word;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WordsMapper implements RowMapper<Word> {
    @Override
    public Word mapRow(ResultSet rs, int rowNum) throws SQLException {
        Word word = new Word();
        word.setId(rs.getLong("ID"));
        word.setWord(rs.getString("WORD"));
        word.setDescription(rs.getString("DESCRIPTION"));
        word.setPartOfSpeech(rs.getByte("PART_OF_SPEECH"));

        return word;

    }
}
