package me.crastchet.pattern_observer;

import java.io.File;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;


public class AddBundleObserver implements Observer {


	public void update(Observable obs) {
		
		if(obs instanceof MyObservable) {
			//add the new directory files
			boolean foundInFiles = false;
			for(File f : ( (MyObservable) obs ).getNewFiles()) {
				for(File f2 : ( (MyObservable) obs ).getFiles())
					if( foundInFiles = f.equals( f2 ) )
						break;
				if( !foundInFiles )
					installBundle( (MyObservable) obs , f );
			}
					
		}
	}
	
	public void installBundle(MyObservable obs, File f) {
		try {
			Bundle bundle = obs.getContext().installBundle( " file:///" + f.getAbsolutePath() );
			obs.addBundle( bundle );
			System.out.println( "Bundle from " + f.getName() + " installed !");
		} catch (BundleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
