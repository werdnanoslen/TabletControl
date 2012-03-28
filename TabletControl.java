import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TabletControl
{	
    static final String ROTNORM = "xrandr -o normal";
    static final String CALNORM1 = "xinput set-int-prop"
	+" \"Fujitsu Component USB Touch Panel\""
	+" \"Evdev Axis Calibration\""
	+" 32 73 3860 292 3989";
    static final String CALNORM2 = 
        "xinput --set-prop 11 \"Evdev Axis Inversion\" 0 0"
        +" && xinput --set-prop 11 \"Evdev Axes Swap\" 0";
    static final String ROTCW = "xrandr -o right";
    static final String CALCW1 = "xinput set-int-prop"
	+" \"Fujitsu Component USB Touch Panel\""
	+" \"Evdev Axis Calibration\""
	+" 32 293 4001 3869 85";
    static final String CALCW2 =
        "xinput --set-prop 11 \"Evdev Axis Inversion\" 0 1"
        +" && xinput --set-prop 11 \"Evdev Axes Swap\" 1";
    static final String ROTCCW = "xrandr -o left";
    static final String CALCCW1 = "xinput set-int-prop"
    	+" \"Fujitsu Component USB Touch Panel\""
	+" \"Evdev Axis Calibration\""
	+" 32 4009 297 73 3870";
    static final String CALCCW2 = 
        "xinput --set-prop 11 \"Evdev Axis Inversion\" 1 0"
        +" && xinput --set-prop 11 \"Evdev Axes Swap\" 1";
    //TODO: fix CALs, as they do not do anything
    //TODO: use external user config file to import above commands
    static final String KEYBOARD = "florence";
    /*
    * TODO: keyboard isRunning() | toggleOnOff(), 
    * TODO: keyboard prevent dupes, kill if TC closes
    */

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
	    	try {
                    Runtime.getRuntime().exec(ROTNORM);
	    	    Runtime.getRuntime().exec(CALNORM1);
		    Runtime.getRuntime().exec(CALNORM2);}
		catch (IOException ioe){ ioe.printStackTrace();}
	    }});
	cw.addActionListener(new ActionListener(){
	    public void actionPerformed(ActionEvent e)
	    {
	    	try {
                    Runtime.getRuntime().exec(ROTCW);
                    Runtime.getRuntime().exec(CALCW1);
                    Runtime.getRuntime().exec(CALCW2);}
		catch (IOException ioe){ioe.printStackTrace();}
	    }});
	ccw.addActionListener(new ActionListener(){
    	    public void actionPerformed(ActionEvent e)
	    {
		try {
                    Runtime.getRuntime().exec(ROTCCW);
        	    Runtime.getRuntime().exec(CALCCW1);
		    Runtime.getRuntime().exec(CALCCW2);}
		catch (IOException ioe){ ioe.printStackTrace();}
	    }});
	keyboard.addActionListener(new ActionListener(){
	    public void actionPerformed(ActionEvent e)
	    {
	    	try {Runtime.getRuntime().exec(KEYBOARD);}
	    	catch (IOException ioe){ ioe.printStackTrace();}
	    }});
	//TODO: configure scrolling/zoom?
	
	frame.addWindowListener(new WindowAdapter(){
	    public void windowClosing(WindowEvent e)
    	    {
	    	try {
                    Runtime.getRuntime().exec(ROTNORM);
        	    Runtime.getRuntime().exec(CALNORM1);
	    	    Runtime.getRuntime().exec(CALNORM2);}
		catch (IOException ioe){ ioe.printStackTrace();}
	    }});
	frame.setAlwaysOnTop(true);
	frame.pack();
	frame.setLocation(0,0);
	frame.setVisible(true);
    }
}
