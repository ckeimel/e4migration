package de.emsw.e4.migration;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;

public class AppLifeCycle {
	
	@PostContextCreate
	public void postContextCreate(IEclipseContext context) {
		LoginService loginService = ContextInjectionFactory.make(LoginService.class, context);
		boolean result = loginService.login();
		if (!result) {
			System.exit(0);
		}
	}
	
}
