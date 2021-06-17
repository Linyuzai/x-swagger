package com.github.linyuzai.xswagger.document.renderer;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.document.entity.SwaggerPath;
import com.github.linyuzai.xswagger.document.entity.SwaggerPathParameter;
import com.github.linyuzai.xswagger.document.entity.SwaggerPathResponse;
import com.github.linyuzai.xswagger.document.writer.MarkdownWriter;

public class MarkdownRenderer implements SwaggerDocumentRenderer {

    private StringBuilder builder = new StringBuilder();

    @Override
    public void render(SwaggerDocument document) {
        String title = document.getTitle();
        if (title != null && !title.isEmpty()) {
            builder.append("# ").append(title).append("\n");
            builder.append("\n");
        }

        builder.append("路径前缀：").append(document.getBasePath()).append("\n");
        builder.append("\n");

        for (SwaggerPath path : document.getPaths()) {
            builder.append("##### ");
            String desc = path.getDescription();
            if (desc == null || desc.trim().isEmpty()) {
                builder.append("[~~缺少描述~~]");
            } else {
                builder.append(desc);
            }
            builder.append("\n");
            builder.append("\n");
            builder.append("```\n");
            builder.append(path.getMethod().toUpperCase()).append(" ").append(path.getPath()).append("\n");
            builder.append("```\n");
            builder.append("\n");
            builder.append("**请求参数**\n");
            builder.append("\n");
            builder.append("|请求类别|参数名称|参数类型|是否必填|参数描述|\n");
            builder.append("|---|---|---|---|---|\n");
            for (SwaggerPathParameter pathParameter : path.getPathParameters()) {
                builder.append("|").append(pathParameter.getIn())
                        .append("|").append(pathParameter.getName())
                        .append("|").append(pathParameter.getType())
                        .append("|").append(pathParameter.isRequired())
                        .append("|").append(pathParameter.getDescription())
                        .append("|\n");
            }
            builder.append("\n");
            builder.append("**返回值**\n");
            builder.append("\n");
            builder.append("```");
            SwaggerPathResponse response = path.getResponse();
            if (response == null) {
                builder.append("\nnull\n");
            } else {
                String resp = response.getResponse();
                if (!resp.startsWith("\n")) {
                    builder.append("\n");
                }
                builder.append(resp).append("\n");
            }
            builder.append("```\n");
            builder.append("\n");
        }
    }

    @Override
    public MarkdownWriter writer() {
        return new MarkdownWriter(builder.toString());
    }
}
