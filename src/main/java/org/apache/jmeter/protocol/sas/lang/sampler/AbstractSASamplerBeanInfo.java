
package org.apache.jmeter.protocol.sas.lang.sampler;

import java.beans.PropertyDescriptor;

import org.apache.jmeter.protocol.sas.lang.sampler.SASSampler;
import org.apache.jmeter.testbeans.BeanInfoSupport;

public abstract class AbstractSASamplerBeanInfo extends BeanInfoSupport {

    public AbstractSASamplerBeanInfo(Class<? extends SASSampler> clazz) {
        super(clazz);

        createPropertyGroup("server", // $NON-NLS-1$
                new String[]{
                    "hostname", // $NON-NLS-1$
                    "port", // $NON-NLS-1$
                    "connectionTimeout"
                });

        createPropertyGroup("user", // $NON-NLS-1$
                new String[]{
                    "username", // $NON-NLS-1$
                    "password" // $NON-NLS-1$
                });


        PropertyDescriptor p;
        p = property("username"); // $NON-NLS-1$
        p.setValue(NOT_UNDEFINED, Boolean.TRUE);
        p.setValue(DEFAULT, "sasdemo");

        p = property("password"); // $NON-NLS-1$
        p.setValue(NOT_UNDEFINED, Boolean.TRUE);
        p.setValue(DEFAULT, "Student1");

        p = property("hostname"); // $NON-NLS-1$
        p.setValue(NOT_UNDEFINED, Boolean.TRUE);
        p.setValue(DEFAULT, "CLO-UAT-SAS");

        p = property("port"); // $NON-NLS-1$
        p.setValue(NOT_UNDEFINED, Boolean.TRUE);
        p.setValue(DEFAULT, 8591);

        p = property("connectionTimeout");
        p.setValue(NOT_UNDEFINED, Boolean.TRUE);
        p.setValue(DEFAULT, 10000);

    }
}
