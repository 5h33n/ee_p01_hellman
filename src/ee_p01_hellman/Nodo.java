package ee_p01_hellman;

public class Nodo<T>{
	 private T dato;
	 private Nodo<T> siguiente;
	 /**
	  * getter del Dato
	  * @return devuelve un dato de tipo T
	  */
	 public T getDato(){ 
		 return dato;
	 }
	 /**
	  * Setter de dato
	  * @param dato requiere un parámetro de tipo T
	  */
	 public void setDato(T dato){
		 this.dato=dato;
	 }
	 /**
	  * getter de siguiente
	  * @return regresa el valor a donde apunta siguiente, de tipo Nodo
	  */
	 public Nodo<T> getSiguiente(){
		 return siguiente;
	 }
	 /**
	  * Setter de siguiente 
	  * @param siguiente requiere un parámetro de tipo Nodo
	  */
	 public void setSiguiente(Nodo<T> siguiente){
		 this.siguiente=siguiente;
	 }
	 /**
	  * Constructor la clase nodo que incializa dato, y siguiente
	  * @param dato requiere un parámetro de tipo t, dato
	  */
	 public Nodo(T dato){
		 this.dato=dato;
		 siguiente=null;
	 }
	 /**
	  * Sobreescritura del método toString
	  * @return String dato
	  */
	 public String ToString(){
		 return ""+dato;
	 }
}
