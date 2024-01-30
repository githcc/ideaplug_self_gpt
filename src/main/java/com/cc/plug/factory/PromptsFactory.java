package com.cc.plug.factory;

import com.cc.plug.window.PromptWindow;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class PromptsFactory implements Configurable, Disposable {
    @Override
    public void dispose() {

    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "Prompt";
    }

    @Override
    public @Nullable JComponent createComponent() {
        PromptWindow promptWindow = new PromptWindow();
        return promptWindow.getPromptJPanel();
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {

    }
}
