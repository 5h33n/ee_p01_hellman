package ee_p01_hellman;

import javax.swing.JOptionPane;

/**
 * Esta clase se encarga de generar y gestionar listas ligadas de un tipo dado
 * @author Oscar Eduardo López Guzmán (Sheen)
 * 22/09/2016
 * @param <T> Tipo de dato que definirá de qué tipo será la lista ligada
 */
public class ListaLigada<T>{
	/**
	 * Se crea una varíable inicio de tipo nodo
	 */
	
     Nodo<T> inicio;
    /**
     * Getter de inicio
     * @return Devuelve un dato de tipo nodo
     */
    public Nodo<T> getInicio(){
    	return inicio; 
    }
    /**
     * Setter de Inicio
     * @param inicio de tipo nodo
     */
    public void setInicio(Nodo<T> inicio){
    	this.inicio=inicio; 
    }
    public boolean estaVacia(){
    	boolean r=true;
    	if(! (inicio==null)){
    		r=false;
    	}
    	return r;
    }
    public void eliminar(T dato){
    	if(! (estaVacia())){
    		if ( contar()==1 && dato==inicio.getDato()){
    			inicio=null;
    		}else if(dato==inicio.getDato()){
    			inicio=inicio.getSiguiente();
    		}else{
    			Nodo<T> anterior, temp;
    			anterior=inicio;
    			temp=inicio.getSiguiente();
    			while(temp!=null && temp.getDato()!=dato){
    				anterior = anterior.getSiguiente();
    				temp = temp.getSiguiente();
    			}
    			if(temp!=null){
    				anterior.setSiguiente(temp.getSiguiente());
    			}
    		}
    	}
    	
    }
    /**
     * Método que recorre los elementos de una lista ligada dada
     * @param n es un nodo auxiliar
     */
    public Integer contar(){
    	Nodo<T> n = new Nodo<T>(null);
    	n=inicio;
    	Integer numero=0;
    	while(n!=null){
    		numero++;
    		n=n.getSiguiente();
    	}
    	return numero;
    }
    public void imprimir(Nodo<T> n){
    	n=inicio;
    	while(n!=null){
    		System.out.println(n.getDato()+"-->");
    		n=n.getSiguiente();
    	}
    }
    public T pos(Nodo<T> n, Integer p){
    	T dato=null;
    	n= inicio;
    	Integer numero=0;
    	if(n!=null){
    		if (p==0){
    			dato = n.getDato();
    		}else{
    			while(numero!=p){
    				numero++;
    				n=n.getSiguiente();
    			}
    			dato = n.getDato();
    		}
    	}
    	return dato;
    }

    /**
     * Este método inserta un dato, en un nodo al inicio de la lista ligada
     * @param dato El dato que se insertará
     */
    public void insertaInicio(T dato){
    	Nodo<T> n=new Nodo<T>(dato);
    	n.setSiguiente(inicio);
    	inicio=n;
    }
    /**
     * Este método inserta un dato, en un nodo al inicio de la lista ligada
     * @param dato El dato que se insertará
     */
    public void insertaFinal(T dato){
    	Nodo<T> n = new Nodo<T>(dato);
    	System.out.println(n.toString());
    	Nodo<T> aux=inicio;
    	
    	while(aux.getSiguiente()!=null){
    		aux=aux.getSiguiente();
    	}
    	aux.setSiguiente(n);
    	System.out.println(aux.toString());
    }

    /**
     * Este metodo, inserta un dato antes de otro especificado
     * @param r el dato antes del cual se insertará
     * @param dato el dato que se insertará
     */
    public void insertaAntesDe(T r,T dato){
    	Nodo<T> n=new Nodo<T>(dato);
    	Nodo<T> aux=inicio;
    	Nodo<T> aux2=inicio;
    	while(aux.getSiguiente()!=null){
    		if(aux==inicio && aux.getDato().equals(r)){
    			this.insertaInicio(dato);
    		}
    		aux=aux.getSiguiente();
    		if(aux.getDato().equals(r)){
    			aux2.setSiguiente(n);
    			n.setSiguiente(aux);
    		}else{
    			aux2=aux;
    		}
    	}
    }
    /**
     * Método toString modificado
     */
    public String toString(){
    	String s="";
    	Nodo<T> n=inicio;
    	while(n!=null){
    		s+=(n.getDato()+" -->");
    		n=n.getSiguiente();
    	}s+=".";
    	return s;
    }
}

