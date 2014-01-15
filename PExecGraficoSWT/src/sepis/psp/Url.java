package sepis.psp;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Url extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private String texto="";

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Url(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(450, 156);
		shell.setText(getText());
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI Symbol", 9, SWT.BOLD));
		lblNewLabel.setBounds(235, 10, 118, 15);
		lblNewLabel.setText("Introduce url");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(197, 31, 170, 21);
		
		Button btnAceptar = new Button(shell, SWT.NONE);
		btnAceptar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				texto=text.getText();
				shell.dispose();
			}
		});
		btnAceptar.setBounds(243, 72, 75, 25);
		btnAceptar.setText("Aceptar");

	}
	
	public String getUrl(){
        if(texto.isEmpty()){
            return "http://www.google.com";
        }
        return texto;
    }
}
