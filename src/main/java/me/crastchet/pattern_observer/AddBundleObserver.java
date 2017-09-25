package me.crastchet.pattern_observer;

import java.io.File;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;


public class AddBundleObserver implements Observer {


	public void update(Observable obs) {
		
		if(obs instanceof MyObservable) {
			
			File[] newFiles = ( (MyObservable) obs ).getNewFiles();
			if( newFiles.length == 0 )
				return;
			
				
			//add the new directory files
			Set<String> currentFiles = ( (MyObservable) obs ).getBundleFileNames();
			boolean foundInFiles = false;
			for(File f : newFiles) {
				for(String s : currentFiles)
					if( foundInFiles = f.getName().compareTo( s ) == 0 )
						break;
				if( !foundInFiles )
					installBundle( (MyObservable) obs , f );
			}
					
		}
	}
	
	// actually install and launches the Bundle we got from the File f
	public void installBundle(MyObservable obs, File f) {
		try {
			Bundle bundle = obs.getContext().installBundle( " file:///" + f.getAbsolutePath() );
			obs.addBundle( f.getName() , bundle );
			System.out.println( "Bundle from " + f.getName() + " installed !");
			bundle.start();
		} catch (BundleException e) {
			if( e.getType() == BundleException.DUPLICATE_BUNDLE_ERROR )
				System.out.println( "Be careful, you're trying to add a bundle whiwh is already installed\n" + e.getMessage() );
			else
				e.printStackTrace();
		}
	}

}
