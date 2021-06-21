package com.github.linyuzai.xswagger.node.gson;

import com.github.linyuzai.xswagger.node.SwaggerNode;
import com.github.linyuzai.xswagger.node.SwaggerNodeFactory;

public class GsonSwaggerNodeFactory implements SwaggerNodeFactory {

    @Override
    public SwaggerNode create(String json) {
        return GsonSwaggerNode.from(json);
    }
}
