package com.cc.plug.data;

import com.cc.plug.entity.GlobalDataEntity;

import javax.swing.table.DefaultTableModel;
import java.io.Serializable;

import static com.cc.plug.data.F.PROMPTS_HEAD;
import static com.cc.plug.util.io.PersistenceUtil.globalToObj;

public class D implements Serializable {
    public static GlobalDataEntity globalDataEntity = globalToObj();

    public static DefaultTableModel tableModel = new DefaultTableModel(null, PROMPTS_HEAD) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
}
