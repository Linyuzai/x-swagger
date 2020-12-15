package com.github.linyuzai.xswagger.document.renderer;

import com.github.linyuzai.xswagger.document.entity.SwaggerDocument;
import com.github.linyuzai.xswagger.document.writer.SwaggerDocumentWriter;

public interface SwaggerDocumentRenderer {

    void render(SwaggerDocument document);

    SwaggerDocumentWriter writer();
}
