package de.fuberlin.hcc.questionnaires.model.multiplechoice.choicewithtext;

import java.util.ArrayList;
import java.util.List;

public class ChoiceAndTextSummary {
    private final long choiceId;
    private final List<String> answerTexts;
    private String label;

    public ChoiceAndTextSummary(Long choiceId) {
        this.choiceId = choiceId;
        answerTexts = new ArrayList<>();
    }

    public void addText(String answerText) {
        answerTexts.add(answerText);
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public long getChoiceId() {
        return choiceId;
    }

    public List<String> getAnswerTexts() {
        return answerTexts;
    }

    public String getLabel() {
        return label;
    }
}
