import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TabletControl extends Frame implements ActionListener
{	
	final String CALNORMAL = "xrandr -o normal && xinput set-int-prop \"Fujitsu Component USB Touch Panel\" \"Evdev Axis Calibration\" 86 3873 280 3993";
	final String CALCW = "xrandr -o left && xinput set-int-prop \    "Fujitsu Component USB Touch Panel\" \"Evdev Axis Calibration\" 86 3873 280     3993";
	final String CALCCW = "xrandr -o right && xinput set-int-prop \    "Fujitsu Component USB Touch Panel\" \"Evdev Axis Calibration\" 86 3873 280     3993";
	final String KEYBOARD = "caribou";
	
	Button normal, cw, ccw, keyboard;
	
	public static void main(String[] args)
	{	
		TabletControl tabletControl = new TabletControl("TabletControl");
		tabletControl.setVisible(true);
		
		tabletControl.setAlwaysOnTop(true);
		tabletControl.pack();
		tabletControl.setLocation(0,0);
	
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run(){
				try {
					Runtime.getRuntime().exec("xinput set-int-prop \"Fujitsu Component USB Touch Panel\" \"Evdev Axis Calibration\" 32 74 3883 280 397 8");
				} catch (IOException e){
					e.printStackTrace();
				}
			}
		}));
	}
	
	public TabletControl(String title)
	{
		super(title);

		normal = new Button("normal");
		cw = new Button("cw");
		ccw = new Button("ccw");
		keyboard = new Button("keyboard");
	
		add(normal);
		add(cw);
		add(ccw);
		add(keyboard);		
		
		normal.addActionListener(this);
		cw.addActionListener(this);
		ccw.addActionListener(this);
		keyboard.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		try {		
			if (e.getSource() == normal){
				Runtime.getRuntime().exec(CALNORMAL);
			} else if (e.getSource() == cw){
				Runtime.getRuntime().exec(CALCW);
			} else if (e.getSource() == ccw){
				Runtime.getRuntime().exec(CALCCW);
			} else if (e.getSource() == keyboard){
				Runtime.getRuntime().exec(KEYBOARD);
			} else {
				;
			}
		} catch (IOException ee){
			ee.printStackTrace();
		}
	}
}
