package ee_p01_hellman;
import java.net.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.io.*;
import java.awt.event.*;

public class Servidor implements ActionListener{
	ServerSocket servidor;
	Socket socket;
	BufferedReader lector;
	PrintWriter escritor;
	static Diseno d = new Diseno();
	static ListaLigada<Conexion> c = new ListaLigada<Conexion>();
	String mensajeRecibido;
	Thread p;
	public Servidor(){
		d.inicializar("Servidor");
		d.getSf().setVisible(false);
		d.getBtn().setText("Cerrar servidor");
		d.getBtn().addActionListener(this);
		d.getBtn().addKeyListener(new Enter(d.getBtn()));
		d.getFondo().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		d.getFondo().addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent e) { 
				d.getBtn().doClick();
			}
			});
		p = new Thread(new Runnable(){
			public void run(){
				try{
					servidor = new ServerSocket(9000);
					while(true){
						socket = servidor.accept();
						lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						String mensajeRecibido = lector.readLine();
							c.insertaInicio(new Conexion(socket,mensajeRecibido));
							((Entrega) new Entrega(socket,actualizarLista())).start();
						
						
					}
				}catch(Exception ex){
					ex.printStackTrace();
					for(int a=0;a<=18;a++){
						for(int i=0;i<=15;i++){
							d.getTa().append("Nel ");
							d.getTa2().append("Nel ");
						}
						d.getTa().append("\n");
						d.getTa2().append("\n");
					}
					JOptionPane.showMessageDialog(null, "Imposible abrir un servidor nuevo con las mismas características");
					System.exit(0);
				}
			}
		});
		p.start();
	}
	public static String actualizarLista(){
		String s="";
		d.getTa2().setText("");;
		for (int i=0;i<c.contar();i++){
			d.getTa2().append(c.pos(new Nodo<Conexion>(null),i).getNick()+"\n");
			s= "°" + c.pos(new Nodo<Conexion>(null),i).getNick()+s;
		}
		return s;
	}

	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource()==d.getBtn()){
			Object [] opciones ={"Aceptar","Cancelar"};
			int eleccion = JOptionPane.showOptionDialog(null,"Si detiene el servidor, la comunicación entre los miembros"
					+ "del chat será imposible \n ¿Está seguro que desea apagar el servidor?","Mensaje de Confirmacion",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
			if (eleccion == JOptionPane.YES_OPTION){
				try {
					System.exit(0);
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		}
	}
}
