package com.example.finalfour;

public class GameStep {
    String[] Colors;
    String LabelColor;
    String LabelText;
    String Correct;
    String FalseTarget;

    public GameStep(String[] colors, String labelColor, String labelText, String correct, String wrong) {
        this.Colors = colors;
        this.LabelColor = labelColor;
        this.LabelText = labelText;
        this.Correct = correct;
        this.FalseTarget = wrong;
    }

    public String[] getColors() {
        return Colors;
    }

    public void setColors(String[] colors) {
        Colors = colors;
    }

    public String getLabelColor() {
        return LabelColor;
    }

    public void setLabelColor(String labelColor) {
        LabelColor = labelColor;
    }

    public String getLabelText() {
        return LabelText;
    }

    public void setLabelText(String labelText) {
        LabelText = labelText;
    }

    public String getCorrect() {
        return Correct;
    }

    public void setCorrect(String correct) {
        Correct = correct;
    }

    public String getFalseTarget() {
        return FalseTarget;
    }

    @Override
    public String toString() {
        return "GameStep{" + "Colors=" + Colors + ", LabelColor=" + LabelColor + ", LabelText=" + LabelText + ", Correct=" + Correct + '}';
    }

    public void guh() {
        for (String color : Colors) {
            System.out.println(color);

        }
    }
}
