package de.fuberlin.hcc.questionnaires.model.singlechoice;

public class ChoiceWithAnswer {
    private long id;
    private String label;
    private boolean selected = false;

    public ChoiceWithAnswer(long id, String label, boolean selected) {
        this.id = id;
        this.label = label;
        this.selected = selected;
    }

    public long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public boolean isSelected() {
        return selected;
    }
}
