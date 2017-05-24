/** 
* Program: Aplikacja 1 - TipOfTheDay
* Klasa MainApplication definiujaca glowne okno aplikacji
*/

package app;
import com.l2fprod.common.swing.JTipOfTheDay;
import com.l2fprod.common.swing.plaf.basic.BasicTipOfTheDayUI;
import com.l2fprod.common.swing.tips.DefaultTip;
import com.l2fprod.common.swing.tips.DefaultTipModel;

import javax.swing.ImageIcon;
import javax.swing.JTree;

public class Tips {
	
	/**
	 * Konstruktor bezparametrowy
	 */
	public Tips() 
	{    
		DefaultTipModel tips = new DefaultTipModel();

	    // plain text
	    tips
	      .add(new DefaultTip(
	        "tip1",
	        "Wykorzystaj wype³nianie tabeli losowymi liczbami, aby móc dokonywaæ na nich obliczeñ bez koniecznoœci rêcznego wpisywania."));

	    // html text
	    tips.add(new DefaultTip("tip2",
	      "<html>This is an html <b>TIP</b><br><center>"
	        + "<table border=\"1\">" + "<tr><td>1</td><td>entry 1</td></tr>"
	        + "<tr><td>2</td><td>entry 2</td></tr>"
	        + "<tr><td>3</td><td>entry 3</td></tr>" + "</table>"));

	    // a Component
	    tips.add(new DefaultTip("tip3", new JTree()));

	    // an Icon
	    tips.add(new DefaultTip("tip 4", new ImageIcon(BasicTipOfTheDayUI.class
	      .getResource("TipOfTheDay24.gif"))));

	    JTipOfTheDay totd = new JTipOfTheDay(tips);
	    totd.setCurrentTip(0);

	    totd.showDialog(totd);   
	}
  
	public static void main(String[] args)
	{
		new Tips();
	}
}
