package com.industrialworld.interfaces.world.multiblock;

// Different from Relocator, it doesn't change the value of "location" arg.
public interface Definer {
    void define(Environment env);
}
