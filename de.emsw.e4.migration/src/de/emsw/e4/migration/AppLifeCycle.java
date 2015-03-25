package de.emsw.e4.migration;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.e4.ui.workbench.lifecycle.PreSave;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessAdditions;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessRemovals;
import org.eclipse.e4.ui.workbench.modeling.EModelService;

/*
 * @PostContextCreate will be called after the IEclipseContext is created
 * @ProcessAdditions will be called directly before the model is passed to the renderer, can be used
 * to add additional elements to the model
 * @ProcessRemovals Same as above but for removals
 * @Presave Will be called before save
 */
public class AppLifeCycle {
	
	@Inject
	private Logger logger;
	
	@PostContextCreate
	public void postContextCreate(IEclipseContext context) {
		logger.info("AppLifeCycle: @PostContextCreate");
		
		LoginService loginService = ContextInjectionFactory.make(LoginService.class, context);
		boolean result = loginService.login();
		if (!result) {
			System.exit(0);
		}
	}

	@ProcessAdditions
	public void processAdditions() {
		logger.info("AppLifeCycle: @ProcessAdditions");
	}
	
	@ProcessRemovals
	public void processRemovals(MApplication application, EModelService modelService) {
		logger.info("AppLifeCycle: @ProcessRemovals");
	}
	
	@PreSave
	public void preSave() {
		logger.info("AppLifeCycle: @PreSave");
	}
}
