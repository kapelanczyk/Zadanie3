/** 
* Program: Aplikacja 1 - Logger
* Klasa MyLogger definiujaca glowne loger zdarzen
* @author lukasz kapelanczyk	
* @version 1.0 05.2015
*/

package app;
import org.apache.log4j.*;
import org.apache.log4j.xml.DOMConfigurator;

public class MyLogger {
	static final Logger logger = Logger.getLogger("logger");
	   
	MyLogger()
	{
		DOMConfigurator.configure("config/log4j-conf.xml");  
	}

	/**
	 * Metoda fatal() typu void, nie zwracaj¹ca wartoœci.
	 * Nadaje wartosc do loggera kategorii FATAL
	 * @param value jest wartoscia typu string, ktora jest wiadomoscia komunikatu
	 */	
	public void fatal(String value)
	{
		logger.fatal(value);
	}

	/**
	 * Metoda error() typu void, nie zwracaj¹ca wartoœci.
	 * Nadaje wartosc do loggera kategorii ERROR
	 * @param value jest wartoscia typu string, ktora jest wiadomoscia komunikatu
	 */
	public void error(String value)
	{
		logger.error(value);
	}

	/**
	 * Metoda warn() typu void, nie zwracaj¹ca wartoœci.
	 * Nadaje wartosc do loggera kategorii WARN
	 * @param value jest wartoscia typu string, ktora jest wiadomoscia komunikatu
	 */
	public void warn(String value)
	{
		logger.warn(value);
	}

	/**
	 * Metoda info() typu void, nie zwracaj¹ca wartoœci.
	 * Nadaje wartosc do loggera kategorii INFO
	 * @param value jest wartoscia typu string, ktora jest wiadomoscia komunikatu
	 */
	public void info(String value)
	{
		logger.info(value);
	}

	/**
	 * Metoda debug() typu void, nie zwracaj¹ca wartoœci.
	 * Nadaje wartosc do loggera kategorii DEBUG
	 * @param value jest wartoscia typu string, ktora jest wiadomoscia komunikatu
	 */
	public void debug(String value)
	{
		logger.debug(value);
	}
}