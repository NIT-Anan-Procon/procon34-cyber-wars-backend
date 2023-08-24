package com.example.procon34_CYBER_WARS_backend.utility;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Component;

@Component
public class StringFormatter {

    // 文字列フォーマット
    public String formatString(final String unformattedString) {
        return StringEscapeUtils.escapeEcmaScript(StringEscapeUtils.escapeHtml4(unformattedString));
    }

}
