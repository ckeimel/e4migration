 
package de.emsw.e4.migration.handler;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.fx.runtime.swt.e4.SWTServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class MessageHandler {
	@Execute
	public void execute(@Named(SWTServiceConstants.ACTIVE_SHELL) Shell shell) {
		MessageDialog.openInformation(shell, "Message", "Hello World!");
	}
}