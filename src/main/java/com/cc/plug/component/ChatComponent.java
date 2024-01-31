package com.cc.plug.component;

import com.intellij.notification.impl.ui.NotificationsUtil;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.panels.VerticalLayout;
import com.intellij.util.ui.HtmlPanel;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class ChatComponent extends JBPanel<ChatComponent> {
    public ChatComponent(String content, int color, int colorDark) {
        setOpaque(true);
        setBackground(new JBColor(color, colorDark));
        setBorder(JBUI.Borders.empty(10));
        setLayout(new BorderLayout(JBUI.scale(8), 1));

        JPanel centerPanel = new JPanel(new VerticalLayout(JBUI.scale(8)));
        centerPanel.setOpaque(false);
        centerPanel.add(createMessage(content));
        add(centerPanel, BorderLayout.CENTER);
    }

    public Component createMessage(String content) {
        HtmlPanel component = new HtmlPanel() {
            private String text;
            @Override
            protected @NotNull @Nls String getBody() {
                return text == null? "":text;
            }
            @Override
            public void setBody(@NotNull @Nls String text) {
                this.text = text;
                super.setBody(text);
            }
        };
        component.setEditable(false);
        NotificationsUtil.configureHtmlEditorKit(component, false);
        component.setBody(content);
        component.revalidate();
        component.repaint();
        return component;
    }
}
