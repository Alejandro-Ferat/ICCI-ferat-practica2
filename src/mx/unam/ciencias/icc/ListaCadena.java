package mx.unam.ciencias.icc;

/**
 * <p>Clase para listas doblemente ligadas de cadenas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas son iterables utilizando sus nodos.</p>
 */
public class ListaCadena {

    /**
     * Clase Nodo para uso interno de la clase ListaCadena.
     */
    public class Nodo {

        /* El elemento del nodo. */
        private String elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /**
         * Construye un nodo con un elemento.
         * @param elemento el elemento del nodo.
         */
        public Nodo(String elemento) {
            this.elemento=elemento;
	    anterior=null;
	    siguiente=null;
        }

        /**
         * Regresa el nodo anterior del nodo.
         * @return el nodo anterior del nodo.
         */
        public Nodo getAnterior() {
            return(anterior);
        }

        /**
         * Regresa el nodo siguiente del nodo.
         * @return el nodo siguiente del nodo.
         */
        public Nodo getSiguiente() {
            return(siguiente);
        }

        /**
         * Regresa el elemento del nodo.
         * @return el elemento del nodo.
         */
        public String get() {
            return(elemento);
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        return(longitud);
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean esVacia() {
        if(cabeza == null){
	    return(true);
	}else{
	return(false);
	}
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     */
    public void agregaFinal(String elemento) {
	Nodo nodo= new Nodo(elemento);
	if(this.esVacia()){
	    cabeza=nodo;
	    rabo=cabeza;
	}else{
	    rabo.siguiente=nodo;
	    nodo.anterior=rabo;
	    rabo=nodo;	    
      	}	
	longitud=longitud+1;

    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     */
    public void agregaInicio(String elemento) {
        Nodo nodo= new Nodo(elemento);
	if(this.esVacia()){
	    cabeza=nodo;
	    rabo=cabeza;
	}else{
	    cabeza.anterior=nodo;
	    nodo.siguiente=cabeza;
	    cabeza=nodo;	    
      	}	
	longitud=longitud+1;
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     */
    public void inserta(int i, String elemento) {
        if( i <1){
	    this.agregaInicio(elemento);
	    return;
	}else if( (0 < i) && (i < longitud) ){
	    Nodo contador = new Nodo(null);
	    contador=cabeza;	    
	    int k=0;
	    while(k < i){
		contador=contador.siguiente;
		k=k+1;
	    }
	    Nodo nuevo = new Nodo(elemento);
	    nuevo.anterior=contador.anterior;
	    nuevo.siguiente=contador;
	    contador.anterior.siguiente=nuevo;
	    contador.anterior=nuevo;
	    longitud = longitud+1;
	    return;
	}else if( longitud -1 <i){
	    this.agregaFinal(elemento);
	    return;
	} 
     
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(String elemento) {
	if(this.esVacia()){
	    return;
	}else{
	    Nodo contador = cabeza;
	    while( contador != null){
		if(contador.elemento.equals(elemento)){
		    if(contador == cabeza){
			this.eliminaPrimero();
			return;
		    }else if(contador == rabo){
			this.eliminaUltimo();
			return;
		    }else{
			contador.anterior.siguiente=contador.siguiente;
			contador.siguiente.anterior=contador.anterior;
			contador=null;
			longitud=longitud-1;
			return;
		    }
		}else{
		    contador=contador.siguiente;
		}
	    }	   		
	}
    }   


    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista está vacía.
     */
    public String eliminaPrimero() {
        if(this.esVacia()){
	    return null;
	}else if(longitud ==1){
	    String primero = cabeza.elemento;
	    cabeza=null;
	    rabo = null;
	    longitud=longitud-1;
	    return(primero);
	}else{
	    String primero = cabeza.elemento;
	    cabeza.siguiente.anterior=null;
	    cabeza=cabeza.siguiente;
	    longitud= longitud-1;
	    return(primero);
	}
	
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista está vacía.
     */
    public String eliminaUltimo() {
	if(this.esVacia()){
	    return(null);
	}else if(longitud == 1){
	    String ultimo = rabo.elemento;
	    rabo = null;
	    cabeza = null;
	    longitud--;
	    return(ultimo);
	}else{
	    String ultimo =rabo.elemento;
	    rabo.anterior.siguiente=null;
	    rabo=rabo.anterior;
	    longitud=longitud-1;
	    return(ultimo);
	}
	
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <tt>true</tt> si <tt>elemento</tt> está en la lista,
     *         <tt>false</tt> en otro caso.
     */
    public boolean contiene(String elemento) {
        Nodo contador= cabeza;
	while(!(contador==null)){
	    if(contador.elemento.equals(elemento)){
		return(true);
	    }else{
		contador=contador.siguiente;
	    }

	}
	return(false);
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public ListaCadena reversa() {
        ListaCadena reversa = new ListaCadena();
	if(this.esVacia()){
	    return(reversa);
	}else{
	    Nodo contador =new Nodo(null);
	    contador=this.rabo;
	    while(contador != null){
		reversa.agregaFinal(contador.elemento);
		contador = contador.anterior;		
	    }
	    return(reversa);
	}
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public ListaCadena copia() {
        ListaCadena copia = new ListaCadena();
	if(this.esVacia()){
	    return(copia);
	}else{
	    Nodo contador =new Nodo(null);
	    contador=this.cabeza;
	    while(contador != null){
		copia.agregaFinal(contador.elemento);
		contador = contador.siguiente;		
	    }
	    return(copia);
	}

    }

    /**
     * Limpia la lista de elementos. El llamar este método es equivalente a
     * eliminar todos los elementos de la lista.
     */
    public void limpia(){
	if(this.esVacia()){
	    return;
	}else{
	    while(longitud != 0){
		this.eliminaPrimero();
	    }
	    return;
	}
	
    }
	

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public String getPrimero() {
        if(this.esVacia() ){
	    return(null);
	}else{
	    return(cabeza.elemento);
	}
	
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public String getUltimo() {
	 if(this.esVacia() ){
	    return(null);
	}else{
	    return(rabo.elemento);
	}
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista. Si el índice es menor
     * que cero o mayor o igual que el número de elementos de la lista, el
     * método regresa <tt>null</tt>.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista, si <em>i</em> es mayor
     *         o igual que cero y menor que el número de elementos en la lista;
     *         <tt>null</tt> en otro caso.
     */
    public String get(int i) {
	String elemento = null;
	if( i <0){
	    elemento=null;
	}else if( (0<=i)  && (i< longitud) ){
	    Nodo contador = new Nodo(null);
	    contador =cabeza;
	    for(int k=0; k<=i; k++){
		elemento=contador.elemento;
		contador=contador.siguiente;
	    }
       }else if(longitud-1 < i){
	    elemento=null;
       }
	return(elemento);
    } 


    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(String elemento){
        if(this.esVacia()){
	    return(-1);
	}else{
	    Nodo contador = new Nodo(null);
	    int  indice =0;
	    contador=cabeza;
	    while( contador != null){
		if(contador.elemento.equals(elemento)){
		    return(indice);
		}else{
		    contador=contador.siguiente;
		    indice=indice+1;
		}	       
	    }
	}
	return(-1);
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    public String toString(){
	if(this.esVacia()){
	    String s="[]";
	    return(s);
	}else if(longitud==1){
	    String s= "["+ String.format("%s]", this.get(0));
	    return(s);
	}else{
        String s = "[";
        for (int i = 0; i < longitud-1; i++)
            s += String.format("%s, ", this.get(i));
        s += String.format("%s]", this.get(longitud-1));
	return(s);
	}
    }

    /**
     * Regresa el nodo cabeza de la lista.
     * @return el nodo cabeza de la lista.
     */
    public Nodo getCabeza(){
        return(cabeza);
    }

    /**
     * Regresa el nodo rabo de la lista.
     * @return el nodo rabo de la lista.
     */
    public Nodo getRabo(){
        return(rabo);
    }
}
