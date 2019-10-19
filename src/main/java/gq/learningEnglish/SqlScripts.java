package gq.learningEnglish;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SqlScripts {
    GET_RUSSIAN_WORD_ID("select r.id, r.word, r.description, r.part_of_speech from russian_words r where r.word = :word"),
    GET_ENGLISH_WORD_ID("select e.id, e.word, e.description, e.part_of_speech from english_words e where e.word = :word"),
    ADD_RUSSIAN_WORD("insert into russian_words(word, description, part_of_speech) values (:word, :description, :partOfSpeech)"),
    ADD_ENGLISH_WORD("insert into english_words(word, description, part_of_speech) values (:word, :description, :partOfSpeech)"),
    ADD_VOCABULARY_PAIR("insert into vocabulary(user_id, russian_id, english_id) values (:userId, :rusId, :engId)"),
    GET_RUSSIAN_WORD_TRANSLATION("select e.word from english_words e\n" +
                                    "join vocabulary v on v.english_id = e.id\n" +
                                    "join russian_words r on r.id = v.russian_id\n" +
                                    "where r.word = :rusWord"),
    GET_ENGLISH_WORD_TRANSLATION("select r.word from english_words e\n" +
                                    "join vocabulary v on v.english_id = e.id\n" +
                                    "join russian_words r on r.id = v.russian_id\n" +
                                    "where e.word = :engWord"),
    UPDATE_RUSSIAN_WORD("update russian_words set word = :rusWord where id = :rusWordId;"),
    UPDATE_ENGLISH_WORD("update english_words set word = :engWord where id = :engWordId;"),
    REMOVE_VOCABULARY_PAIR("delete from vocabulary where id = :vocabularyId"),
    REMOVE_RUSSIAN_WORD("delete from vocabulary where russian_id = :wordId; delete from russian_words where id = :wordId;"),
    REMOVE_ENGLISH_WORD("delete from vocabulary where english_id = :wordId; delete from english_words where id = :wordId;"),
    GET_RANDOM_WORDS("select e.id as asking_word_id, e.word as asking_word, e.description, v.id as vocabulary_id, r.id as answer_word_id, r.word as answer_word, 'ENGLISH' as asking_language\n" +
                        "from (select * from english_words order by random() limit :numberOfEnglishWords) e\n" +
                        "join vocabulary v on v.english_id = e.id\n" +
                        "join russian_words r on r.id = v.russian_id\n" +
                        "union all\n" +
                        "select r.id, r.word, r.description, v.id, e.id, e.word, 'RUSSIAN'\n" +
                        "from (select * from russian_words order by random() limit :numberOfRussianWords) r\n" +
                        "join vocabulary v on v.russian_id = r.id\n" +
                        "join english_words e on e.id = v.english_id;"),
    ADD_HISTORY_RESULT("insert into history (user_id, vocabulary_id, asking_language, result) values (:userId, :vocabularyId, :lang, :result)"),;

    private String sql;
}