package tech.nocountry.eco_rent.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateEditor extends PropertyEditorSupport {
    private static final Logger logger = LoggerFactory.getLogger(CustomDateEditor.class);
    // Adjust the inputFormatter to expect the yyyy-MM-dd format
    private SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null || text.trim().isEmpty()) {
            setValue(null); // Set to null if the input string is empty or null
        } else {
            try {
                Date date = inputFormatter.parse(text);
                // Since the output format is not used for parsing input, you can directly set the parsed date
                setValue(date);
            } catch (ParseException e) {
                logger.error("Failed to parse date: " + text, e);
                throw new IllegalArgumentException("Could not parse date: " + text, e);
            }
        }
    }

    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        return (value != null ? outputFormatter.format(value) : "");
    }
}