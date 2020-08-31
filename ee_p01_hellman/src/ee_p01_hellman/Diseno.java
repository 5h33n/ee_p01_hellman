package ee_p01_hellman;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
public class Diseno {
	private JPanel ventana;
	private JButton btn;
	private JTextArea tf;
	private JTextArea ta,ta2;
	private JFrame fondo;
	private JPanel p1;
	private JPanel p2;
	private JPanel west;
	private JScrollPane s1,s2,sf;
	private Border border = BorderFactory.createLineBorder(Color.BLACK);
	
	
	public JPanel getVentana() {
		return ventana;
	}
	public void setVentana(JPanel ventana) {
		this.ventana = ventana;
	}
	public JButton getBtn() {
		return btn;
	}
	public void setBtn(JButton btn) {
		this.btn = btn;
	}
	public JTextArea getTf() {
		return tf;
	}
	public void setTf(JTextArea tf) {
		this.tf = tf;
	}
	public JTextArea getTa() {
		return ta;
	}
	public void setTa(JTextArea ta) {
		this.ta = ta;
	}
	public JTextArea getTa2() {
		return ta2;
	}
	public void setTa2(JTextArea ta2) {
		this.ta2 = ta2;
	}
	public JFrame getFondo() {
		return fondo;
	}
	public void setFondo(JFrame fondo) {
		this.fondo = fondo;
	}
	public JPanel getP1() {
		return p1;
	}
	public void setP1(JPanel p1) {
		this.p1 = p1;
	}
	public JPanel getP2() {
		return p2;
	}
	public void setP2(JPanel p2) {
		this.p2 = p2;
	}
	public JPanel getWest() {
		return west;
	}
	public void setWest(JPanel west) {
		this.west = west;
	}
	public JScrollPane getS1() {
		return s1;
	}
	public void setS1(JScrollPane s1) {
		this.s1 = s1;
	}
	public JScrollPane getS2() {
		return s2;
	}
	public void setS2(JScrollPane s2) {
		this.s2 = s2;
	}
	public JScrollPane getSf() {
		return sf;
	}
	public void setSf(JScrollPane sf) {
		this.sf = sf;
	}
	public Border getBorder() {
		return border;
	}
	public void setBorder(Border border) {
		this.border = border;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Diseno(){
	}
	public JLabel ele(){
		
		JLabel l = new JLabel("L");
		l.setForeground(Color.white);
		return l;
	}
	public void primero(){
		btn = new JButton("Enviar");
		tf = new JTextArea();
		tf.setColumns(2);
		tf.setRows(30);
		tf.setBorder(border);
		sf = new JScrollPane(tf);
		ta = new JTextArea(18,40);
		ta.setBorder(border);
		ta.setEditable(false);
		s1 = new JScrollPane(ta);
		p1 = new JPanel();
		p1.setLayout(new GridLayout(1,3));
		p1.add(s1);
		p2 = new JPanel();
		p2.setLayout(new GridLayout(2,2));
		p2.add(sf);
		p2.add(btn);
		ventana = new JPanel();
		ventana.setLayout(new BorderLayout());
		ventana.add(p1,BorderLayout.NORTH);
		p2.setPreferredSize(new Dimension(100,70));
		ventana.add(p2, BorderLayout.CENTER);
		ventana.add(ele(),BorderLayout.SOUTH);
		
		
	}
	public void segundo(){
		west = new JPanel();
		west.setLayout(new BorderLayout());
		ta2 = new JTextArea(5,20);
		ta2.setBorder(border);
		ta2.setEditable(false);
		s2 = new JScrollPane(ta2);
		west.add(s2,BorderLayout.CENTER);
		west.add(ele(), BorderLayout.EAST);
		west.add(ele(), BorderLayout.SOUTH);
	}
	public void inicializar(String v){
		primero();
		segundo();
		fondo = new JFrame(v);
		fondo.setLayout(new BorderLayout());
		fondo.add(ventana,BorderLayout.WEST);
		fondo.add(west, BorderLayout.EAST);
		fondo.pack();
		fondo.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		fondo.setVisible(true);
		fondo.setResizable(false);
		fondo.setLocationRelativeTo(null);
		fondo.setIconImage(new ImageIcon(getClass().getResource("../drawable/icono.png")).getImage());
	}

}
