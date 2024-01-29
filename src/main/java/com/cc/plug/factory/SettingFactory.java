package com.cc.plug.factory;

import com.cc.plug.window.SettingWindow;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SettingFactory implements Configurable, Disposable {
    @Override
    public void dispose() {

    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "Gpt";
    }

    @Override
    public @Nullable JComponent createComponent() {
        SettingWindow settingWindow = new SettingWindow();
        return settingWindow.getSettingJPanel();
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {

    }
}
