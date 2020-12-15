package com.github.linyuzai.xswagger.locator.array;

import com.github.linyuzai.xswagger.locator.AbstractLocator;

import java.util.Arrays;

public class ArrayLocator extends AbstractLocator {

    public ArrayLocator(String... locations) {
        super(Arrays.asList(locations));
    }
}
