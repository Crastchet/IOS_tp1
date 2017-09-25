package me.crastchet.pattern_observer;

import java.io.File;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

public class RemoveBundleObserver implements Observer {

	
	public void update(Observable obs) {
		if(obs instanceof MyObservable) {
			//remove the missing directory files
			
			Set<String> currentFiles = ( (MyObservable) obs ).getBundleFileNames();
			if( currentFiles.isEmpty() )
				return;
			
			File[] newFiles = ( (MyObservable) obs ).getNewFiles();
			boolean foundInDirectory = false;
			for(String s : currentFiles) {
				for(File f : newFiles)
					if( foundInDirectory = s.compareTo( f.getName() ) == 0 )
						break;
				if( !foundInDirectory ) 
					uninstallBundle( (MyObservable) obs, s );
			}
		}
		
	}
	
	private void uninstallBundle(MyObservable obs, String s) { // right now, the bundles are removed depending on their file name
		Bundle bundle =  obs.getBundle( s ); // here is where we're gonna find the File, according to its name
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

}
