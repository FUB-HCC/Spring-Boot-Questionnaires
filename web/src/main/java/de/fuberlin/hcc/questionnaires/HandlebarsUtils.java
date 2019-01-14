package de.fuberlin.hcc.questionnaires;

import com.github.jknack.handlebars.Options;
import com.google.common.base.Objects;
import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper;

import java.io.IOException;

@HandlebarsHelper
public class HandlebarsUtils {
    public CharSequence equals(final Object obj1, final Options options) throws IOException {
        Object obj2 = options.param(0);
        return Objects.equal(obj1, obj2) ? options.fn() : options.inverse();
    }

    public String checked(final Object obj1, final Object obj2) {
        if (Objects.equal(obj1, obj2)) {
            return "checked";
        } else {
            return "";
        }
    }

    public CharSequence even(final Object obj1, final Options options) throws IOException {
        int number = (int) obj1;
        return (number % 2 == 0) ? options.fn() : options.inverse();
    }

}
