package org.jboss.tools.bpmn2.ui.bot.test.testcase.editor;

import org.jboss.tools.bpmn2.reddeer.editor.ConstructType;
import org.jboss.tools.bpmn2.reddeer.editor.Position;
import org.jboss.tools.bpmn2.reddeer.editor.AbstractGateway.Direction;
import org.jboss.tools.bpmn2.reddeer.editor.jbpm.BPMN2Process;
import org.jboss.tools.bpmn2.reddeer.editor.jbpm.activities.ScriptTask;
import org.jboss.tools.bpmn2.reddeer.editor.jbpm.activities.UserTask;
import org.jboss.tools.bpmn2.reddeer.editor.jbpm.boundaryevents.ErrorBoundaryEvent;
import org.jboss.tools.bpmn2.reddeer.editor.jbpm.endevents.ErrorEndEvent;
import org.jboss.tools.bpmn2.reddeer.editor.jbpm.gateways.ParallelGateway;
import org.jboss.tools.bpmn2.reddeer.editor.jbpm.startevents.StartEvent;
import org.jboss.tools.bpmn2.ui.bot.test.JBPM6BaseTest;
import org.jboss.tools.bpmn2.ui.bot.test.requirements.ProcessDefinitionRequirement.ProcessDefinition;
import org.junit.Test;

/**
 * ISSUES:
 * 	1) engine does not validate the presence of the rules.
 *     
 * @author mbaluch
 */
@ProcessDefinition(name="BPMN2-ErrorBoundaryEventOnTask", project="EditorTestProject")
public class ErrorBoundaryEventOnTaskTest extends JBPM6BaseTest {

	/**
	 * ISSUE: Default Error names are increased even though cancel has been hit.
	 * 
	 * @throws Exception
	 */
	@Test
	public void runTest() throws Exception {
		BPMN2Process process = new BPMN2Process("BPMN2-ErrorBoundaryEventOnTask");
		process.addError("", "org.jbpm.bpmn2.objects.MyError", null);
		
		StartEvent start = new StartEvent("StartProcess");
		start.append("Split", ConstructType.PARALLEL_GATEWAY);
		
		ParallelGateway gateway = new ParallelGateway("Split");
		gateway.setDirection(Direction.DIVERGING);
		gateway.append("User Task", ConstructType.USER_TASK, Position.NORTH_EAST);
		gateway.append("User task error attached", ConstructType.USER_TASK, Position.SOUTH_EAST);
		
		UserTask task1 = new UserTask("User Task");
		task1.addActor("", "john");
		task1.append("Error end event", ConstructType.ERROR_END_EVENT);
		
		ErrorEndEvent end1 = new ErrorEndEvent("Error end event");
		end1.setErrorEvent("", "org.jbpm.bpmn2.objects.MyError", "");
		
		UserTask task2 = new UserTask("User task error attached");
		task2.addActor("", "mary");
		task2.append("Error 1", ConstructType.END_EVENT);
		task2.addEvent("Error Boundary Event", ConstructType.ERROR_BOUNDARY_EVENT);
		
		ErrorBoundaryEvent boundaryEvent = new ErrorBoundaryEvent("Error Boundary Event");
		boundaryEvent.setErrorEvent("", "org.jbpm.bpmn2.objects.MyError", "");
		boundaryEvent.append("Script Task", ConstructType.SCRIPT_TASK, Position.SOUTH);
		
		ScriptTask script = new ScriptTask("Script Task");
		script.setScript("Java", "System.out.println(\"Error handled\");");
		script.append("Error 2", ConstructType.END_EVENT);
	}
	
}