package ee_p01_hellman;

import java.net.*;

public class Conexion {
	private Socket s;
	private String nick;
	
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public Conexion(Socket s,String nick){
		this.s = s;
		this.nick = nick;
	}
	public String toString(){
		return ""+nick;
	}

}
