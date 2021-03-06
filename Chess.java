import java.util.Scanner;
public class Chess {
	                                                           /*GIOCO DELLE n REGINE*/
	/*l rompicapo (o problema) delle otto regine � un problema che consiste nel trovare il modo di posizionare otto regine (pezzo degli scacchi) su una scacchiera 8x8 
	 * tali che nessuna di esse possa catturarne un'altra, usando i movimenti standard della regina. Perci�, una soluzione dovr� prevedere che nessuna regina abbia una colonna, 
	 * traversa o diagonale in comune con un'altra regina. 
	 * CASO GENERALE:
	 * Il problema delle otto regine � un esempio del pi� generale problema delle n regine, che consiste nel piazzare, 
	 * con le condizioni illustrate precedentemente, n regine su una scacchiera n � n; in questa forma, in particolare,
	 * esso viene spesso usato per illustrare tecniche di progettazione di algoritmi e di programmazione.
	 * � stato dimostrato matematicamente che il problema � risolvibile per n = 1 oppure n => 4, mentre non lo � per n = 2 ed n = 3.
	 * NB: Le soluzioni possono essere trovate tenendo conto della propriet� di simmetria della scacchiera.
	 * La matrice viene implementata come una matrice di bit in cui chess[i][j]=1 se in posizione (i,j) c'� una regina o la si inserisce , chess[i][j]=0 altrimenti*/
	
	private int[][] chess;//creazione della scacchiera come una matrice di interi
	
	public Chess(int n) {//costruttore che prende dimensione n della scacchiera
		chess = new int[n][n];//creazione della scacchiera come una matrice quadrata di interi di dimensione nxn
	}
	
	public boolean NQueens(int i) {
		//NB: i= indice di riga dove inserire la regina 
		if (i >= chess.length)//se si � fuori dalla matrice
			return true;//si assume che la matrice sia gi� stata esplorata e si ritorna true
		//else(i < chess.length)//se si � all'interno della matrice
		for (int j=0; j<chess.length; j++)//si scorrono le colonne della matrice 
			if (check(chess,i,j)) {//se inserimento � possibile
				chess[i][j] = 1;//si inserisce regina in posizione (i,j)
				if (NQueens(i+1))//se nella riga successiva si pu� inserire una regina allora � possibile trovare una soluzione
					return true;
				else //se nella riga successiva non si pu� inserire una regina allora non � possibile trovare una soluzione
					chess[i][j] = 0;//si cancella assegnamento effettuato
			}
		return false;
	}
	
	public static boolean check(int[][] ch, int row, int col) {//METODO DI VERIFICA DELLA PRESENZA DELLA REGINE NELLA RIGA row E COLONNA col
		
		for (int i=0; i<row; i++)//ciclo che scandisce elementi della colonna col
			if (ch[i][col]==1)//se nella colonna col c'� un elemento uguale ad 1 (contenente una regina) inserimento non � possibile nella colonna
				return false;
		int i = row; int j = col;//si inizializzano indici i e j per scandire righe e colonne lungo le diagonali
		//CICLO WHILE CHE SCANDISCE LA DIAGONALE DISCENDENTE \ PARTENDO DALLA CASELLA chess[row][col]
		while (i>0 && j>0) {//fino a quando indici sono positivi
			i--; j--;//decrementiamo indice di riga e colonna cos� facendo si sale nella scacchiera di una riga spostandosi a sinistra di una colonna
			if (ch[i][j]==1)//se lungo tale diagonale c'� una regina
				return false;//inseirimento non possibile
		}
		i = row; j = col;//si inizializzano indici i e j per scandire righe e colonne lungo le diagonali
		//CICLO WHILE CHE SCANDISCE LA DIAGONALE ASCENDENTE / PARTENDO DALLA CASELLA chess[row][col]
		while (i>0 && j<ch.length-1) {//fino a quando indici di riga � positivo e indice di colonna � all'interno della matrice
			i--; j++;//decrementiamo indice di riga e aumentiamo indice colonna cos� facendo si sale nella scacchiera di una riga spostandosi a destra di una colonna
			if (ch[i][j]==1)//se lungo tale diagonale c'� una regina
				return false;//inseirimento non possibile
		}
		return true;//se scandendo diagonali e colonna 
	}
	/*COMPLESSITA' DEL METODO check(int[][] ch, int row, int col)
	 * O(n)=complessit� temporale dovuta alla presenza dei cicli*/
	
	/*COMPLESSITA' DEL METODO NQueens(int i)
	 * NB:si trascurano operazioni e istruzioni la cui complessit� � costante
	 * T(NQueens,n)=T(ciclo while) x[T(check)+T(NQueens,n-1)].....esplicitando il calcolo
	 * T(NQueens,n)=T(ciclo while) x [T(check,n)+T(ciclo while,n-1)x[T(check,n-1)+T(NQueens,n-2)]
	 * ...svolgendo il conto
	 *T(NQueens,n)=O(n^n) caso peggiore in cui per trovare una soluzione bisogna scandire tutto lo spazio di ricerca */
	
	                                                 /*GIOCO DEL CAVALLO*/
	/*Il gioco consiste nello spostare il cavallo su una scacchiera nxn secondo la regola del gioco degli scacchi, con un movimento a L, 
	 *in modo da toccare tutte le caselle della scacchiera nel minor numero di mosse possibili. Non E� possibile ritornare sulle caselle gi� toccate.
	 *La scacchiera si implementa con una matrice di bit in cui chess[i][j]=k se in posizione (i,j) pu� posizionare cavallo/o c'� passato con mosso numero k , 
	 *chess[i][j]=0 nel caso in cui il cavallo non sia ancora passato in posizione (i,j).
	 *NB:il metodo appena trova una soluzione la restituisce non le cerca tutte .*/
	
	
	public boolean Horse() {//METODO CHE SCEGLIE UNA POSIZIONE DA CUI PARTIRE E CI DICE SE ESISTE UNA SOLUZIONE
		for (int i = 0; i < chess.length; i++) //si scorre tutta la matrice nelle righe 
			for (int j=0; j<chess.length; j++)  {//si scorre tutta la matrice nelle colonne
				chess[i][j] = 1;//si posiziona il cavallo nella cella
				if (Horse(chess, i, j, 1))//si richiama il metodo PRIVATO che verifica possibilit� di effettuare una mossa
					return true;//se si pu� fare un mossa � possibile trovare una soluzione
				else {
					chess[i][j] = 0;//se non si pu� fare una mossa si torna indietro alla ricerca di una soluzione alternativa cancellando assegramento fatto in precedenza
				}
			}
		return false;//caso in cui non esista alcuna soluzione
	}
	
	private static boolean Horse(int[][] ch, int i, int j, int k) {//METODO CHE VERIFICA POSSIBILITA' DI EFFETTUARE UNA MOSSA
		//NB: K=contatore del numero di mosse effettuate e quindi le celle esplorate dal cavallo
		if (k >= ch.length*ch.length)//se il numero di mosse effettuate � maggiore o uguale al numero di poszioni nella scacchiera
			return true;//allora si � trovata una soluzione poich� si � percorsa tutta le caselle della scacchiera
		//else : k < ch.length*ch.length ; non si � percorsa la scacchiera per intero 
		int k1 = k+1;//si aggiorna in numero di mosse incrementandolo di uno e salvandolo nella variabile k1
/*VERIFICA DELLE MOSSE POSSIBILI DEL CAVALLO
 * CONSIDERAZIONE: un cavallo(previa disponibilit� di spazio lungo la scacchiera) pu� muovere lungo le 4 direnzioni principali (sopra,sotto,destra,sinistra) e per ogni direzione
 * ha a disposizione 2 versi di movimento(destra,sinistra) quindi in totale si possono effettuare al massimo 8 mosse diverse .*/
		
		
	// move 1
		int i1 = i+1;//si scende in basso di una riga lungo la scacchiera 
		int j1 = j+2;//ci si muove a destra di due caselle rispetto alle colonne
		if (move(ch, i1, j1, k1))// se la mossa � possibile 
			return true;
		
		// move 2
		i1 = i+1;//si scende in basso di una riga lungo la scacchiera
		j1 = j-2;//ci si muove a sinistra di due caselle rispetto alle colonne
		if (move(ch, i1, j1, k1))// se la mossa � possibile 
			return true;
		
		// move 3
		i1 = i+2;//si scende in basso di una due lungo la scacchiera 
		j1 = j+1;//ci si muove a destra di una casella rispetto alle colonne
		if (move(ch, i1, j1, k1))// se la mossa � possibile 
			return true;
		
		// move 4
		i1 = i+2;//si scende in basso di una due lungo la scacchiera 
		j1 = j-1;//ci si muove a sinistra di una casella rispetto alle colonne
		if (move(ch, i1, j1, k1))// se la mossa � possibile 
			return true;
		
		// move 5
		i1 = i-1;//si sale in alto di una riga lungo la scacchiera
		j1 = j+2;//ci si muove a destra di due caselle rispetto alle colonne
		if (move(ch, i1, j1, k1))// se la mossa � possibile 
			return true;
		
		// move 6
		i1 = i-1;//si sale in alto di una riga lungo la scacchiera
		j1 = j-2;//ci si muove a sinistra di due caselle rispetto alle colonne
		if (move(ch, i1, j1, k1))// se la mossa � possibile 
			return true;
		
		// move 7
		i1 = i-2;//si sale in alto di due righe lungo la scacchiera
		j1 = j+1;//ci si muove a destra di una casella rispetto alle colonne
		if (move(ch, i1, j1, k1))// se la mossa � possibile 
			return true;
		
		// move 8
		i1 = i-2;//si sale in alto di due righe lungo la scacchiera
		j1 = j-1;//ci si muove a sinistra di una casella rispetto alle colonne
		if (move(ch, i1, j1, k1))// se la mossa � possibile 
			return true;
		
		return false;//nel caso in cui non sia pi� possibile fare una mossa 
		
	}
		
	private static boolean move(int[][] ch, int i1, int j1, int k1) {//METODO CHE EFFETTUA UNA MOSSA VERIFICANDONE LA AMMISSIBILITA'
		if (i1 >= 0 && i1 < ch.length && j1 >= 0 && j1 < ch.length && ch[i1][j1] == 0) {
//	se indice di riga � positivo e minore della lunghezza della matrice e indice di colonna � positivo e minore della larghezza della matrice 
//siamo all'interno della matrice quindi la mossa � potenzialmente possibile
			ch[i1][j1] = k1;//si effettua la mossa spostando il cavallo in posizione (i,j) riempiendo la casella con il numero della mossa
			if (Horse(ch,i1,j1,k1))//si richiama la funzione PRIVATA 
				return true;//caso in cui mossa � possibile
			else  {//se la mossa non � possibile 
				ch[i1][j1] = 0;//si annulla inserimento fatto 
				return false;//si restituisce false
			}
		}
		return false;//caso in cui non sia possibile effettuare la mossa
	}
		
	public void print() {//METODO DI STAMPA DELLA MATRICE CHE RESTITUISCE LA PRIMA SOLUZIONE TROVATA
		for (int i=0; i < chess.length; i++) {
			for (int j=0; j < chess[0].length; j++)
				System.out.print(" " + chess[i][j]);
			System.out.println();
		}
	}

}
