 
package de.emsw.e4.migration.part;

import javax.annotation.PostConstruct;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.widgets.Composite;

public class OnePart {
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		ListViewer viewer = new ListViewer(parent);
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setInput(new String[] {"Element 01", "Element 02", "Element 03"});
	}
	
}