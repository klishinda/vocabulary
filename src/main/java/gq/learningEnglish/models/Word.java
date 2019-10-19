package gq.learningEnglish.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Word {
    Long id;
    String word;
    String description;
    byte partOfSpeech;

    public Word (String word, String description, byte partOfSpeech) {
        this.word = word;
        this.description = description;
        this.partOfSpeech = partOfSpeech;
    }
}
