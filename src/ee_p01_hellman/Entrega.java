package ee_p01_hellman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Entrega extends Thread{
	BufferedReader lector;
	PrintWriter escritor;
	Socket socket;
	public Entrega(Socket socket,String s) throws IOException{
		this.socket=socket;
		for (int i=0;i<Servidor.c.contar();i++){
			escritor = new PrintWriter(Servidor.c.pos(new Nodo<Conexion>(null),i).getS().getOutputStream(),true);
			escritor.println(s);
		}
	}
	public void run(){
		try{
					lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					while(true){
						String mensajeRecibido = lector.readLine();
						if(mensajeRecibido.startsWith("°")){
							mensajeRecibido=mensajeRecibido.substring(7);
							for (int i=0;i<Servidor.c.contar();i++){
								if(mensajeRecibido.equals(Servidor.c.pos(new Nodo<Conexion>(null), i).getNick())){
									Servidor.c.eliminar(Servidor.c.pos(new Nodo<Conexion>(null), i));
								}
							}
							Servidor.actualizarLista();
							for (int i=0;i<Servidor.c.contar();i++){
								escritor = new PrintWriter(Servidor.c.pos(new Nodo<Conexion>(null),i).getS().getOutputStream(),true);
								
								escritor.println(Servidor.actualizarLista());
							}
						}else{
							Servidor.d.getTa().append( mensajeRecibido + "\n");
							for (int i=0;i<Servidor.c.contar();i++){
								escritor = new PrintWriter(Servidor.c.pos(new Nodo<Conexion>(null),i).getS().getOutputStream(),true);
								escritor.println(mensajeRecibido);
							}
						}
					}
		}catch(Exception ex){
			
		}
	}
}
