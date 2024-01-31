package com.cc.plug.factory;

import com.cc.plug.window.SettingWindow;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SettingFactory implements Configurable, Disposable {

    SettingWindow settingWindow = new SettingWindow();
    @Override
    public void dispose() {
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "Gpt";
    }

    @Override
    public @Nullable JComponent createComponent() {
        return settingWindow.getSettingJPanel();
    }

    @Override
    public boolean isModified() {
        return settingWindow.isModified();
    }

    @Override
    public void apply() {
        settingWindow.apply();
    }

    @Override
    public void reset() {
        settingWindow.reset();
    }
}
