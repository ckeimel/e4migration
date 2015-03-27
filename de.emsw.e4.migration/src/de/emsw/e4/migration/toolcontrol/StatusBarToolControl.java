package de.emsw.e4.migration.toolcontrol;

import javax.annotation.PostConstruct;

import org.eclipse.jface.dialogs.ProgressIndicator;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class StatusBarToolControl {
	
	@PostConstruct
	public void createControls(Composite parent) {
		Text textField = new Text(parent, SWT.SINGLE | SWT.READ_ONLY);
		textField.setText("No current status information available ...");
		
		ProgressIndicator progressBar = new ProgressIndicator(parent);
		progressBar.beginTask(100);
		progressBar.worked(42);
		
		GridLayout layout = GridLayoutFactory.swtDefaults().create();
		layout.numColumns = 2;
		parent.setLayout(layout);
		textField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		progressBar.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, true));
	}
	
}