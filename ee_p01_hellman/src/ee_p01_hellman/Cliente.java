package ee_p01_hellman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Cliente{
	Socket socket ;
	BufferedReader lector;
	PrintWriter escritor;
	Diseno d = new Diseno();
	String nick;
	String ip;
	static boolean shift;
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
	public Cliente(String nick,String ip){
		this.nick=nick;
		this.ip=ip;
		d.inicializar("Chat Seguro");
		d.getTf().addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					d.getBtn().doClick();				
				}
			}
		});
		d.getFondo().addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent e) { 
				Object [] opciones ={"Aceptar","Cancelar"};
				int eleccion = JOptionPane.showOptionDialog(null,"¿Está seguro que desea salir del chat? :(","Mensaje de Confirmacion",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
				if (eleccion == JOptionPane.YES_OPTION){
					try {
						escritor = new PrintWriter(socket.getOutputStream(),true);
						escritor.println("°M3b0i°"+nick);
						System.exit(0);
					} catch (Throwable e1) {
						System.exit(0);
					}
				}
			}
			});
		Thread principal = new Thread(new Runnable(){
			public void run(){
				try{
					socket = new Socket(ip,9000);
					escritor = new PrintWriter(socket.getOutputStream(),true);
					escritor.println(nick);
					leer();
					escribir();
				}catch(Exception ex){
					d.getBtn().setText("Reconectar");
					JOptionPane.showMessageDialog(null, "No se encontró el servidor especificado");
					d.getBtn().addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							Object [] opciones ={"Aceptar","Cancelar"};
							int eleccion = JOptionPane.showOptionDialog(null,"Imposible conectar, no se encontró ningun servidor activo"
									+ " ¿Desea salir de la aplicación?","Mensaje de Confirmacion",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
							if (eleccion == JOptionPane.YES_OPTION){
								try {
									System.exit(0);
								} catch (Throwable e1) {
									e1.printStackTrace();
								}
							}
						}
					});
					//ex.printStackTrace();
				}
			}
		});
		principal.start();
	}
	
	public void leer(){
		Thread leerHilo = new Thread(new Runnable(){
			public void run(){
				try{
					lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					while(true){
						String mensajeRecibido="";
						try{
							mensajeRecibido = lector.readLine();
						}catch(Exception ex){
							d.getBtn().setText("Reconectar");
							d.getBtn().addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent e) {
									new Cliente(nick,ip);
									d.getFondo().setVisible(false);
								}
							});
						}
						if(mensajeRecibido.startsWith("°")){
							d.getTa2().setText("");
							String[] temp;
							temp = mensajeRecibido.split("°");
							System.out.println(temp[0]);
							for(int i =0; i < temp.length ; i++){
								if (temp[i].equals(nick)){
									d.getTa2().append("(Yo)"+temp[i]);
								}
							}
							for(int i =0; i < temp.length ; i++){
								if (! temp[i].equals(nick)){
									d.getTa2().append(temp[i]+"\n");
								}
							}
						}else{
							char[] t,n;
							t = mensajeRecibido.toCharArray();
							n = nick.toCharArray();
							boolean flag=false;
							for(int i=0;i<n.length;i++){
								if(n[i] == t[i]){
									flag = true;
								}else{
									flag=false;
								}
							}
							if(flag){
								d.getTa().append("(Yo)"+ mensajeRecibido + "\n");
							}else{
								d.getTa().append( mensajeRecibido + "\n");
							}
						}
					}
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		leerHilo.start();
	}
	
	public void escribir(){
		Thread escribirHilo = new Thread(new Runnable(){
			public void run(){
				try{
					escritor = new PrintWriter(socket.getOutputStream(),true);
					d.getBtn().addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							String enviarMensaje = d.getTf().getText();
							escritor.println(nick+": "+enviarMensaje);
							d.getTf().setText("");
						}
					});
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		escribirHilo.start();
	}
}
