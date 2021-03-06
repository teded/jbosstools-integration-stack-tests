package org.jboss.tools.bpmn2.reddeer.editor.jbpm.gateways;

import org.jboss.tools.bpmn2.reddeer.editor.AbstractGateway;
import org.jboss.tools.bpmn2.reddeer.editor.ConstructType;

/**
 * 
 * @author Marek Baluch <mbaluch@redhat.com>
 */
public class ParallelGateway extends AbstractGateway {

	/**
	 * 
	 * @param name
	 */
	public ParallelGateway(String name) {
		super(name, ConstructType.PARALLEL_GATEWAY);
	}
	
	/**
	 * @see org.jboss.tools.bpmn2.reddeer.editor.AbstractGateway#setDirection(org.jboss.tools.bpmn2.reddeer.editor.AbstractGateway.Direction)
	 */
	@Override
	public void setDirection(Direction direction) {
		super.setDirection(direction);
	}
	
}
