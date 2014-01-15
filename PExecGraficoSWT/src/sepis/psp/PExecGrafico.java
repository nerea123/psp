package sepis.psp;

import java.io.IOException;

import javax.swing.ImageIcon;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;

public class PExecGrafico {
	
	
	//No puedo poner imagenes porque solo las  acepta con ruta absoluta

	protected Shell shell;

	boolean windows;
	
	public PExecGrafico(){
		if( System.getProperty("os.name").indexOf("Windows")!=-1)
            windows=true;
        else
            windows=false;
		
		
	}
	
	public static void main(String[] args) {
		try {
			PExecGrafico window = new PExecGrafico();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT LanzaProcesos");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		//ImageIcon img = new ImageIcon( getClass().getResource( "imagenes/navegador.png" ) );
		btnNewButton.setImage(null);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Url direccion=new Url(shell,0);
				direccion.open();
				String url = direccion.getUrl();
		        try {
						java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		btnNewButton.setBounds(33, 72, 150, 74);
		btnNewButton.setText("Navegador");
		
		Button btnPaint = new Button(shell, SWT.NONE);
		btnPaint.setEnabled(windows);
		btnPaint.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(windows)
		            ejecutar("cmd /c mspaint");
			}
		});
		btnPaint.setText("Paint");
		btnPaint.setImage(null);
		btnPaint.setBounds(205, 72, 150, 74);
		
		Button btnNotepad = new Button(shell, SWT.NONE);
		btnNotepad.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 if(windows)
			            ejecutar("cmd /c notepad");
			        else
			            ejecutar("gedit");
			}
		});
		btnNotepad.setText("Notepad");
		btnNotepad.setImage(null);
		btnNotepad.setBounds(33, 166, 150, 74);
		
		Button btnCalculadora = new Button(shell, SWT.NONE);
		btnCalculadora.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				 if(windows)
			            ejecutar("cmd /c calc");
			       else
			           ejecutar("gnome-calculator");
			}
		});
		btnCalculadora.setText("Calculadora");
		btnCalculadora.setImage(null);
		btnCalculadora.setBounds(205, 166, 150, 74);
		
		Label lblLanzaProcesos = new Label(shell, SWT.NONE);
		lblLanzaProcesos.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblLanzaProcesos.setBounds(143, 23, 104, 27);
		lblLanzaProcesos.setText("Lanza Procesos");

	}
	
	 public static void ejecutar(String comando){
			
			try
			{
	                    Process p = Runtime.getRuntime().exec (comando);
		
			}
			catch (Exception e)
			{
			  
			  System.out.println("No se encuentra el programa o comando");
			}
		}

}
