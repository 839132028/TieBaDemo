package edu.tstc.yy;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by w_2 on 2016-12-07.
 */
public class MyCustomDateEditor extends PropertyEditorSupport {
    private final SimpleDateFormat dateFormat;
    private final boolean allowEmpty;
    private final int exactDateLength;

    public MyCustomDateEditor(SimpleDateFormat dateFormat, boolean allowEmpty) {
        this.dateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
        this.exactDateLength = -1;
    }

    public MyCustomDateEditor(SimpleDateFormat dateFormat, boolean allowEmpty, int exactDateLength) {
        this.dateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
        this.exactDateLength = exactDateLength;
    }

    public void setAsText(String text) throws IllegalArgumentException {
        if(this.allowEmpty && !StringUtils.hasText(text)) {
            this.setValue((Object)null);
        } else {
            if(text != null && this.exactDateLength >= 0 && text.length() != this.exactDateLength) {
                throw new IllegalArgumentException("Could not parse date: it is not exactly" + this.exactDateLength + "characters long");
            }

            try {
                this.setValue(this.dateFormat.parse(text));
            } catch (ParseException var3) {
                throw new IllegalArgumentException("Could not parse date: " + var3.getMessage(), var3);
            }
        }

    }

    public String getAsText() {
        Date value = (Date)this.getValue();
        return value != null?this.dateFormat.format(value):"";
    }
}
