package me.crastchet;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchService;
import java.util.List;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;


public class Activator implements BundleActivator {
	

    public void start(BundleContext context) throws Exception {
    	System.out.println("Auto-deploy hey !");	
    	run();
    }

    public void stop(BundleContext context) throws Exception {
    	System.out.println("Auto-deploy bye !");	
    }
    
    

    
    	
    	public static void run() {
	    	//define a folder root
		    Path myDir = Paths.get("/home/m2miage/coilliaux/Documents/IOS/tp1_project/my_bundles");       
		
		    try {
		       WatchService watcher = myDir.getFileSystem().newWatchService();
		       myDir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, 
		       StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
		
		       WatchKey watchKey = watcher.take();
		       watchEvents(watchKey);
	
		    } catch (Exception e) {
		        System.out.println("Error: " + e.toString());
		    }   
    	}
    	
    	public static void watchEvents(WatchKey watchKey) {
    		List<WatchEvent<?>> events = watchKey.pollEvents();
		       for (WatchEvent event : events) {
		            if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
		                System.out.println("Created: " + event.context().toString());
		            }
		            if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
		                System.out.println("Delete: " + event.context().toString());
		            }
		            if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
		                System.out.println("Modify: " + event.context().toString());
		            }
		        }
		       //watchEvents(watchKey);
    	}
 
    
    
}   