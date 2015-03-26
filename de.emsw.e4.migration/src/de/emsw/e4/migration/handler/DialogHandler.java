 
package de.emsw.e4.migration.handler;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.widgets.Shell;

import de.emsw.e4.migration.dialog.SampleTitleAreaDialog;

public class DialogHandler {
	
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell) {
		SampleTitleAreaDialog dialog = new SampleTitleAreaDialog(shell);
		dialog.open();
	}
	
}