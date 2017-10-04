package me.crastchet.pattern_observer;

import java.io.File;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

public class UpdateBundleObserver implements Observer {

	
	public void update(Observable obs) {
		
		if( obs instanceof MyObservable ) {
			
			Set<String> currentFiles = ( (MyObservable) obs ).getBundleFileNames();
			if( currentFiles.isEmpty() )
				return;
			
			File[] newFiles = ( (MyObservable) obs ).getNewFiles();
			if( newFiles.length == 0 )
				return;
			
			long date;
			for(String s : currentFiles)
				for(File f : newFiles)
					if( s.compareTo( f.getName() ) == 0) {
						date = ( (MyObservable) obs ).getDate( s );
						if( f.lastModified() > date )
							updateBundle( (MyObservable)obs , f );
					}
		}
	}
	
	private void updateBundle(MyObservable obs, File file) {
		Bundle bundleToUpdate = obs.getBundle( file.getName() );
		try {
			bundleToUpdate.update();
			obs.setDate( file.getName() , file.lastModified() );
			System.out.println( "[UPDATE] Bundle " + bundleToUpdate.getSymbolicName() + " was successfully updated !" );
		} catch (BundleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
