import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TabletControl
{	
    static final String CALNORM = "xrandr -o normal";/* && xinput set-int-prop"
	+ " \"Fujitsu Component USB Touch Panel\""
	+ " \"Evdev Axis Calibration\" 86 3873 280 3993";*/
    static final String CALCW = "xrandr -o left";/* && xinput set-int-prop"
	+ " \"Fujitsu Component USB Touch Panel\""
	+ " \"Evdev Axis Calibration\" 86 3873 280 3993";*/
    static final String CALCCW = "xrandr -o right";/* && xinput set-int-prop"
	+ " \"Fujitsu Component USB Touch Panel\""
	+ " \"Evdev Axis Calibration\" 86 3873 280 3993";*/
    static final String KEYBOARD = "florence";

    Button normal, cw, ccw, keyboard;

    public static void main(String[] args)
    {	
	TabletControl tabletControl = new TabletControl("TabletControl");
	
    }

    public TabletControl(String title)
    {
	JFrame frame = new JFrame(title);
	
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	JButton normal = new JButton("normal");
	JButton cw = new JButton("cw");
	JButton ccw = new JButton("ccw");
	JButton keyboard = new JButton("keyboard");

	frame.add(normal, BorderLayout.CENTER);
	frame.add(cw, BorderLayout.LINE_END);
	frame.add(ccw, BorderLayout.LINE_START);
	frame.add(keyboard, BorderLayout.PAGE_END);		

	normal.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
			try {Runtime.getRuntime().exec(CALNORM);}
			catch (IOException ioe){ ioe.printStackTrace();}
		}});
	cw.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
			try {Runtime.getRuntime().exec(CALCW);}
			catch (IOException ioe){ioe.printStackTrace();}
		}});
	ccw.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
			try {Runtime.getRuntime().exec(CALCCW);}
			catch (IOException ioe){ ioe.printStackTrace();}
		}});
	keyboard.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
			try {Runtime.getRuntime().exec(KEYBOARD);}
			catch (IOException ioe){ ioe.printStackTrace();}
		}});
	
	frame.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e)
    		{
			try {Runtime.getRuntime().exec(CALNORM);}
			catch (IOException ioe){ ioe.printStackTrace();}
			System.exit(0);
		}});
	frame.setAlwaysOnTop(true);
	frame.pack();
	frame.setLocation(0,0);
	frame.setVisible(true);
    }

}
