package de.emsw.e4.migration;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.internal.services.BundleTranslationProvider;
import org.eclipse.e4.core.services.translation.TranslationService;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessAdditions;
import org.eclipse.fx.ui.services.restart.LifecycleRV;

public class AppLifeCycle {
	
	@PostContextCreate
	public LifecycleRV postContextCreate(IEclipseContext context) {
		LoginService loginService = ContextInjectionFactory.make(LoginService.class, context);
		boolean result = loginService.login();
		if (!result) {
			return LifecycleRV.SHUTDOWN;
		}
		return LifecycleRV.CONTINUE;
	}

	@ProcessAdditions
	public void processAdditions(MApplication application) {
		TranslationService translationProvider = new BundleTranslationProvider() {
			@Override
			public String translate(String key, String contributorURI) {
				String result = super.translate(key, contributorURI);
				return result.replace('&', '_');
			}
		};
		application.getContext().set(TranslationService.class, translationProvider);
	}
	
}
