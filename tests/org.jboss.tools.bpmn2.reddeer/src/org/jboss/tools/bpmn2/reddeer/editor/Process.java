package org.jboss.tools.bpmn2.reddeer.editor;

import org.jboss.reddeer.swt.impl.button.CheckBox;


/**
 * 
 * @author Marek Baluch <mbaluch@redhat.com>
 */
public class Process extends ContainerConstruct {

	/**
	 * Creates a new instance of Process.
	 * 
	 * @param name
	 */
	public Process(String name) {
		super(name, ConstructType.PROCESS);
	}
	
	/**
	 * ISSUES:
	 * 	1) Set the name of the process changes description! Changing the description
	 *     does not change the name of the process!
	 * 
	 * @param name
	 */
	public void setName(String name) {
		super.setName(name);
	}
	
	/**
	 * 
	 * @param b
	 */
	public void setExecutable(boolean b) {
		properties.selectTab("Process");
		properties.selectCheckBox(new CheckBox(1), b);
//		properties.selectCheckBox(new CheckBox("Is Executable"), b);
	}
	
 }
