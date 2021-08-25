package abc;


import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author garci
 */
public class IO {
    public static double getDouble(String s)
	{
		return Double.parseDouble(getWord(s));
	}
	public static int getInt(String s)
	{
		return Integer.parseInt(getWord(s));
	}
	public static String getWord(String s)
	{
		return JOptionPane.showInputDialog(s);
	}
	public static void display(String title, String text, int row, int col)
	{
		JTextArea s = new JTextArea(text, row, col);
		JScrollPane pane = new JScrollPane(s);
		JOptionPane.showMessageDialog(null, pane, title, JOptionPane.INFORMATION_MESSAGE);
	}

    void display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
