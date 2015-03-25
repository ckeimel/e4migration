package de.emsw.e4.migration;

import javax.inject.Inject;

import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class LoginService {

	@Inject
	private Display display;
	@Inject
	private Logger logger;

	private boolean result = false;

	public boolean login() {
		Shell loginShell = createLoginShell(display);
		loginShell.open();

		while (!loginShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		return result;
	}

	private Shell createLoginShell(Display display) {
		Shell loginShell = new Shell(display);
		loginShell.setText("Login");
		loginShell.setLayout(new FillLayout());

		final Composite inputComposite = new Composite(loginShell, SWT.NONE);
		GridLayout gridLayout = GridLayoutFactory.swtDefaults().create();
		gridLayout.numColumns = 2;
		inputComposite.setLayout(gridLayout);

		final Label userLabel = new Label(inputComposite, SWT.NONE);
		userLabel.setText("Username");
		userLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));

		final Text userText = new Text(inputComposite, SWT.SINGLE | SWT.BORDER);
		userText.setText("user");
		userText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		final Label pwLabel = new Label(inputComposite, SWT.NONE);
		pwLabel.setText("Password");
		pwLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));

		final Text kennwortText = new Text(inputComposite, SWT.SINGLE | SWT.BORDER | SWT.PASSWORD);
		kennwortText.setText("test");
		kennwortText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		Composite buttonsComposite = new Composite(inputComposite, SWT.NONE);
		buttonsComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, false, false, 2, 1));
		buttonsComposite.setLayout(gridLayout);

		Button loginButton = new Button(buttonsComposite, SWT.PUSH);
		loginButton.setText("Login");
		loginButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		loginShell.setDefaultButton(loginButton);

		loginButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				if (userText.getText() == null || userText.getText().equals("")) {
					MessageDialog.openError(loginShell, "Input Error", "Username must be filled");
					userText.setFocus();
					return;
				}
				if (kennwortText.getText() == null || kennwortText.getText().equals("")) {
					MessageDialog.openError(loginShell, "Input Error", "Password must be filled");
					kennwortText.setFocus();
					return;
				}
				String message = NLS.bind("User \"{0}\" has succesfully logged in", userText.getText());
				logger.info(message);
				result = true;
				loginShell.close();
			}
		});

		Button cancelButton = new Button(buttonsComposite, SWT.PUSH);
		cancelButton.setText("Cancel");
		cancelButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		cancelButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				logger.info("Login cancelled");
				result = false;
				loginShell.close();
			}
		});

		loginShell.setSize(400, 150);
		return loginShell;
	}

}
