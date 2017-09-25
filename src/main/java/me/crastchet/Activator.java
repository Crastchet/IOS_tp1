package me.crastchet;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import me.crastchet.pattern_observer.AddBundleObserver;
import me.crastchet.pattern_observer.MyObservable;
import me.crastchet.pattern_observer.RemoveBundleObserver;
import me.crastchet.pattern_observer.UpdateBundleObserver;


public class Activator implements BundleActivator {

	MyObservable myObservable;
	
    public void start(BundleContext context) throws Exception {
    	// Ubuntu MAISON : /home/thibault/School/IOS/auto-deploy/my_bundles/
    	// Windows PORTABLE : E:\\Code\\Java\\Workspace_IOS\\auto-deploy\\my_bundles
        myObservable = new MyObservable( context, "//home/thibault/School/IOS/helloworld-bundle/target" );
        AddBundleObserver addObserver = new AddBundleObserver();
        RemoveBundleObserver remObserver = new RemoveBundleObserver();
        UpdateBundleObserver updObserver = new UpdateBundleObserver();
        myObservable.addObserver(addObserver);
        myObservable.addObserver(remObserver);
        myObservable.addObserver(updObserver);
        myObservable.init();
    }

    public void stop(BundleContext context) throws Exception {
        myObservable.stop();
    }

}
