package com.josescalia.tumblr.util.swing;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Josescalia
 * Date: 12/14/12
 * Time: 11:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class UIAlert {
    public static ImageIcon createIcon(String path) {
        return new ImageIcon(UIAlert.class.getResource(path));
    }

    public static void showSuccessDelete(JComponent parent) {
        showInformation(parent,UIMessageConstants.saveSucceedMsg);
    }

    public static void showSuccessSave(JComponent parent) {
        showInformation(parent, UIMessageConstants.saveFailedMsg);
    }

    public static void showSuccessUpdate(JComponent parent) {
        showInformation(parent, UIMessageConstants.updateSucceedMsg);
    }

    public static void showInformation(JComponent parent, String message) {
        JOptionPane.showMessageDialog(
                parent,
                message,
                UIMessageConstants.informationAlertTitle,
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showError(JComponent parent, String message) {
        JOptionPane.showMessageDialog(
                parent,
                message,
                UIMessageConstants.errorAlertTitle,
                JOptionPane.ERROR_MESSAGE);
    }

    public static int showConfirm(JComponent parent, String message) {
        return JOptionPane.showConfirmDialog(parent, message, UIMessageConstants.confirmAlertTitle,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }
}
