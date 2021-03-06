package org.jboss.tools.teiid.reddeer.wizard;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.jboss.reddeer.eclipse.jface.wizard.WizardPage;
import org.jboss.reddeer.swt.impl.button.PushButton;
import org.jboss.reddeer.swt.impl.button.RadioButton;
import org.jboss.reddeer.swt.impl.combo.DefaultCombo;
import org.jboss.reddeer.swt.impl.shell.DefaultShell;
import org.jboss.reddeer.swt.impl.table.DefaultTable;
import org.jboss.reddeer.swt.impl.text.DefaultText;
import org.jboss.reddeer.swt.impl.text.LabeledText;
import org.jboss.reddeer.swt.impl.tree.DefaultTreeItem;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;

/**
 * Wizard for importing XML schemas
 * 
 * @author lfabriko
 * 
 */
public class XMLSchemaImportWizard extends TeiidImportWizard {

	private static final String IMPORT_XML_SCHEMA_SHELL = "Import XML Schema Files";
		private boolean isLocal;
		private String rootPath;
		//private List<String[]> elements;
		private String[] schemas;
		public String[] getSchemas() {
			return schemas;
		}

		public void setSchemas(String[] schemas) {
			this.schemas = schemas;
		}

		private String destination;

		public void setDestination(String destination) {
			this.destination = destination;
		}

		public XMLSchemaImportWizard() {
			super("XML Schemas");
		}

		public void setLocal(boolean isLocal) {
			this.isLocal = isLocal;
		}


		public void setRootPath(String rootPath) {
			this.rootPath = rootPath;
		}

		
		/*public void addElement(String[] path) {
			elements.add(path);
		}

		public void addElement(String path) {
			elements.add(path.split("/"));
		}*/

		public void execute() {
			open();
			if (isLocal) {
				new DefaultShell(IMPORT_XML_SCHEMA_SHELL).setFocus();
				new RadioButton("Import XML schemas from file system").click();
				next();

				new SWTWorkbenchBot().comboBox().setText(rootPath);

				new DefaultText().setText(destination);
				new SWTWorkbenchBot().text().setFocus();
				
				/*for (String schema: schemas){
					new DefaultTable().check(schema);
				}*/
				//new DefaultTable().select(schemas);
				for (String schema : schemas){
					new DefaultTable().getItem(schema).setChecked(true);
				}
				
				finish();
			} else {
				new RadioButton("Import XML schemas via").click();
				throw new UnsupportedOperationException();
			}

		}

		@Override
		public WizardPage getFirstPage() {
			throw new UnsupportedOperationException();
		}

	}
