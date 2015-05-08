package singleton;

import com.sas.iom.WorkspaceConnector;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Leonar Tambunan on 4/25/2015.
 */
public class ClassicSingleton {

    private static ClassicSingleton instance = null;

    private static Map<String,WorkspaceConnector> map  = new HashMap<String, WorkspaceConnector>();


    protected ClassicSingleton() {
        // Exists only to defeat instantiation.
    }
    public static ClassicSingleton getInstance() {
        if(instance == null) {
            instance = new ClassicSingleton();
        }
        return instance;
    }

    public WorkspaceConnector getWorkspaceConnector(String threadName) {
        return map.get(threadName);
    }

    public WorkspaceConnector putWorkspaceConnector(String threadName, WorkspaceConnector workspaceConnector) {
        return map.put(threadName,workspaceConnector);
    }


}
