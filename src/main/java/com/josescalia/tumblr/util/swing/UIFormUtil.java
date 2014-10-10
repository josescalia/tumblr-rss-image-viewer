package com.josescalia.tumblr.util.swing;

import com.josescalia.tumblr.util.swing.LabelValue;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Josescalia
 * Date: 11/11/12
 * Time: 5:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class UIFormUtil {
    public static final String EDIT_MODE = "edit";
    public static final String ADD_MODE = "add";
    public static final String CANCEL_MODE = "cancel";

    public static void btnSetupConfig(String mode, JPanel btnPanel) {
        Component[] comps = btnPanel.getComponents();
        if (mode.equalsIgnoreCase(EDIT_MODE) || mode.equalsIgnoreCase(ADD_MODE)) {
            for (Component comp : comps) {
                if (comp instanceof JButton) {
                    if (comp.getName().equalsIgnoreCase("btnNew")) {
                        comp.setEnabled(false);
                    }
                    if (comp.getName().equalsIgnoreCase("btnEdit")) {
                        comp.setEnabled(false);
                    }
                    if (comp.getName().equalsIgnoreCase("btnDelete")) {
                        comp.setEnabled(false);
                    }
                    if (comp.getName().equalsIgnoreCase("btnCancel")) {
                        comp.setEnabled(true);
                    }
                    if (comp.getName().equalsIgnoreCase("btnSave")) {
                        comp.setEnabled(true);
                    }
                    if (comp.getName().equalsIgnoreCase("btnExit")) {
                        comp.setEnabled(true);
                    }
                } else {
                    comp.setEnabled(false);
                }
            }
        }
        if (mode.equalsIgnoreCase(CANCEL_MODE)) {
            for (Component comp : comps) {
                if (comp instanceof JButton) {
                    if (comp.getName().equalsIgnoreCase("btnNew")) {
                        comp.setEnabled(true);
                    }
                    if (comp.getName().equalsIgnoreCase("btnEdit")) {
                        comp.setEnabled(true);
                    }
                    if (comp.getName().equalsIgnoreCase("btnDelete")) {
                        comp.setEnabled(true);
                    }
                    if (comp.getName().equalsIgnoreCase("btnCancel")) {
                        comp.setEnabled(false);
                    }
                    if (comp.getName().equalsIgnoreCase("btnSave")) {
                        comp.setEnabled(false);
                    }
                    if (comp.getName().equalsIgnoreCase("btnExit")) {
                        comp.setEnabled(true);
                    }
                } else {
                    comp.setEnabled(false);
                }
            }
        }

    }

    public static void fillAndSetOrderLvMap(Map<String, String> map, List<LabelValue> filterList) {
        //map.put("0", "-- Pilih "+ label+" --");
        for (String key : map.keySet()) {
            LabelValue lv = new LabelValue();
            lv.setLabel(map.get(key));
            lv.setValue(key);
            filterList.add(lv);
        }


    }

    public static void isEnabledAndClearComp(boolean isEnabled, boolean isClear, JPanel parent) {
        Component[] comps = parent.getComponents();
        for (Component comp : comps) {
            //enable component
            if (comp instanceof JLabel) {
                comp.setEnabled(true);
            } else {
                if(comp instanceof JScrollPane){
                    for(Component component : ((JScrollPane) comp).getComponents()){
                        if(component instanceof JViewport){  //this is text Area inside jscroll pane
                            if(((JViewport) component).getView() instanceof JTextArea){
                                ((JViewport) component).getView().setEnabled(isEnabled);
                            }

                        }
                    }
                }else{
                    comp.setEnabled(isEnabled);
                }
            }
            //clear component
            if (isClear) {
                if (comp instanceof JTextField) {
                    ((JTextField) comp).setText("");
                } else if (comp instanceof JComboBox) {
                    ((JComboBox) comp).setSelectedIndex(0);
                } else if (comp instanceof JTextArea) {
                    ((JTextArea) comp).setText("");
                } else if (comp instanceof JScrollPane) { //this is JTextArea
                    for (Component component : ((JScrollPane) comp).getComponents()) {
                        if (component instanceof JViewport) {
                            if (((JViewport) component).getView() instanceof JTextArea) {
                                ((JTextArea) ((JViewport) component).getView()).setText("");
                            }

                        }
                    }
                } else {
                    //un defined
                }
            }
        }
    }
}

