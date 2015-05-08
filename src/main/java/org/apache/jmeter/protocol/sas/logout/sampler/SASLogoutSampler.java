
package org.apache.jmeter.protocol.sas.logout.sampler;

import com.sas.iom.WorkspaceConnector;
import com.sas.iom.WorkspaceFactory;
import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testbeans.TestBean;
import singleton.ClassicSingleton;

public class SASLogoutSampler extends AbstractSampler implements TestBean {

    private String failureReason = "Unknown";
    private WorkspaceConnector workspaceConnector = null;
    private WorkspaceFactory workspaceFactory = null;

    public SASLogoutSampler() {
        super();
        setName("SAS Logout Sampler");
    }



    public String disconnect() {

        StringBuilder sb = new StringBuilder();

        sb.append("Disconnecting");

        try {
            workspaceConnector = ClassicSingleton.getInstance().getWorkspaceConnector(Thread.currentThread().getName());

            if (workspaceConnector != null && workspaceConnector.getWorkspace() != null) {
                workspaceConnector.getWorkspace().Close();
            }

            if (workspaceConnector != null) {
                workspaceConnector.close();
            }

            sb.append("DISCONNETED");

        } catch (Exception e) {
            sb.append(e.getMessage());
        }

        return sb.toString();
    }

    protected String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public WorkspaceFactory getWorkspaceFactory() {
        return workspaceFactory;
    }

    public void setWorkspaceFactory(WorkspaceFactory workspaceFactory) {
        this.workspaceFactory = workspaceFactory;
    }

    @Override
    public void finalize() {
        try {
            super.finalize();
        } catch (Throwable e) {
            System.out.println("SAS finalize error");
        } finally {
            if (workspaceFactory != null) {
                workspaceFactory.shutdown();
                workspaceFactory = null;
            }
            if (workspaceConnector != null) {
                workspaceConnector.close();
                workspaceConnector = null;
            }

        }
    }

    public SampleResult sample(Entry e) {

        SampleResult res = new SampleResult();

        res.setSampleLabel(getName());

        res.setSamplerData("logout");

        res.setDataType(SampleResult.TEXT);
        res.setContentType("text/plain");

        String response;


        try {

            res.sampleStart();

            response = disconnect();

            res.sampleEnd();

            res.setResponseData(response.getBytes());

            res.setSuccessful(true);

            res.setResponseMessageOK();

        } catch (NullPointerException e1) {
            res.setSuccessful(false);
            res.setResponseCode("NullPointerException");
            res.setResponseMessage(e1.getMessage());
            res.setResponseData(e1.getMessage().getBytes());
        } catch (Exception e1) {
            res.setSuccessful(false);
            res.setResponseCode("Exception");
            res.setResponseMessage(e1.getMessage());
            res.setResponseData(e1.getMessage().getBytes());
        } finally {
//            disconnect();
        }
        return res;
    }
}
