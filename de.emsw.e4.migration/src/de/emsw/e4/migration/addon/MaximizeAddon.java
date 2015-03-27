package de.emsw.e4.migration.addon;

import java.util.Arrays;

import javafx.stage.Stage;

import javax.annotation.PostConstruct;

import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.fx.ui.workbench.renderers.base.widget.WWindow;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

/**
 * AddOn to maximize the first window that is opended by the application
 */
public class MaximizeAddon {

	public static final String APP_ARG_NO_MAXIMIZE = "-noMaximize"; //$NON-NLS-1$
	
	@PostConstruct
	public void init(final IEventBroker eventBroker) {
		EventHandler handler = new EventHandler() {
			@Override
			public void handleEvent(Event event) {
				if (!UIEvents.isSET(event))
					return;
				
				Object objElement = event.getProperty(UIEvents.EventTags.ELEMENT);
				if (!(objElement instanceof MWindow))
					return;
	
				MWindow windowModel = (MWindow) objElement;
				WWindow<?> windowImpl = (WWindow<?>) windowModel.getWidget();
				Stage theStage = (Stage) windowImpl.getWidget();
				if (theStage == null)
					return;
				theStage.setMaximized(true);
				
				eventBroker.unsubscribe(this);
			}
		};
		
		if (!Arrays.asList(Platform.getApplicationArgs()).contains(APP_ARG_NO_MAXIMIZE)) {
			eventBroker.subscribe(UIEvents.UIElement.TOPIC_WIDGET, handler);
		}
	}
	
}
