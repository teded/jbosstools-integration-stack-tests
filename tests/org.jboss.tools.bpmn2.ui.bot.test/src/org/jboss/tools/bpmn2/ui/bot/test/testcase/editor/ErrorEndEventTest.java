package org.jboss.tools.bpmn2.ui.bot.test.testcase.editor;

import org.jboss.tools.bpmn2.reddeer.editor.ConstructType;
import org.jboss.tools.bpmn2.reddeer.editor.jbpm.endevents.ErrorEndEvent;
import org.jboss.tools.bpmn2.reddeer.editor.jbpm.startevents.StartEvent;
import org.jboss.tools.bpmn2.ui.bot.test.JBPM6BaseTest;
import org.jboss.tools.bpmn2.ui.bot.test.requirements.ProcessDefinitionRequirement.ProcessDefinition;
import org.junit.Test;

/**
 *     
 * @author mbaluch
 */
@ProcessDefinition(name="BPMN2-ErrorEndEvent", project="EditorTestProject")
public class ErrorEndEventTest extends JBPM6BaseTest {

	/**
	 *
	 *  
	 * @throws Exception
	 */
	@Test
	public void runTest() throws Exception {
		StartEvent start = new StartEvent("StartProcess");
		start.append("ErrorEvent", ConstructType.ERROR_END_EVENT);
		
		ErrorEndEvent end = new ErrorEndEvent("ErrorEvent");
		end.setErrorEvent("", "error", "");
	}
	
}