package ee_p01_hellman;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.*;

public class Enter extends KeyAdapter{
	JButton btn = new JButton();
	public Enter(JButton btn){
		this.btn = btn;
	}
	
	public void keyPressed(KeyEvent ke){
		if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
			btn.doClick();
		}
	}
}
