package com.github.linyuzai.xswagger.document.writer;

import java.io.IOException;
import java.io.OutputStream;

public interface SwaggerDocumentWriter {

    void write(OutputStream os) throws IOException;
}
