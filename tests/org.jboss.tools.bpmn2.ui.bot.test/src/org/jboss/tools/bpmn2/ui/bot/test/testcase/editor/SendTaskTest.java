package org.jboss.tools.bpmn2.ui.bot.test.testcase.editor;

import org.jboss.tools.bpmn2.reddeer.editor.ConstructType;
import org.jboss.tools.bpmn2.reddeer.editor.jbpm.BPMN2Process;
import org.jboss.tools.bpmn2.reddeer.editor.jbpm.FromVariable;
import org.jboss.tools.bpmn2.reddeer.editor.jbpm.InputParameterMapping;
import org.jboss.tools.bpmn2.reddeer.editor.jbpm.ToDataInput;
import org.jboss.tools.bpmn2.reddeer.editor.jbpm.activities.SendTask;
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
@ProcessDefinition(name="BPMN2-SendTask", project="EditorTestProject")
public class SendTaskTest extends JBPM6BaseTest {

	/**
	 *
	 *  
	 * @throws Exception
	 */
	@Test
	public void runTest() throws Exception {
		BPMN2Process process = new BPMN2Process("BPMN2-SendTask");
		process.addLocalVariable("s", "String");
		process.addMessage("_2_Message", "String");
		
		StartEvent start = new StartEvent("StartProcess");
		start.append("Send", ConstructType.SEND_TASK);

		SendTask send = new SendTask("Send");
		send.setImplementation("Other");
		send.setMessage("_2_Message", "String");
		send.addParameterMapping(new InputParameterMapping(new FromVariable("BPMN2-SendTask/s"), new ToDataInput("Message")));
		send.append("EndProcess", ConstructType.TERMINATE_END_EVENT);
	}
	
}