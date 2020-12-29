package com.github.linyuzai.xswagger.document.handler;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.document.entity.SwaggerPath;
import com.github.linyuzai.xswagger.exception.XSwaggerException;

import java.util.regex.Pattern;

public class PathsDescriptionReplaceAllHandler implements SwaggerDocumentHandler {

    private Pattern pattern;

    private String regex;

    private String replacement;

    public PathsDescriptionReplaceAllHandler(String regex, String replacement) {
        if (regex == null || regex.isEmpty()) {
            throw new XSwaggerException("Contains can not be null or empty");
        }
        if (replacement == null) {
            throw new XSwaggerException("Replacement can not be null");
        }
        this.regex = regex;
        this.replacement = replacement;
        this.pattern = Pattern.compile(regex, Pattern.LITERAL);
    }

    @Override
    public void handle(SwaggerDocument document) {
        for (SwaggerPath path : document.getPaths()) {
            String desc = path.getDescription();
            if (pattern.matcher(desc).matches()) {
                path.setDescription(desc.replaceAll(regex, replacement));
            }
        }
    }
}
