package de.fuberlin.hcc.questionnaires.model.multiplechoice.choicewithtext;

public class ChoiceWithTextWithAnswer {
    private final long choiceId;
    private final String label;
    private final boolean selected;
    private final String answerText;

    public ChoiceWithTextWithAnswer(ChoiceWithText choice, boolean selected, String answerText) {
        this.choiceId = choice.getId();
        this.label = choice.getLabel();
        this.selected = selected;
        this.answerText = answerText;
    }

    public long getChoiceId() {
        return choiceId;
    }

    public String getLabel() {
        return label;
    }

    public boolean isSelected() {
        return selected;
    }

    public String getAnswerText() {
        return answerText;
    }
}
