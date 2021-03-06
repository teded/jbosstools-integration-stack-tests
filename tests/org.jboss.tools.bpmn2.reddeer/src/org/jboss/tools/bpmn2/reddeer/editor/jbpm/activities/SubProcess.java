package org.jboss.tools.bpmn2.reddeer.editor.jbpm.activities;

import org.jboss.reddeer.swt.impl.text.LabeledText;
import org.jboss.tools.bpmn2.reddeer.editor.ConstructType;
import org.jboss.tools.bpmn2.reddeer.editor.ContainerConstruct;
import org.jboss.tools.bpmn2.reddeer.editor.jbpm.DataType;

/**
 * TBD
 * 
 * @author Marek Baluch <mbaluch@redhat.com>
 */
public class SubProcess extends ContainerConstruct {

	/**
	 * 
	 * @param name
	 */
	public SubProcess(String name) {
		super(name, ConstructType.SUB_PROCESS);
	}
	
	public void addLocalVariable(String name, String dataType) {
		properties.selectTab("Sub Process");
		properties.toolbarButton("Variable List", "Add").click();
		new LabeledText("Name").setText(name);
		new DataType(dataType).add();
		bot.toolbarButtonWithTooltip("Close").click();
	}
}
