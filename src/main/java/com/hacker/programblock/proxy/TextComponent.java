package com.hacker.programblock.proxy;

import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nonnull;
import java.util.function.UnaryOperator;

@SuppressWarnings("all")
public class TextComponent implements IProxy<IFormattableTextComponent> {
    private final IFormattableTextComponent target;

    public TextComponent(IFormattableTextComponent target) {
        this.target = target;
    }

    @Nonnull
    @Override
    public IFormattableTextComponent getTarget() {
        return target;
    }

    public TextComponent append(ITextComponent sibling) {
        target.append(sibling);
        return this;
    }

    public TextComponent append(TextComponent sibling) {
        target.append(sibling.target);
        return this;
    }

    public TextComponent modifyStyle(UnaryOperator<Style> modifyFunc) {
        target.modifyStyle(modifyFunc);
        return this;
    }

    public TextComponent mergeStyle(Style style) {
        target.mergeStyle(style);
        return this;
    }

    public TextComponent mergeStyle(TextFormatting... formats) {
        target.mergeStyle(formats);
        return this;
    }

    public TextComponent mergeStyle(TextFormatting format) {
        target.mergeStyle(format);
        return this;
    }
}
