package gq.learningEnglish;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SqlScripts {
    GET_RUSSIAN_WORD_ID("select r.id from russian_words r where r.word = :rusWord"),
    GET_ENGLISH_WORD_ID("select e.id from english_words e where e.word = :engWord"),
    ADD_RUSSIAN_WORD("insert into russian_words(word) values (:rusWord)"),
    ADD_ENGLISH_WORD("insert into english_words(word) values (:engWord)"),
    ADD_VOCABULARY_PAIR("insert into vocabulary(user_id, russian_id, english_id, part_of_speech) values (:userId, :rusId, :engId, :partOfSpeechId)"),
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
    GET_RANDOM_WORDS("select v.id, r.word rus_word, e.word eng_word\n" +
                        "from vocabulary as v\n" +
                        "join russian_words r on r.id = v.russian_id\n" +
                        "join english_words e on e.id = v.english_id\n" +
                        "order by random() limit :numberOfRandomRecords;"),
    ADD_HISTORY_RESULT("insert into history (user_id, vocabulary_id, asking_language, result) values (:userId, :vocabularyId, :lang, :result)"),;

    private String sql;
}