package org.jboss.tools.bpmn2.ui.bot.test.testcase.editor;

import org.jboss.tools.bpmn2.reddeer.editor.ConstructType;
import org.jboss.tools.bpmn2.reddeer.editor.Position;
import org.jboss.tools.bpmn2.reddeer.editor.jbpm.activities.ScriptTask;
import org.jboss.tools.bpmn2.reddeer.editor.jbpm.activities.SubProcess;
import org.jboss.tools.bpmn2.reddeer.editor.jbpm.activities.Task;
import org.jboss.tools.bpmn2.reddeer.editor.jbpm.boundaryevents.ConditionalBoundaryEvent;
import org.jboss.tools.bpmn2.reddeer.editor.jbpm.endevents.EscalationEndEvent;
import org.jboss.tools.bpmn2.reddeer.editor.jbpm.startevents.StartEvent;
import org.jboss.tools.bpmn2.ui.bot.test.JBPM6BaseTest;
import org.jboss.tools.bpmn2.ui.bot.test.requirements.ProcessDefinitionRequirement.ProcessDefinition;
import org.junit.Test;

/**
 *     
 * @author mbaluch
 */
@ProcessDefinition(name="BPMN2-ConditionalBoundaryEventInterrupting", project="EditorTestProject")
public class ConditionalBoundaryEventInterruptingTest extends JBPM6BaseTest {

	/**
	 *
	 *  
	 * @throws Exception
	 */
	@Test
	public void runTest() throws Exception {
		StartEvent startEvent = new StartEvent("StartProcess");
		startEvent.append("Hello", ConstructType.SUB_PROCESS);
		
		SubProcess subProcess = new SubProcess("Hello");
		subProcess.append("EndProcess", ConstructType.TERMINATE_END_EVENT);
		subProcess.add("StartSubProcess", ConstructType.START_EVENT);
		
		StartEvent subProcessStartEvent = new StartEvent("StartSubProcess");
		subProcessStartEvent.append("Task", ConstructType.TASK);
		
		Task task = new Task("Task");
		task.append("EscalationEvent", ConstructType.ESCALATION_END_EVENT, Position.SOUTH);
		
		EscalationEndEvent endEvent = new EscalationEndEvent("EscalationEvent");
		endEvent.setEscalation("Timeout", "400");
		
		subProcess.add("Conditional Boundary Event Process", ConstructType.CONDITIONAL_BOUNDARY_EVENT);
		
		ConditionalBoundaryEvent conditionalBoundaryEvent = new ConditionalBoundaryEvent("Conditional Boundary Event Process");
		conditionalBoundaryEvent.setScript("", "org.jbpm.bpmn2.objects.Person(name == \"john\")");
		conditionalBoundaryEvent.append("Goodbye", ConstructType.SCRIPT_TASK, Position.NORTH);
		
		ScriptTask scriptTask = new ScriptTask("Goodbye");
		scriptTask.setScript("", "System.out.println(\"Condition met\");");
		scriptTask.append("EndProcess2", ConstructType.END_EVENT);
	}
	
}