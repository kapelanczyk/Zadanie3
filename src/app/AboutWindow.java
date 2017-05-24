/** 
* Program: Aplikacja 1 - Okno autora
* Klasa MainApplication definiujaca glowne okno aplikacji

*/

package app;
import javax.swing.*;

class AboutWindow
{
 	/**
 	 * Konstruktor bezparametrowy, tworzy okienko typu modal w wskazan¹ zawartoœci¹
 	 */	
	AboutWindow()
	{
	    String message = "<html><h2>Aplikacja nr 2 i 3</h2>"
	    		+ "<p>Data: 18 maj 2017</p><br/>"
	    		+ "<p><b>Autor</b></p>"
	    		+ "<p>£ukasz Kapelañczyk</p>"
	    		+ "<p>kontakt: lukasz.kapelanczyk@gmial.com</p></html>";

	    ImageIcon icon=new ImageIcon("./images/program.png");
	    JOptionPane.showMessageDialog(null, message, "Informacja o programie", JOptionPane.ERROR_MESSAGE,icon);		
	}	
}