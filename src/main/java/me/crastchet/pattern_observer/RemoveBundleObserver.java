package me.crastchet.pattern_observer;

import java.io.File;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

public class RemoveBundleObserver implements Observer {

	
	public void update(Observable obs) {
		if(obs instanceof MyObservable) {
			//remove the missing directory files
			boolean foundInDirectory = false;
			for(File f : ( (MyObservable) obs ).getFiles()) {
				for(File f2 : ( (MyObservable) obs ).getNewFiles())
					if( foundInDirectory = f.equals( f2 ) )
						break;
				if( !foundInDirectory ) 
					uninstallBundle( (MyObservable) obs, f );
			}
			
		}
		
	}
	
	private void uninstallBundle(MyObservable obs, File f) {
		Bundle bundle = findBundle( obs, f );
		if( bundle == null) {
			System.out.println( "That is weird, we can't find the bundle in progress" );
			return;
		}
		try {
			bundle.stop();
			bundle.uninstall();
			System.out.println( "Bundle " + bundle.getSymbolicName() + " uninstalled !" );
		} catch (BundleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Bundle findBundle(MyObservable obs, File f) {
		for(Bundle b : obs.getBundles()) {
			System.out.println( b.getSymbolicName() );
			if( b.getSymbolicName().compareTo( f.getName() ) == 0 )
				return b;
		}
		return null;
	}

}
