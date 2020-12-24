package com.github.linyuzai.xswagger.handler;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.document.entity.SwaggerPathResponse;
import com.github.linyuzai.xswagger.node.SwaggerNode;

public class PathsResponsesHandler extends AbstractSwaggerHandler {

    public PathsResponsesHandler() {
        super("paths", "*", "*", "responses", "*", "*", "$ref");
    }

    @Override
    public void handle(SwaggerDocument document, SwaggerNode node) {
        SwaggerNode def = document.getDefinition(node.stringValue());
        getPath(document, node).setResponse(new SwaggerPathResponse(format(def.toResponseJson())));
    }

    public static String format(String json) {
        if (json == null) {
            return null;
        }
        int level = 0;
        StringBuilder builder = new StringBuilder("\n");
        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            if (level > 0 && '\n' == builder.charAt(builder.length() - 1)) {
                for (int j = 0; j < level; j++) {
                    builder.append("\t");
                }
            }
            switch (c) {
                case '{':
                case '[':
                    builder.append(c).append("\n");
                    level++;
                    break;
                case ',':
                    builder.append(c).append("\n");
                    break;
                case '}':
                case ']':
                    builder.append("\n");
                    level--;
                    for (int j = 0; j < level; j++) {
                        builder.append("\t");
                    }
                    builder.append(c);
                    break;
                default:
                    builder.append(c);
                    break;
            }
        }

        return builder.toString();
    }
}
