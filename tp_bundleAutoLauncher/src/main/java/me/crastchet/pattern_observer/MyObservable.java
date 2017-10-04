package me.crastchet.pattern_observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.swing.Timer;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import me.crastchet.filters.BundleFilter;


public class MyObservable extends Observable {
	
	private BundleContext context;				//our context
	private File directory;						//directory to watch
	
	private File[]  newFiles;					//where we store the new scanned directory files
	private HashMap<String, Bundle> bundles;	//bundles linked to a filename.jar
	private HashMap<String, Long> dates;		//dates (as a Long) linked to a filename.jar
	
	private BundleFilter filter;				//enables to filter bundle files (at least .jar files)
	private Timer timer;						//enables to do the check every X seconds

	
	public MyObservable( BundleContext ctxt , String path ) {
		super();
		context = ctxt;
		directory = new File( path );
		
		bundles = new HashMap<String, Bundle>();
		dates = new HashMap<String, Long>();
		
		filter = new BundleFilter();
		timer = new Timer(1000, new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				newFiles = directory.listFiles( filter );
				notifyObservers();
			}
		});
		
	}
	


	@Override
	public void init() {
		timer.start();
	}
	
	@Override
	public void stop() {
		timer.stop();
	}
	
	public void addBundle(File file, Bundle bundle) {
		this.bundles.put( file.getName() , bundle );
		// also we save the date of the .jar file in order to be able to check whether we always use the last release
		this.dates.put( file.getName() , file.lastModified() );
	}
	
	public void removeBundle(String name) {
		this.bundles.remove( name );
	}
	
	public Set<String> getBundleFileNames()  {
		return this.bundles.keySet();
	}
	
	public File[] getNewFiles() {
		return this.newFiles;
	}
	
	public Bundle[] getBundles() {
		return (Bundle[]) this.bundles.values().toArray();
	}
	
	public Bundle getBundle(String s) {
		return this.bundles.get( s );
	}
	
	public long getDate(String s) {
		return this.dates.get( s );
	}
	
	public void setDate(String s, long date) {
		this.dates.put( s , date );
	}
	
	public BundleContext getContext() {
		return this.context;
	}

}
