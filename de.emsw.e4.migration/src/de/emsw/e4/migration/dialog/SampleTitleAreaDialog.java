package de.emsw.e4.migration.dialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SampleTitleAreaDialog extends TitleAreaDialog {
	
	public SampleTitleAreaDialog(Shell parentShell) {
		super(parentShell);
	}
	
	@Override
	public void create() {
		super.create();
		this.getShell().setText("TitleAreaDialog Test");
		this.setTitle("TitleAreaDialog Test");
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogArea = (Composite) super.createDialogArea(parent);
		
		final Composite panel = new Composite(dialogArea, SWT.NONE);
		panel.setLayout(GridLayoutFactory.swtDefaults().create());
		panel.setLayoutData(new GridData(GridData.FILL_BOTH));
		createInputArea(panel);
		
		Label titleBarSeparator = new Label(dialogArea, SWT.HORIZONTAL | SWT.SEPARATOR);
		titleBarSeparator.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		return dialogArea;
	}

	private void createInputArea(Composite parent) {
		Text text = new Text(parent, SWT.BORDER | SWT.MULTI);
		text.setLayoutData(new GridData(GridData.FILL_BOTH));
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.CLOSE_ID, IDialogConstants.CLOSE_LABEL, false);
	}
	
	@Override
	protected void buttonPressed(int buttonId) {
		switch (buttonId) {
		case IDialogConstants.CLOSE_ID:
			cancelPressed();
		}
	}
	
}
