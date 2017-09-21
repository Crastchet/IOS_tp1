package me.crastchet;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import me.crastchet.pattern_observer.MyObserver;
import me.crastchet.pattern_observer.MyObservable;


public class Activator implements BundleActivator {

	MyObservable myObservable;
	
    public void start(BundleContext context) throws Exception {
        myObservable = new MyObservable( "/home/thibault/School/IOS/auto-deploy/my_bundles/" );
        MyObserver myObserver = new MyObserver();
        myObservable.addObserver(myObserver);
        myObservable.init();
    }

    public void stop(BundleContext context) throws Exception {
        myObservable.stop();
    }

}
