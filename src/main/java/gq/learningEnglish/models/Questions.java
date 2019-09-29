package gq.learningEnglish.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Questions {
    int userId;                             // он всегда один на все вопросы - нужен ли он тут?
    int vocabularyId;                       // возможно, станет массивом со значениями ниже
    AvailableLanguages askingLanguage;      // меняется, нужно
    String russianWord;                     // возможно, заменить на asking word
    String englishWord;                     // и здесь будет массив
    Boolean result;                         // и это массив. В итоге три поля в новый объект, который здесь будет List-ом

    public Questions (int userId, int vocabularyId, AvailableLanguages askingLanguage, String russianWord, String englishWord) {
        this.userId = userId;
        this.vocabularyId = vocabularyId;
        this.askingLanguage = askingLanguage;
        this.russianWord = russianWord;
        this.englishWord = englishWord;
    }
}
