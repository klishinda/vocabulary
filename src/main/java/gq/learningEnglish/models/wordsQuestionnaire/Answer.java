package gq.learningEnglish.models.wordsQuestionnaire;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Answer {
    int vocabularyId;
    int answerWordId;
    String answerWord;
    boolean result;

    public Answer(int vocabularyId, int answerWordId, String answerWord) {
        this.vocabularyId = vocabularyId;
        this.answerWordId = answerWordId;
        this.answerWord = answerWord;
    }
}
