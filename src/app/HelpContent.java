/** 
* Program: Aplikacja 1 - Kontekst pomocy
* Klasa MainApplication definiujaca glowne okno aplikacji

*/

package app;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

@SuppressWarnings("serial")
class HelpContent extends JFrame 
{
	/**
	 * Konstruktor bezparametrowy
	 */
	@SuppressWarnings("deprecation")
	HelpContent()
	  {
		     JEditorPane jep = new JEditorPane();
		     jep.setEditable(false);   
		     
		     try {
		    	 String url = "file:///" + System.getProperty("user.dir") + "/help/index.html";
		    	 jep.setContentType("text/html");
		    	 jep.setPage(url);
		     }
		     catch (IOException e) {
		       jep.setContentType("text/html");
		       jep.setText("Nie mo¿na za³adowaæ pliku pomocy!");
		     } 
		      
		     JScrollPane scrollPane = new JScrollPane(jep);     
		     JFrame f = new JFrame("Kontekst pomocy");
		 	/**
		 	 * Fragment kodu wykorzystany z serwisu StackOverflow: http://stackoverflow.com/questions/7291956/swing-jdialog-jtextpane-and-html-links
		 	 * Pozwalaj¹cy na pos³ugiwanie siê odnoœnikami do danej czêœci strony - kotwice.
		 	 */	
			    jep.addHyperlinkListener(new HyperlinkListener() 
			    {
			        public void hyperlinkUpdate(HyperlinkEvent pE) 
			        {
			            if (HyperlinkEvent.EventType.ACTIVATED == pE.getEventType()) 
			            {
			                String desc = pE.getDescription();
			                if (desc == null || !desc.startsWith("#")) return;
			                desc = desc.substring(1);
			                jep.scrollToReference(desc);
			            }
			        }
			    });	
			    
		     f.getContentPane().add(scrollPane);
		     f.setSize(800, 600);
		     f.setLocationRelativeTo(null);
		     f.show();
	  }
}