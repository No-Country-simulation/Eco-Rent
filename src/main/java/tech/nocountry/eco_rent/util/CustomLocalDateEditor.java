package tech.nocountry.eco_rent.util;

import org.springframework.beans.propertyeditors.PropertiesEditor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateEditor extends PropertiesEditor {
    private DateTimeFormatter formatter;

    public CustomLocalDateEditor(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(LocalDate.parse(text, formatter));
    }

    @Override
    public String getAsText() {
        LocalDate value = (LocalDate) getValue();
        return (value != null ? value.format(formatter) : "");
    }
}