package ee_p01_hellman;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class Main implements ActionListener {
	JFrame ventana;
	ButtonGroup bg;
	JRadioButton op1;
	JRadioButton op2;
	JButton btn;
	JLabel lb1,lb2;
	JPanel cont1,cont2,cont3,cont31,cont4,fondo,f;
	JTextField tf1,tf2;
	Integer nCliente=0;
	private Border border = BorderFactory.createLineBorder(Color.BLACK);
	public JLabel ele(){
		
		JLabel l = new JLabel("L");
		l.setForeground(Color.white);
		l.setBackground(Color.white);
		return l;
	}
	public void primera(){
		cont1 = new JPanel();
		cont1.setLayout(new BoxLayout(cont1,BoxLayout.X_AXIS));
		cont1.add(new JLabel("Bienvenido al chat seguro, seleccione una opción para comenzar:"));
		cont1.setBackground(Color.white);
	}
	public void segunda(){
		cont2 = new JPanel();
		bg = new ButtonGroup();
		op1 = new JRadioButton("Servidor");
		op2 = new JRadioButton("Cliente");
		op1.setBackground(Color.white);
		op2.setBackground(Color.white);
		op1.addActionListener(this);
		op2.addActionListener(this);
		op1.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					btn.doClick();
				}
			}
		});
		op2.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					btn.doClick();
				}
			}
		});
		bg.add(op1);
		bg.add(op2);
		cont2.setLayout(new BoxLayout(cont2,BoxLayout.X_AXIS));
		cont2.add(op1);
		cont2.add(op2);
		cont2.setBackground(Color.white);
	}
	public void tercera(){
		cont3 = new JPanel();
		lb1 = new JLabel("Introduce tu nombre:");
		tf1 = new JTextField(4);
		tf1.setText("");
		tf1.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					btn.doClick();
				}
			}
		});
		cont3.setLayout(new BoxLayout(cont3,BoxLayout.X_AXIS));
		cont3.add(lb1);
		cont3.add(tf1);
		cont3.setBackground(Color.white);
	}
	public void terceraUno(){
		lb2 = new JLabel("ip del servidor:");
		tf2 = new JTextField(4);
		tf2.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					btn.doClick();
				}
			}
		});
		cont31 = new JPanel();
		cont31.setLayout(new BoxLayout(cont31,BoxLayout.X_AXIS));
		cont31.add(lb2);
		cont31.add(tf2);
		cont31.setBackground(Color.white);
	}
	public void cuarta(){
		cont4 = new JPanel();
		btn = new JButton("Inciar");
		cont4.setLayout(new BoxLayout(cont4,BoxLayout.X_AXIS));
		cont4.add(btn);
		btn.addActionListener(this);
		btn.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					btn.doClick();
				}
			}
		});
	}
	public void inicializar(){
		fondo = new JPanel();
		ventana = new JFrame("Chat seguro");
		fondo.setBackground(Color.white);
		fondo.setLayout(new BoxLayout(fondo,BoxLayout.Y_AXIS));
		cont3.setVisible(false);
		cont31.setVisible(false);
		fondo.add(cont1);
		fondo.add(ele());
		fondo.add(cont2);
		fondo.add(ele());
		fondo.add(cont3);
		fondo.add(ele());
		fondo.add(cont31);
		fondo.add(ele());
		fondo.add(cont4);
		fondo.add(ele());
		fondo.add(ele());
		fondo.add(ele());
		fondo.add(ele());
		
		f = new JPanel();
		f.setBackground(Color.white);
		f.setLayout(new BorderLayout());
		f.add(fondo,BorderLayout.CENTER);
		f.add(ele(),BorderLayout.NORTH);
		f.add(ele(),BorderLayout.SOUTH);
		f.add(ele(),BorderLayout.EAST);
		f.add(ele(),BorderLayout.WEST);
		f.setBorder(border);
		
		ventana.setLayout(new BorderLayout());
		ventana.add(f,BorderLayout.CENTER);
		ventana.add(ele(),BorderLayout.NORTH);
		ventana.add(ele(),BorderLayout.SOUTH);
		ventana.add(ele(),BorderLayout.EAST);
		ventana.add(ele(),BorderLayout.WEST);
		
		ventana.setVisible(true);
		ventana.setBackground(Color.white);
		ventana.setResizable(false);
		ventana.setSize(400, 200);
		ventana.pack();
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLocationRelativeTo(null);
		ventana.setIconImage(new ImageIcon(getClass().getResource("../drawable/icono.png")).getImage());

	}
	public Main(){
		primera();
		segunda();
		tercera();
		terceraUno();
		cuarta();
		inicializar();
	}
	public static void main(String...args){
		new Main();
	}
	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource()==btn){
			if (op1.isSelected()){
				new Servidor();
				ventana.setVisible(false);
			}else if(op2.isSelected()){
				if(tf1.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Debes introducir un nombre");
				}else if(tf2.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Debes introducir una dirección ip válida");
				}else{
					if(tf2.getText().equals("localhost")){
						try {
							new Cliente(tf1.getText(),InetAddress.getLocalHost().getHostAddress());
						} catch (UnknownHostException e) {
							e.printStackTrace();
						}
						ventana.setVisible(false);
					}else{
						new Cliente(tf1.getText(),tf2.getText());
						ventana.setVisible(false);
					}
				}
			}else{
				JOptionPane.showMessageDialog(null, "Debe seleccionar una opción");
			}
		}else if (evento.getSource()==op2){
			cont3.setVisible(true);
			cont31.setVisible(true);
		}
		else if (evento.getSource()==op1){
			cont3.setVisible(false);
			cont31.setVisible(false);
		}
	}
}
