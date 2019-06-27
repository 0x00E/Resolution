package com.github.qianniancc.resolution;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;

public class Main extends ApplicationWindow {
	private Text text;
	private Text text_1;

	public Main() {
		super(null);
		setBlockOnOpen(true);
		setShellStyle(SWT.MIN);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();

	}

	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		text = new Text(container, SWT.BORDER);
		text.setBounds(26, 31, 151, 35);

		text_1 = new Text(container, SWT.BORDER);
		text_1.setBounds(215, 31, 151, 35);

		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				int width = 0;
				int height = 0;
				String ratio = "";
				MessageBox messageBox = new MessageBox(getShell(), SWT.OK);

				try {
					width = Integer.parseInt(text.getText());
					height = Integer.parseInt(text_1.getText());
				} catch (Exception e2) {
					ratio = "发生错误";
					messageBox.setMessage(ratio);
					messageBox.open();
					return;
				}
				if (width == 0 || height == 0) {
					ratio = "宽或高不能为0";
				} else if (width / 16 == height / 9) {
					ratio = "16:9";
				} else if (width / 16 == height / 10) {
					ratio = "16:10";
				} else if (width / 4 == height / 3) {
					ratio = "4:3";
				} else if (width / 5 == height / 4) {
					ratio = "5:4";
				} else if (width / 21 == height / 9) {
					ratio = "21:9";
				} else if (width / 5 == height / 3) {
					ratio = "5:3";
				} else if (width / 2 == height / 1) {
					ratio = "15:9";
				} else if (width / 15.6 == height / 10) {
					ratio = "15.6:10";
				} else if (width / 72 == height / 35) {
					ratio = "72:35";
				} else if (width / 60 == height / 29) {
					ratio = "60:29";
				} else {
					ratio = "未知的分辨率";
				}
				messageBox.setMessage(ratio);
				messageBox.open();

			}
		});
		btnNewButton.setBounds(129, 118, 80, 27);
		btnNewButton.setText("\u8BA1\u7B97");

		Label lblpx = new Label(container, SWT.NONE);
		lblpx.setBounds(215, 77, 151, 35);
		lblpx.setText("\u5BBD\u5EA6(px):");

		Label label = new Label(container, SWT.NONE);
		label.setText("\u957F\u5EA6(px):");
		label.setBounds(26, 77, 151, 35);

		return container;
	}

	private void createActions() {
	}

	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		return toolBarManager;
	}

	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	public static void main(String args[]) {
		try {
			Main window = new Main();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("分辨率比例计算器");
		String path = String.valueOf("./images/icon.png");
		Display display = Display.getDefault();
		Image img = new Image(display, path);
		newShell.setImage(img);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(400, 250);
	}
}
