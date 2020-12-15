package com.github.linyuzai.xswagger.document.writer;

import java.io.IOException;
import java.io.OutputStream;

public class MarkdownWriter implements SwaggerDocumentWriter {

    private String document;

    public MarkdownWriter(String document) {
        this.document = document;
    }

    @Override
    public void write(OutputStream os) throws IOException {
        os.write(document.getBytes());
    }

    public String document() {
        return document;
    }
}
