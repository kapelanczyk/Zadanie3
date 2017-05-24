/** 
* Model tabeli
* Klasa <code>TableModel</code> definiujaca model tabeli

*/

package app;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings({ "serial", "unused" })
public class TableModel extends AbstractTableModel 
{
	private final int countRowTable = 5;
	private final int countColumnTable = 5;
	private Integer[][] dataTable = new Integer[5][5];
	private String[] colNames = {"1","2","3","4","5"};
	
	/**
	 * Konstruktor bezparametrowy klasy <code>TableModel</code>
	 */
	public TableModel() {
		emptyTable();
	}

	/**
	 * Metoda getColumnCount() typu int, nie przyjmuj¹ca wartosci 
	 * Zwraca ilosc kolumn w tabeli.
	 * @return countColumnTable zwraca ilosc kolumn tabeli
	 */	
	public int getColumnCount() {
		return countColumnTable;
	}

	/**
	 * Metoda getRowCount() typu int, nie przyjmuj¹ca wartosci 
	 * Zwraca ilosc wierszy w tabeli.
	 * @return getRowCount zwraca ilosc wierszy tabeli
	 */
	public int getRowCount() {
		return countColumnTable;
	}

	/**
	 * Metoda getValueAt() typu Object
	 * Zwraca wartosc komorki ze wskazanych wspolrzednych
	 * @param row okreslajaca numer wiersza tabeli, typ int
	 * @param col okreslajaca numer kolumny tabeli, typ int
	 * @return zwraca zawartosc komorki pod wskazanymi wspolrzednymi
	 */
    public Object getValueAt(int row, int col) {
        return dataTable[row][col];
    }

	/**
	 * Metoda setValue() typu void, nie zwraca wartosci
	 * Wpisuje w podana komorke, wartosc z argumentu funkcji
	 * @param value bedaca wartoscia do wpisania w tablicy, typ int
	 * @param row okreslajaca numer wiersza tabeli, typ int
	 * @param col okreslajaca numer kolumny tabeli, typ int
	 */
	public void setValue(Integer value, int row, int col) {
		dataTable[row][col] = value;
		fireTableDataChanged();	
	}
	
	/**
	 * Metoda randInt() typu void, nie przyjmuj¹ca wartosci 
	 * Wypelnia tabele pseudolosowymi wartosciami z zakresu 1-999.
	 */		
	public void randInt() {
		Random random = new Random();
		for(int i=0; i<countRowTable; i++)
			for(int j=0; j<countColumnTable; j++) {
				dataTable[i][j] = Math.abs(random.nextInt()) % 999;
			}
		fireTableDataChanged();
	}
	
	/**
	 * Metoda emptyTable() typu void, nie przyjmuj¹ca wartosci 
	 * Wypelnia tabele "0".
	 */	
	public void emptyTable() {
		for(int i=0; i<countRowTable; i++)
			for(int j=0; j<countColumnTable; j++) {
				dataTable[i][j] = new Integer(0);
			}
		fireTableDataChanged();
	}

	/**
	 * Metoda calculateSum() typu int, nie przyjmuj¹ca wartosci 
	 * Zwraca wartosc sumy wszystkich komorek tabeli
	 * @return sum zwraca wartosc policzonej sumy elementow tabeli
	 */	
	public Integer calculateSum() {
		Integer sum = new Integer(0);
		for(int i=0; i<countRowTable; i++)
			for(int j=0; j<countColumnTable; j++) {
				sum = sum + dataTable[i][j];
			}
		return sum;
	}
	
	/**
	 * Metoda calculateAverage() typu float, nie przyjmuj¹ca wartosci 
	 * Zwraca wartosc srednia wszystkich komorek tabeli
	 * @return avg zwraca wartosc policzonej sredniej elementow tabeli
	 */		
	public Float calculateAverage() {
		Float avg = new Float(0.0);
		Integer sum = calculateSum();
		if(sum > 0) avg = (sum.floatValue())/(countRowTable*countColumnTable);
		return avg;
	}
	
	/**
	 * Metoda findMin() typu float, nie przyjmuj¹ca wartosci 
	 * Zwraca najmniejsza wartosc z tabeli
	 * @return min zwraca najmniejsza wartosc z tabeli
	 */		
	public Float findMin() {
		float temp = 0;
		float min=dataTable[0][0];
		
		for(int i=0; i<countRowTable; i++)
			for(int j=0; j<countColumnTable; j++) {
            	temp=dataTable[i][j];
            	if(temp<min)min=temp;
			}
		return min;	
	}

	/**
	 * Metoda findMax() typu float, nie przyjmuj¹ca wartosci 
	 * Zwraca najwieksza wartosc z tabeli
	 * @return max zwraca najwieksza wartosc z tabeli
	 */	
	public Float findMax() {
		float temp = 0;
		float max=dataTable[0][0];
		
		for(int i=0; i<countRowTable; i++)
			for(int j=0; j<countColumnTable; j++) {
            	temp=dataTable[i][j];
            	if(temp>max)max=temp;
			}
		return max;	
	}
	
    public String getColumnName(int index) { 
        return colNames[index]; 
    }
}
