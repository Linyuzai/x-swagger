package com.github.linyuzai.xswagger.node.jackson;

import com.github.linyuzai.xswagger.node.SwaggerNode;
import com.github.linyuzai.xswagger.node.SwaggerNodeFactory;

public class JacksonSwaggerNodeFactory implements SwaggerNodeFactory {

    @Override
    public SwaggerNode create(String json) {
        return JacksonSwaggerNode.from(json);
    }
}
