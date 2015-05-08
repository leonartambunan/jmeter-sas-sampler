package org.apache.jmeter.protocol.sas.lang.sampler;

import org.apache.jmeter.testbeans.gui.TextAreaEditor;
import org.apache.jmeter.testbeans.gui.TypeEditor;

import java.beans.PropertyDescriptor;

public class SASSamplerBeanInfo extends AbstractSASamplerBeanInfo {

    public SASSamplerBeanInfo() {
        
        super(SASSampler.class);
        
        createPropertyGroup("sasLanguage", new String[]{"command"});
        
        PropertyDescriptor p = property("command", TypeEditor.TextAreaEditor);
        p.setValue(NOT_UNDEFINED, Boolean.TRUE);
        p.setValue(DEFAULT,
                "pro sql;\n" +
                "quit;");

        p.setPropertyEditorClass(TextAreaEditor.class);
        p.setValue(TEXT_LANGUAGE, "command");  // $NON-NLS-1$

    }
    
}
