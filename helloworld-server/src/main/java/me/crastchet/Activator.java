package me.crastchet;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

    private ServiceRegistration sAck;

    public void start(BundleContext context) throws Exception {
        Service s = new Server();
    	  sAck = context.registerService(Service.class.getName(), s, null);
    	  System.out.println("Bundle starts...");
    }

    public void stop(BundleContext context) throws Exception {
        sAck.unregister();
    	System.out.println("Bundle stops...");
    }



    public interface Service {
        void printMessage(String msg);
    }


    public class Server implements Service {
        public void printMessage(String msg) {
            System.out.println(">> "+msg);
        }
    }


}
