package UserInterface;

import javax.swing.*;
import org.jdatepicker.impl.JDatePickerImpl;

public class FormField {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JDatePickerImpl datePicker;
    private JRadioButton buttonGroup;
    private JCheckBox checkBox;

    public FormField(){}

    public FormField(JTextField textField1, JTextField textField2, JTextField textField3, JDatePickerImpl datePicker, JRadioButton buttonGroup, JCheckBox checkBox) {
        this.textField1 = textField1;
        this.textField2 = textField2;
        this.textField3 = textField3;
        this.datePicker = datePicker;
        this.buttonGroup = buttonGroup;
        this.checkBox = checkBox;
    }

    // Getters and setters for each field
    public JTextField getFirstName() {
        return textField1;
    }
    public void setFirstName(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JTextField getLastName() {
        return textField2;
    }
    public void setLastName(JTextField textField2) {
        this.textField2 = textField2;
    }

    public JTextField getPhone() {
        return textField3;
    }
    public void setPhone(JTextField textField2) {
        this.textField3 = textField3;
    }

    public JDatePickerImpl getDOB() {
        return datePicker;
    }
    public void setDOB(JDatePickerImpl datePicker) {
        this.datePicker = datePicker;
    }

    public JRadioButton getButtonGroup() {
        return buttonGroup;
    }
    public void setButtonGroup(JRadioButton buttonGroup) {
        this.buttonGroup = buttonGroup;
    }

    public JCheckBox getIsTurkish() {
        return checkBox;
    }
    public void setIsTurkish(JCheckBox checkBox) {
        this.checkBox = checkBox;
    }
}
