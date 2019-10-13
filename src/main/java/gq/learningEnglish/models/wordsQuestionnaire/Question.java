package gq.learningEnglish.models.wordsQuestionnaire;


import gq.learningEnglish.models.AvailableLanguages;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Question {
    AvailableLanguages askingLanguage;
    int askingWordId;
    String askingWord;

    public Question(AvailableLanguages askingLanguage, int askingWordId, String askingWord) {
        this.askingLanguage = askingLanguage;
        this.askingWordId = askingWordId;
        this.askingWord = askingWord;
    }
}
