
package org.apache.jmeter.protocol.sas.login.sampler;

import com.sas.iom.WorkspaceConnector;
import com.sas.iom.WorkspaceFactory;
import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testbeans.TestBean;
import singleton.ClassicSingleton;

import java.util.Properties;

public class SASLoginSampler extends AbstractSampler implements TestBean {

    private String hostname = "";
    private int port = 8591;
    private String username = "";
    private String password = "";
    private int connectionTimeout = 10000;

    private String failureReason = "Unknown";
    private WorkspaceConnector workspaceConnector = null;
    private WorkspaceFactory workspaceFactory = null;

    public SASLoginSampler() {
        super();
        setName("SAS Login Sampler");
    }

    public String connect() {

        StringBuilder sb = new StringBuilder();

        System.out.println("CONNECTING TO WORKSPACE");
        
        try {

            failureReason = "Unknown";

            Properties iomServerProperties = new Properties();

            iomServerProperties.put("host", "CLO-UAT-SAS");

            iomServerProperties.put("port", "8591");

            iomServerProperties.put("userName", "sasdemo");

            iomServerProperties.put("password", "Student1");

            Properties[] serverList = {iomServerProperties};

            workspaceFactory = new WorkspaceFactory(serverList,null,null);

            workspaceConnector = workspaceFactory.getWorkspaceConnector(0L);

            ClassicSingleton.getInstance().putWorkspaceConnector(Thread.currentThread().getName(),workspaceConnector);

            sb.append("CONNECTED");

            System.out.println("CONNECTED");

        } catch (Exception e) {
            failureReason = (e==null?"exception":e.getMessage());
            if (workspaceFactory!=null) workspaceFactory.shutdown();
            if (workspaceConnector!=null) workspaceConnector.close();
            workspaceConnector = null;
            if (e!=null) e.printStackTrace();
        }

        return sb.toString();
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String server) {
        this.hostname = server;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
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

        res.setSamplerData(command);

        res.setDataType(SampleResult.TEXT);

        res.setContentType("text/plain");

        String response;


        try {

            res.sampleStart();

            response = connect();

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

    private String command;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
