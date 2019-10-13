package gq.learningEnglish.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WordsForTesting {
    int vocabularyId;                       // возможно, станет массивом со значениями ниже
    AvailableLanguages askingLanguage;      // меняется, нужно
    String russianWord;                     // возможно, заменить на asking word
    String englishWord;                     // и здесь будет массив
    Boolean result;                         // и это массив. В итоге три поля в новый объект, который здесь будет List-ом

    public WordsForTesting(int vocabularyId, AvailableLanguages askingLanguage, String russianWord, String englishWord) {
        this.vocabularyId = vocabularyId;
        this.askingLanguage = askingLanguage;
        this.russianWord = russianWord;
        this.englishWord = englishWord;
    }
}
