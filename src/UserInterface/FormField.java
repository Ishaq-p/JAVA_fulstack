package UserInterface;

import javax.swing.*;
import org.jdatepicker.impl.JDatePickerImpl;

public class FormField {
    private JTextField textField1;
    private JTextField textField2;
    private JDatePickerImpl datePicker;
    private ButtonGroup buttonGroup;
    private JCheckBox checkBox;

    public FormField(){}

    public FormField(JTextField textField1, JTextField textField2, JDatePickerImpl datePicker, ButtonGroup buttonGroup, JCheckBox checkBox) {
        this.textField1 = textField1;
        this.textField2 = textField2;
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

    public JDatePickerImpl getDOB() {
        return datePicker;
    }
    public void setDOB(JDatePickerImpl datePicker) {
        this.datePicker = datePicker;
    }

    public ButtonGroup getButtonGroup() {
        return buttonGroup;
    }
    public void setButtonGroup(ButtonGroup buttonGroup) {
        this.buttonGroup = buttonGroup;
    }

    public JCheckBox getIsTurkish() {
        return checkBox;
    }
    public void setIsTurkish(JCheckBox checkBox) {
        this.checkBox = checkBox;
    }
}
