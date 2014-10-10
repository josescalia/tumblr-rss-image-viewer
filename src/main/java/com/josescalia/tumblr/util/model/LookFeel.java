package com.josescalia.tumblr.util.model;

/**
 * Created by josescalia on 24/07/14.
 */
public class LookFeel {
    private String lookAndFeelLabel;
    private String lookAndFeelValue;

    public LookFeel() {
    }

    public LookFeel(String lookAndFeelLabel, String lookAndFeelValue) {
        this.lookAndFeelLabel = lookAndFeelLabel;
        this.lookAndFeelValue = lookAndFeelValue;
    }

    public String getLookAndFeelLabel() {
        return lookAndFeelLabel;
    }

    public void setLookAndFeelLabel(String lookAndFeelLabel) {
        this.lookAndFeelLabel = lookAndFeelLabel;
    }

    public String getLookAndFeelValue() {
        return lookAndFeelValue;
    }

    public void setLookAndFeelValue(String lookAndFeelValue) {
        this.lookAndFeelValue = lookAndFeelValue;
    }

    @Override
    public String toString() {
        return "LookAndFeel{" +
                "lookAndFeelLabel='" + lookAndFeelLabel + '\'' +
                ", lookAndFeelValue='" + lookAndFeelValue + '\'' +
                '}';
    }
}
