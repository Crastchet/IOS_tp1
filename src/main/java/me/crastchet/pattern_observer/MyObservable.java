package me.crastchet.pattern_observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Timer;

import org.osgi.framework.Bundle;

import me.crastchet.filters.BundleFilter;



public class MyObservable extends Observable {
	
	private File directory;			//directory to watch
	private ArrayList<File> files;
	private ArrayList<Bundle> bundles;
	private BundleFilter filter;	//enables to filter bundle files (at least .jar files)
	private Timer timer;			//enables to do the check every X seconds

	
	public MyObservable( String path ) {
		super();
		directory = new File( path );
		System.out.println(directory.getAbsolutePath());
		System.out.println(directory.isDirectory());
		files = new ArrayList<File>();
		//bundles = new ArrayList<Bundle>();
		
		filter = new BundleFilter();
		timer = new Timer(1000, new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateFilesList();				
			}
		});
		
	}
	
	
	public void updateFilesList() {
		//if there is no file in our list nor in our directory, we can stop here
		if( files.isEmpty() && directory.listFiles( filter ).length == 0 )
			return;
		
		ArrayList<File> tmpList = new ArrayList<File>();

		//add the new directory files
		boolean foundInFiles = false;
		for(File f : directory.listFiles( filter )) {
			for(File f2 : files)
				if( foundInFiles = f.equals( f2 ) )
					break;
			if( !foundInFiles )
				tmpList.add( f );
		}
		files.addAll( tmpList );
		
		tmpList.clear();
		//remove the missing directory files
		boolean foundInDirectory = false;
		for(File f : files) {
			for(File f2 : directory.listFiles( filter ))
				if( foundInDirectory = f.equals( f2 ) )
					break;
			if( !foundInDirectory )
				tmpList.add( f );
		}
		files.removeAll( tmpList );
		
		//notify observers if the content of directory has changed
		if( !foundInFiles || !foundInDirectory )
			notifyObservers();
		
	}

	@Override
	public void init() {
		timer.start();
	}
	
	@Override
	public void stop() {
		timer.stop();
	}

	

}
