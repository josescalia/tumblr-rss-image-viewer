package com.josescalia.tumblr.util.swing;


import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Josescalia
 * Date: 11/11/12
 * Time: 5:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class LabelValueListCellRenderer extends DefaultListCellRenderer {
    private static final long serialVersionUID = 3288106828229968577L;

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        final Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof LabelValue) {
            setText(((LabelValue) value).getLabel());
        }
        return c;
    }
}
