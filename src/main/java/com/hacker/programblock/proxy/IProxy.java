package com.hacker.programblock.proxy;

import javax.annotation.Nonnull;

interface IProxy<T> {
    @Nonnull
    T getTarget();
}
