package me.crastchet.filters;

import java.io.File;
import java.io.FilenameFilter;

public class BundleFilter implements FilenameFilter {


	public boolean accept(File f, String name) {	//maybe improve it, add filter rules
		if ( !isJARFile( name ) )
			return false;
			
		return true;
	}

	
	public boolean isJARFile(String name) {
		return name.endsWith( ".jar" );
	}
}
