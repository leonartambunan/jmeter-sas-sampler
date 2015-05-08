package org.apache.jmeter.protocol.sas.login.sampler;

import org.apache.jmeter.protocol.sas.lang.sampler.SASSampler;
import org.apache.jmeter.testbeans.gui.TextAreaEditor;
import org.apache.jmeter.testbeans.gui.TypeEditor;

import java.beans.PropertyDescriptor;

public class SASLoginSamplerBeanInfo extends AbstractSASLoginSamplerBeanInfo {

    public SASLoginSamplerBeanInfo() {
        
        super(SASLoginSampler.class);
        
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
