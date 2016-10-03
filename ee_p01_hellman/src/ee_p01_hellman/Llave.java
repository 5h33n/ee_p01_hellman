package ee_p01_hellman;

import java.util.Random;

import javax.swing.JOptionPane;

public class Llave {
	ListaLigada<Integer> llpublica = new ListaLigada<Integer>();
	Object[] llprivada = new Object[3];
	Integer w,q,r;
	Random rnd = new Random();
	
	public Llave(){
		generarPublica();
	}
	public void generarPublica(){
		ListaLigada<Integer> w = new ListaLigada<Integer>();
		Integer l1=1,l2=1,acum=0;
		for(int i=0;i<8;i++){
			Integer al = (int) (rnd.nextDouble() * l2 + l1);
			w.insertaInicio(al);
			l1 = (l1*(i+1))+(int) (rnd.nextDouble() * 50 + 1);
			l2 = l1*2;
			acum=+al;
		}
		q=acum + (int) (rnd.nextDouble() * 50 + 1);
		r=q*2;
		while(mcd(r,q)!=1){
			q = q+(int) (rnd.nextDouble() * 10 + 1);
		}
		for(int i=7;i>=0;i--){
			//Aquí puede haber un error
			llpublica.insertaInicio((r*(w.pos(new Nodo<Integer>(null), i))) % q);
		}
		llprivada[0]=w;
		llprivada[1]=q;
		llprivada[2]=r;
	}
	public Integer mcd(Integer a, Integer b){
		Integer min, max, resto, mcd=0;
		min = Math.min(a, b);
		max = Math.max(a, b);
		while(min!=0){
			resto = max % min;
			max=min;
			min=resto;
		}
		mcd=max;
		return mcd;
	}
	public Integer cifrar(String texto){
		Integer codigo=0;
		char[] tC = texto.toCharArray();
		for(int i=0;i<tC.length;i++){
			ListaLigada<Integer> aux = new ListaLigada<Integer>();
			String Byte= Integer.toBinaryString(tC[i]);
			if (Byte.length()==7){
				Byte = "0"+Byte;
			}else if(Byte.length()==6){
				Byte = "00"+Byte;
			}else if(Byte.length()==5){
				Byte = "000"+Byte;
			}else if(Byte.length()==4){
				Byte = "0000"+Byte;
			}else if(Byte.length()==3){
				Byte = "00000"+Byte;
			}else if(Byte.length()==2){
				Byte = "000000"+Byte;
			}else if(Byte.length()==1){
				Byte = "0000000"+Byte;
			}
			String[] c= Byte.split("");
			for(int a=0;a<c.length;a++){
				aux.insertaInicio(Integer.parseInt(c[a]));
			}
			for(int a=0;a<8;a++){
				codigo+=aux.pos(new Nodo<Integer>(null), a) * llpublica.pos(new Nodo<Integer>(null), a);
			}
		}
		return codigo;
	}
}
