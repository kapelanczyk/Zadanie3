/** 
* Program: Aplikacja 1
* Klasa MainApplication g³ówne okno aplikacji
* @author £ukasz Kapelanczyk	
* @version 1.2.3-a
* java 
* apache and 
* apache log4j
* zad3 properties przez XML plik konfiguracyjny
* dokumentacja do .doc
* konfiguracja do .conf
*/

package app;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.freixas.jcalendar.*;

import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTaskPaneGroup;

@SuppressWarnings("serial")
public class MainApplication extends JFrame implements ActionListener
{
	
	/**
	 * Zmienne komponentów tworz¹ce menu oraz elementów menu
	 */
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileMenu = new JMenu();
	private JMenu viewMenu = new JMenu();
	private JMenu calcMenu = new JMenu();
	private JMenu helpMenu = new JMenu();
	
	private JMenuItem openItem = new JMenuItem();
	private JMenuItem clearItem = new JMenuItem();
	private JMenuItem saveItem = new JMenuItem();
	private JMenuItem exitItem = new JMenuItem();
	
	private JMenuItem sumItem = new JMenuItem();
	private JMenuItem averageItem = new JMenuItem();
	private JMenuItem maxItem = new JMenuItem();
	private JMenuItem minItem = new JMenuItem();
	
	
	private JCheckBoxMenuItem showMenuBarItem=new JCheckBoxMenuItem();
	private JCheckBoxMenuItem showStatusBarItem=new JCheckBoxMenuItem();
	
	private JMenuItem helpItem = new JMenuItem();
	private JMenuItem authorItem = new JMenuItem();
	
	/**
	 * Komponent JFileChooser tworz¹cy obiekt, pozwalaj¹cy na uruchomienie mechanizmu wyboru plików przez u¿ytkownika
	 */	
	private JCalendarCombo calendar;

	/**
	 * Zmienne komponentów tworz¹ce pasek narzêdziowy - ToolBar oraz tabelê 
	 */	
	private JToolBar toolBar = new JToolBar();
	private JTable table;

	/**
	 * Zmienne komponentu tworz¹ce obiekty przycisków
	 */	
	private JButton buttonToolBarInsert, buttonToolBarSave, buttonToolBarHelp, buttonToolBarAbout, buttonToolBarAdd, buttonToolBarClear,
	buttonToolBarSum, buttonToolBarAverage, buttonToolBarMin, buttonToolBarMax, buttonToolBarExit,
	buttonAdd, buttonClear, buttonInsert, buttonSave, buttonCalculate;
	JButton[] buttons  = new JButton[12];

	
	/**
	 * Zmienne komponentu tworz¹ce obiekty pól tekstowych
	 */	
	private JTextField fieldEnter, fieldInfo, fieldStatus;

	/**
	 * Zmienne komponentu tworz¹ce obiekty etykiet
	 */	
	private JLabel labelOption, labelEnter, labelInfo, labelStatus;

	/**
	 * Zminne komponentu tworz¹ce obiekty paneli
	 */	
	private JPanel panelCenter = new JPanel();
	private JPanel contentPane = new JPanel();
	private JPanel panelMain = new JPanel();
	private JPanel panelLeft = new JPanel();
	private JPanel resultPanel, panelBottom;
	
	/**
	 * Zmienna komponentu tworz¹ce obiekt obszaru tekstowego
	 */	
	private JTextArea resultTextArea;
	
	/**
	 * Zmienne komponentów tworz¹ce obiekt pola tekstowego sterowanego przyciskami oraz model tabeli
	 */	
	private JSpinner spinnerRow, spinnerCol;
	//private DefaultTableModel tableModel;
	
	/**
	 * Zmienne komponentow tworzace obiekt obramowania, obramowania tytulu oraz listy
	 */		
	private Border blackLine;
	private TitledBorder borderTitle;
	@SuppressWarnings("rawtypes")
	private JList list;
	
	
	private TableModel tableModel;
	private MyLogger logger=null;
	
	@SuppressWarnings("unused")
	private String[] columns = {"kol.1","kol.2","kol.3","kol.4","kol.5"};
	
	/**
	 * Konstruktor bezparametrowy klasy MainApplication
	 */		
	MainApplication()
	{
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);		
		setTitle("Zadanie 1 - Projektowanie aplikacji");
		setSize(800,560);
		setResizable(false);
		setLocationRelativeTo(null);
		logger = new MyLogger();
		logger.info("Uruchomienie aplikacji przez u¿ytkownika");
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		initGUI();
		
		/**
		 * Pattrelayout ustawianie Formatu Loggera 
		 * INFO:i dalej zale¿y od argumentu w patternlayout
		 */
		
		
		this.addWindowListener (new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				logger.info("Zamkniecie aplikacji przez u¿ytkownika");
				dispose();
				new Tips();
				System.exit(0);
			}
		});	
	}
	
	/**
	 * Metoda initGUI() typu void, nie przyjmuj¹ca i nie zwracaj¹ca wartoœci 
	 * Tworzy interfejs GUI w oparciu o zestawienie innych metod.
	 */		
	public void initGUI()
	{
		try
		{
		CreateMenu();
		CreateToolBarIcons();
		MainPanel();
		
		CreatePanelCenter();
	
		panelLeft();
		panelCenter();
		
		BottomPanel();
		setStatus();
		}
		catch(Exception e)
		{
			logger.error("Blad podczas tworzenia interfejsu GUI");
		}
	}
	
	/**
	 * Metoda MainPanel() typu void, nie przyjmuj¹ca i nie zwracaj¹ca wartoœci.
	 * Tworzy g³ówny panel i dodaje go do ramki.
	 */		
	public void MainPanel()
	{
		contentPane.add(panelMain, BorderLayout.NORTH);
		contentPane.add(toolBar, BorderLayout.NORTH);		
	}

	/**
	 * Metoda CreateMenu() typu void, nie przyjmuj¹ca i nie zwracaj¹ca wartoœci.
	 * Tworzy menu wraz z jego elementami
	 */	
	@SuppressWarnings("deprecation")
	public void CreateMenu()
	{
		try
		{
		    setJMenuBar(menuBar);
		    
		    fileMenu.setLabel("Plik");
		    calcMenu.setLabel("Obliczenia");
		    viewMenu.setLabel("Widok");
		    helpMenu.setLabel("Pomoc");
		    menuBar.add(fileMenu);
		    menuBar.add(calcMenu);
		    menuBar.add(viewMenu);
		    menuBar.add(helpMenu);
		    
		    //Image img=new ImageIcon(getClass().getResource("./images/api.png")).getImage();

		    //System.out.println(img);
		    //bindowanie klawiatury .......>>>
		 
		    Icon fillIcon = new ImageIcon("./images/random.png");
		    openItem.setLabel("Wype³nij");
		    openItem.setIcon(fillIcon);
		    openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
		    openItem.addActionListener(this);
		    fileMenu.add(openItem);
		    
		    Icon clearIcon = new ImageIcon("./images/reset.png");
		    clearItem.setLabel("Wyzeruj");
		    clearItem.setIcon(clearIcon);
		    clearItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
		    clearItem.addActionListener(this);
		    fileMenu.add(clearItem);
		    
		    Icon saveIcon = new ImageIcon("./images/save.png");
		    saveItem.setLabel("Zapisz");
		    saveItem.setIcon(saveIcon);
		    saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		    saveItem.addActionListener(this);
		    fileMenu.add(saveItem);		    
		    
		    Icon exitIcon = new ImageIcon("./images/close.jpg");
		    fileMenu.addSeparator();
		    exitItem.setLabel("Wyjœcie");
		    exitItem.setIcon(exitIcon);
		    exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		    exitItem.addActionListener(this);
		    fileMenu.add(exitItem);
		    
		    Icon sumIcon = new ImageIcon("./images/sum.png");
		    sumItem.setLabel("Suma wartoœci");
		    sumItem.setIcon(sumIcon);
		    sumItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		    sumItem.addActionListener(this);
		    calcMenu.add(sumItem);
		    
		    Icon averIcon = new ImageIcon("./images/avg.png");
		    averageItem.setLabel("Œrednia wartoœci");
		    averageItem.setIcon(averIcon);
		    averageItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
		    averageItem.addActionListener(this);
		    calcMenu.add(averageItem);
		    
		    Icon maxIcon = new ImageIcon("./images/max.png");
		    maxItem.setLabel("Wartoœæ maksymalna");
		    maxItem.setIcon(maxIcon);
		    maxItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.ALT_MASK));
		    maxItem.addActionListener(this);
		    calcMenu.add(maxItem);
		  
		    Icon minIcon = new ImageIcon("./images/min.png");
		    minItem.setLabel("Wartoœæ minimalna");
		    minItem.setIcon(minIcon);
		    minItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
		    minItem.addActionListener(this);
		    calcMenu.add(minItem);
		    
		    showMenuBarItem.setLabel("Ukryj pasek narzêdziowy");
		    showMenuBarItem.addActionListener(this);
		    viewMenu.add(showMenuBarItem);
		    
		    showStatusBarItem.setLabel("Ukryj pasek statusu");
		    showStatusBarItem.addActionListener(this);
		    viewMenu.add(showStatusBarItem);
		    
		    Icon helpIcon = new ImageIcon("./images/help_context.jpg");
		    helpItem.setLabel("Kontekst pomocy");
		    helpItem.setIcon(helpIcon);
		    helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));
		    helpItem.addActionListener(this);
		    helpMenu.add(helpItem);	
		    
		    Icon infoIcon = new ImageIcon("./images/about.jpg");
		    authorItem.setLabel("O programie");
		    authorItem.setIcon(infoIcon);
		    authorItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK));
		    authorItem.addActionListener(this);
		    helpMenu.add(authorItem);			    
		}
		catch(Exception e)
		{
			logger.error("Blad przy tworzeniu paska menu");
		}
	}

	/**
	 * Metoda CreateToolBarIcons() typu void, nie przyjmuj¹ca i nie zwracaj¹ca wartoœci 
	 * Tworzy ca³e menu wraz z jego elementami
	 */	
	public void CreateToolBarIcons()
	{	
		try
		{
		    Icon insertIcon = new ImageIcon("./images/add.png");
		    buttonToolBarInsert = new JButton(insertIcon);
		    buttonToolBarInsert.addActionListener(this);
		    buttonToolBarInsert.setToolTipText("Wype³nij");
		    toolBar.add(buttonToolBarInsert);
		    
		    Icon saveIcon = new ImageIcon("./images/save.png");
		    buttonToolBarSave = new JButton(saveIcon);
		    buttonToolBarSave.addActionListener(this);
		    buttonToolBarSave.setToolTipText("Zapisz");
		    toolBar.add(buttonToolBarSave);
		    
		    Icon exitIcon = new ImageIcon("./images/close.jpg");
		    buttonToolBarExit = new JButton(exitIcon);
		    buttonToolBarExit.addActionListener(this);
		    buttonToolBarExit.setToolTipText("Wyjœcie");
		    toolBar.add(buttonToolBarExit);
		    
		    toolBar.addSeparator();
		    
		    Icon addIcon = new ImageIcon("./images/add.png");
		    buttonToolBarAdd = new JButton(addIcon);
		    buttonToolBarAdd.addActionListener(this);
		    buttonToolBarAdd.setToolTipText("Dodaj wartoœæ");
		    toolBar.add(buttonToolBarAdd);
		    
		    Icon clearIcon = new ImageIcon("./images/reset.png");
		    buttonToolBarClear = new JButton(clearIcon);
		    buttonToolBarClear.addActionListener(this);
		    buttonToolBarClear.setToolTipText("Wyzeruj");
		    toolBar.add(buttonToolBarClear);
		    
		    toolBar.addSeparator();
		    
		    Icon sumIcon = new ImageIcon("./images/sum.png");
		    buttonToolBarSum=new JButton(sumIcon);
		    buttonToolBarSum.addActionListener(this);
		    buttonToolBarSum.setToolTipText("Suma elementów");
		    toolBar.add(buttonToolBarSum);
		    
			Icon averageIcon = new ImageIcon("./images/avg.png");
			buttonToolBarAverage=new JButton(averageIcon);
			buttonToolBarAverage.addActionListener(this);
			buttonToolBarAverage.setToolTipText("Œrednia wartoœci");
			toolBar.add(buttonToolBarAverage);
	
			Icon maxIcon = new ImageIcon("./images/max.png");
			buttonToolBarMax=new JButton(maxIcon);
			buttonToolBarMax.addActionListener(this);
			buttonToolBarMax.setToolTipText("Maksymalny element");
			toolBar.add(buttonToolBarMax);
			
			Icon minIcon = new ImageIcon("./images/min.png");
			buttonToolBarMin=new JButton(minIcon);
			buttonToolBarMin.addActionListener(this);
			buttonToolBarMin.setToolTipText("Minimalny element");
			toolBar.add(buttonToolBarMin);	    
		    
		    toolBar.addSeparator();
		    
		    Icon helpIcon = new ImageIcon("./images/help_context.jpg");
		    buttonToolBarHelp = new JButton(helpIcon);
		    buttonToolBarHelp.addActionListener(this);
		    buttonToolBarHelp.setToolTipText("Pomoc");
		    toolBar.add(buttonToolBarHelp);	  
		    
		    Icon aboutIcon = new ImageIcon("./images/about.jpg");
		    buttonToolBarAbout = new JButton(aboutIcon);
		    buttonToolBarAbout.addActionListener(this);
		    buttonToolBarAbout.setToolTipText("O programie");
		    toolBar.add(buttonToolBarAbout);
		    
		    toolBar.setFloatable(false);
		}
		catch(Exception e)
		{
			logger.error("Blad podczas tworzenia paska narzedziowego");
		}
	}

	/**
	 * Metoda CreatePanelCenter() typu void, nie przyjmuj¹ca i nie zwracaj¹ca wartoœci 
	 * Tworzy panel œrodkowy i dodaje go do ramki
	 */	
	public void CreatePanelCenter()
	{
		panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);
		contentPane.add(panelLeft, BorderLayout.WEST);
		panelCenter.setLayout(null);
	}

	/**
	 * Metoda panelLeft() typu void, nie przyjmuj¹ca i nie zwracaj¹ca wartoœci 
	 * Tworzy panel z lewej strony, zawieraj¹cy JTaskPane
	 */	
	public void panelLeft()
	{	
		try
		{
			String[] buttonName={
					"Wype³nij",
					"Wyczyœæ",
					"Zapisz",
					"Oblicz sumê",
					"Oblicz œredni¹",
					"Wyszukaj min",
					"Wyszukaj max",
					"O programie",
					"Kontekst pomocy"
			};
			//=======================================================================================>>>>>
			String[] buttonIcon={
					"add.png",
					"reset.png",
					"save.png",
					"sum.png",
					"avg.png",
					"min.png",
					"max.png",
					"about.jpg",
					"help_context.jpg"
			};
					
			JTaskPane taskPane=new JTaskPane();
			JTaskPaneGroup groupFile=new JTaskPaneGroup();
			JTaskPaneGroup groupCalc=new JTaskPaneGroup();
			JTaskPaneGroup groupHelp=new JTaskPaneGroup();
			
			groupFile.setTitle("Plik");
			groupCalc.setTitle("Obliczenia");
			groupHelp.setTitle("Pomoc");
	
			for(int i = 0; i < 3; i++)
			{
				Icon ico=new ImageIcon("./images/"+buttonIcon[i]);
			    buttons[i] = new JButton(buttonName[i], ico); 
			    buttons[i].addActionListener(this); 
			    buttons[i].setOpaque(false);
			    buttons[i].setContentAreaFilled(false);
			    buttons[i].setBorderPainted(false);
			    buttons[i].setHorizontalAlignment(SwingConstants.LEFT);
			    groupFile.add(buttons[i]);
			}	
			
			for(int i = 3; i < 7; i++)
			{
				Icon ico=new ImageIcon("./images/"+buttonIcon[i]);
			    buttons[i] = new JButton(buttonName[i], ico); 
			    buttons[i].addActionListener(this); 
			    buttons[i].setOpaque(false);
			    buttons[i].setContentAreaFilled(false);
			    buttons[i].setBorderPainted(false);
			    buttons[i].setHorizontalAlignment(SwingConstants.LEFT);
			    groupCalc.add(buttons[i]);
			}	
			
			for(int i = 7; i < 9; i++)
			{
				Icon ico=new ImageIcon("./images/"+buttonIcon[i]);
			    buttons[i] = new JButton(buttonName[i], ico); 
			    buttons[i].addActionListener(this); 
			    buttons[i].setOpaque(false);
			    buttons[i].setContentAreaFilled(false);
			    buttons[i].setBorderPainted(false);
			    buttons[i].setHorizontalAlignment(SwingConstants.LEFT);
			    groupHelp.add(buttons[i]);
			}
			
			taskPane.add(groupFile);
			taskPane.add(groupCalc);
			taskPane.add(groupHelp);
			
			panelLeft.add(taskPane);
			taskPane.setBackground(new Color(238,238,238));	
		}
		catch(Exception e)
		{
			logger.error("Blad podczas tworzenia panelu bocznego - taskpane");
		}
	}
	
	/**
	 * Metoda typu void, nie przyjmuj¹ca i nie zwracaj¹ca wartoœci 
	 * Definiuje wszystkie elementy, które nale¿¹ do tego panelu
	 */	
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	public void panelCenter()
	{
		try
		{
			/* ETYKIETA WprowadŸ liczbê */
			labelEnter = new JLabel("WprowadŸ liczbê:");
			labelEnter.setBounds(15, 15, 100,20);
			panelCenter.add(labelEnter);
			
			/* POLE WprowadŸ liczbê */
			fieldEnter = new JTextField();
			fieldEnter.setBounds(130, 15, 60,20);
			panelCenter.add(fieldEnter);
			
			/* ETYKIETA Numer wiersza */ 
			JLabel labelRow = new JLabel("Numer wiersza:");
			labelRow.setBounds(225, 15, 160,20);
			panelCenter.add(labelRow);
			
			/* POLE Numer wiersza */
			spinnerRow = new JSpinner();
			spinnerRow.setModel(new SpinnerNumberModel(1, 1, 5, 1));;
			spinnerRow.setBounds(330, 15, 45,20);
			panelCenter.add(spinnerRow);
			
			/* ETYKIETA Numer kolumny */ 
			JLabel labelCol = new JLabel("Numer kolumny:");
			labelCol.setBounds(420, 15, 160,20);
			panelCenter.add(labelCol);
			
			/* POLE Numer kolumny */
			spinnerCol = new JSpinner();
			spinnerCol.setModel(new SpinnerNumberModel(1, 1, 5, 1));
			spinnerCol.setBounds(530, 15, 45,20);
			panelCenter.add(spinnerCol);
			
			/* Tabela */
			table=new JTable();
		    
		    // Model TABELI o wymiarze 5x5
		    //tableModel = new DefaultTableModel(5,5); 
		    
		    // Etykiety nazw kolumn
		    //table.setColumnIdentifiers(columns);
			
			tableModel = new TableModel();
			table = new JTable(tableModel);
		    //table.setModel(tableModel);
		    table.getTableHeader().setReorderingAllowed(false);
		    table.setRowHeight(19);
		    table.setEnabled(false);
		    tableModel.getColumnName(0);
		   
		    rightAlign();
		    	    
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(0,45,440,118);
	
			panelCenter.add(scrollPane);
			
			/* Przycisk DODAJ */
			Icon addIconThumb = new ImageIcon("images/add.png");
			buttonAdd=new JButton("Dodaj", addIconThumb);
			buttonAdd.addActionListener(this);
			buttonAdd.setBounds(465, 45, 110,25);
			buttonAdd.setHorizontalAlignment(SwingConstants.LEFT);
			panelCenter.add(buttonAdd);
			
			/* Przycisk WYZERUJ */
			Icon clearIconThumb = new ImageIcon("images/reset.png");
			buttonClear=new JButton("Wyzeruj", clearIconThumb);
			buttonClear.addActionListener(this);
			buttonClear.setBounds(465, 75, 110,25);
			buttonClear.setHorizontalAlignment(SwingConstants.LEFT);
			panelCenter.add(buttonClear);
			
			/* Przycisk WYPELNIJ */
			Icon insertIconThumb = new ImageIcon("images/add.png");
			buttonInsert=new JButton("Wype³nij", insertIconThumb);
			buttonInsert.addActionListener(this);
			buttonInsert.setBounds(465, 105, 110,25);
			buttonInsert.setHorizontalAlignment(SwingConstants.LEFT);
			panelCenter.add(buttonInsert);
	
			/* Przycisk ZAPISZ */
			Icon saveIconThumb = new ImageIcon("images/save.png");
			buttonSave=new JButton("Zapisz", saveIconThumb);
			buttonSave.addActionListener(this);
			buttonSave.setBounds(465, 135, 110,25);
			buttonSave.setHorizontalAlignment(SwingConstants.LEFT);
			panelCenter.add(buttonSave);
	
			blackLine = BorderFactory.createLineBorder(Color.gray);
			borderTitle = BorderFactory.createTitledBorder(blackLine,"Wynik operacji");
			borderTitle.setTitleJustification(TitledBorder.LEFT);
			
			resultPanel = new JPanel();
			resultPanel.setBounds(0, 320, 575, 110);
			panelCenter.add(resultPanel);
			resultPanel.setLayout(null);
			//resultPanel.setEnabled(false);
			resultPanel.setBorder(borderTitle);
			resultPanel.setLayout(new BorderLayout());
			
			resultTextArea = new JTextArea();
			resultTextArea.setLineWrap(true);
			resultPanel.add(new JScrollPane(resultTextArea),BorderLayout.CENTER);
			
			labelOption=new JLabel();
			labelOption.setText("Wybierz opcjê:");
			labelOption.setBounds(0, 170, 120, 20);
			
			// Definicja komponentu JList
			JList lista=new JList();
			String[] selections = { "Suma elementów", "Œrednia elementów", "Wartoœæ max i min" };
			list = new JList(selections);
			list.setBounds(120, 170, 120,55);
			list.setBorder(blackLine);
			panelCenter.add(list);
			panelCenter.add(labelOption);
			
			showCalendar();
									
			Icon calculateIconThumb = new ImageIcon("images/thumb/execute.png");
			buttonCalculate=new JButton("Oblicz", calculateIconThumb);
			buttonCalculate.addActionListener(this);
			buttonCalculate.setBounds(260,170,100,55);
			panelCenter.add(buttonCalculate);	
		}
		catch(Exception e)
		{
			logger.error("Blad podczas tworzenia panelu centralnego");
		}		
	}
	
	public void showCalendar()
	{
		DateListener listen = new DateListener() 
		{
			public void dateChanged(DateEvent e) 
			{
				Calendar c = e.getSelectedDate();
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				String formatted = format1.format(c.getTime());
				resultTextArea.append("Wybrano datê:" + formatted + "\n");				
			}
		};
		calendar=new JCalendarCombo();
		calendar.setBounds(0, 240, 575, 60);
		calendar.addDateListener(listen);
		calendar.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		panelCenter.add(calendar);
	}

	/**
	 * Metoda typu void, nie przyjmuj¹ca i nie zwracaj¹ca wartoœci 
	 * Ustawia zawartoœæ komórek w kolumnie do prawej strony.
	 */	
	public void rightAlign() 
	{
	    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
	    rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
	    
	    for(int i=0;i<5;i++) table.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);		
	}

	/**
	 * Metoda typu void, nie przyjmuj¹ca i nie zwracaj¹ca wartoœci 
	 * Tworzy dolny panel wraz z elementami
	 */		
	public void BottomPanel()
	{
		try
		{
			panelBottom=new JPanel();
			contentPane.add(panelBottom, BorderLayout.SOUTH);
			panelBottom.setBackground(new Color(210,210,210));
			panelBottom.setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
				
			labelInfo=new JLabel("Info");
			labelInfo.setPreferredSize(new Dimension(25,20));
			panelBottom.add(labelInfo);
			
			fieldInfo=new JTextField(24);
			fieldInfo.setEditable(false);
			panelBottom.add(fieldInfo);
			
			JLabel labelSpacer=new JLabel();
			labelSpacer.setPreferredSize(new Dimension(270,20));
			panelBottom.add(labelSpacer);		
			
			labelStatus=new JLabel("Status");
			labelStatus.setPreferredSize(new Dimension(55,20));
			labelStatus.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
			panelBottom.add(labelStatus);
			
			fieldStatus=new JTextField(10);
			fieldStatus.setEditable(false);
			panelBottom.add(fieldStatus);
		}
		catch(Exception e)
		{
			logger.error("Blad podczas tworzenia panelu dolnego - statusbar");
		}
	}	
	
	/**
	 * Metoda typu void, nie przyjmuj¹ca i nie zwracaj¹ca wartoœci 
	 * Ustawia wartoœci do pola tekstowego Status
	 */	
	public void setStatus()
	{
		fieldStatus.setText("W³¹czony");
	}

	/**
	 * Metoda typu void, nie przyjmuj¹ca i nie zwracaj¹ca wartoœci 
	 * Znajduje w obszarze ca³ej tablicy minimaln¹ wartoœæ. Dopisuje do obszaru tekstowego informacj¹ o wyniku oraz typ operacji do pola Info
	 */	
	public void FindMinValue()
	{
		float min=tableModel.findMin();
		resultTextArea.append("Wartoœæ minimum: " + min + "\r\n");
		fieldInfo.setText("Obliczono wartoœæ minimum");
	}

	/**
	 * Metoda typu void, nie przyjmuj¹ca i nie zwracaj¹ca wartoœci 
	 * Znajduje w obszarze ca³ej tablicy maksymaln¹ wartoœæ. Dopisuje do obszaru tekstowego informacj¹ o wyniku oraz typ operacji do pola Info
	 */	
	public void FindMaxValue()
	{
		float max=tableModel.findMax();
		resultTextArea.append("Wartoœæ maksimum: " + max + "\r\n");		
		fieldInfo.setText("Obliczono wartoœæ maximum");
	}
	
	/**
	 * Metoda typu void, przyjmuje argument obiekt klasy ActionEvent
	 * Obsuguje nas³uchwanie zdarzenia.
	 * @param evt bêd¹ca obiektem klasy ActionEvent
	 */	
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent evt) 
	{
		Object listener=evt.getSource();

		/* WYPELNIJ */
		if(listener == this.buttonInsert || listener == this.buttonToolBarInsert || listener == this.openItem || listener == this.buttons[0])
		{
			  tableModel.randInt();
	          fieldInfo.setText("Wype³niono tabelê");
		}		
		
		/* WYCZYŒÆ */
		if(listener == this.buttonClear || listener == this.buttonToolBarClear || listener ==this.clearItem || listener == this.buttons[1])
		{
			  tableModel.emptyTable();
	          fieldInfo.setText("Wyczyszczono tabelê");
	          resultTextArea.setText("");
		}
		
		/* ZAPISZ */
		if(listener == this.buttonSave || listener == this.saveItem || listener == this.buttonToolBarSave || listener == this.buttons[2])
		{
			//int option = saveFile.showOpenDialog(MainApplication.this);
			int messageType = JOptionPane.INFORMATION_MESSAGE;
			
			// Jeœli zosta³‚ wybrany jakiœ element z okna dialogowego oraz wciœniêty przycisk potwierdzaj¹cy
			//if(option == JFileChooser.APPROVE_OPTION) 
			//{
                File temp = new File("tabela.txt");
                
                String fileName = temp.getName();
                
                // Obs³uga w¹tku przy zapisywaniu do pliku z kategori¹ walidacji operacji wejœcia/wyjœcia
                try 
                {
                	// Tworzony jest obiekt klasy PrintWriter, jako parametr konstruktora podajemy nazwê pliku - temp
                	PrintWriter os = new PrintWriter(temp);
                	
                	for(int row=0;row<tableModel.getRowCount();row++)
                		for(int col=0;col<tableModel.getColumnCount();col++)
                		{
                			os.print(table.getValueAt(row, col) + " ");
                		}                                       
                	os.close();
                		
                    JOptionPane.showMessageDialog(null, "Plik " + fileName + " zosta³‚ zapisany!" ,"Zapisano plik", messageType);
                    fieldInfo.setText("Zapisano tabelê do pliku");
                }           
                catch (IOException ex)
                {
                	System.out.println("Wyst¹pi³ b³¹d " + ex);
                } 
			//}
		}
		
		/* POKAZ/UKRYJ PASEK NARZEDZIOWY */
		if(listener == this.showMenuBarItem)
		{
			boolean showToolBar = showMenuBarItem.getState();
			
			if(showToolBar) toolBar.setVisible(false);
			else toolBar.setVisible(true);			
		}
		
		/* POKAZ/UKRYJ PASEK STATUSU */
		if(listener == this.showStatusBarItem)
		{
			boolean showStatusBar = showStatusBarItem.getState();
			
			if(showStatusBar) panelBottom.setVisible(false);
			else panelBottom.setVisible(true);		
		}
		
		/* PRZYCISK OBLICZ */
		if(listener == this.buttonCalculate)
		{
			int index = list.getSelectedIndex();
			
			if(index==0) { resultTextArea.append("Suma wartoœci: "+ tableModel.calculateSum() + "\r\n"); fieldInfo.setText("Obliczono sumê wartoœci"); }
			if(index==1) { resultTextArea.append("Œrednia wartoœci: "+ tableModel.calculateAverage() + "\r\n"); fieldInfo.setText("Obliczono œredni¹ wartoœci"); }
			if(index==2) 
			{
				FindMaxValue();
				FindMinValue(); 
				fieldInfo.setText("Obliczono wartoœci max i min");
			}
		}
		
		/* WARTOSC MAXIMUM */
		if(listener == this.buttonToolBarMax || listener == this.maxItem || listener == this.buttons[6])
		{
			FindMaxValue();
		}
		
		/* WARTOSC MINIMUM */
		if(listener == this.buttonToolBarMin || listener == this.minItem || listener == this.buttons[5])
		{
			FindMinValue();
		}
		
		/* SUMA WARTOSCI */
		if(listener == this.buttonToolBarSum || listener == this.sumItem || listener == this.buttons[3])
		{
			System.out.println(tableModel.calculateSum());
			resultTextArea.append("Suma wartoœci: " + tableModel.calculateSum() + "\r\n");		
			fieldInfo.setText("Obliczono sumê wartoœci");
		}
		
		/* SREDNIA WARTOSCI */
		if(listener == this.buttonToolBarAverage || listener == this.averageItem || listener == this.buttons[4])
		{
			resultTextArea.append("Œrednia wartoœci: " + tableModel.calculateAverage() + "\r\n");	
			fieldInfo.setText("Obliczono œredni¹ wartoœci");
		}
		
		/* PRZYCISK DODAJ */
		if(listener == this.buttonAdd || listener == this.buttonToolBarAdd)
		{	
			// Obs³uga wyj¹tków z walidacj¹ kategorii typ liczbowy
			try 
			{		
				int value=Integer.parseInt(fieldEnter.getText().toString());
				int row=(Integer)spinnerRow.getValue();
				int col=(Integer)spinnerCol.getValue();
				
				tableModel.setValue(value, row-1, col-1);
				resultTextArea.append("Dodano wartoœæ " + value + " do wiersza " + row + " i kolumny " + col + "\r\n");
				fieldInfo.setText("Dodano wartoœæ do tabeli");
				
				fieldEnter.setText("");
				spinnerRow.setValue(1);
				spinnerCol.setValue(1);
			}
	        catch (NumberFormatException ex)
	        {
	            System.err.println("Wyst¹pi³ b³¹d " + ex);
	            logger.warn("Wprowadzono niepoprawna wartosc - wartosc do wpisania w tabele");
	            JOptionPane.showMessageDialog(null, "Nie wprowadzono wartoœci lub wartoœæ nie jest liczb¹" ,"B³¹d wprowadzonej wartoœci", JOptionPane.ERROR_MESSAGE);
	            fieldEnter.setText("");
	        }
		}		
		
		/* KONTEKST POMOCY */
		if(listener == this.buttonToolBarHelp || listener == this.helpItem || listener == this.buttons[8])
		{
			HelpContent cp=new HelpContent();
		}
		
		/* O PROGRAMIE */
		if(listener == this.authorItem || listener == this.buttonToolBarAbout || listener == this.buttons[7])
		{
			AboutWindow about=new AboutWindow();
		}
		
		/* WYJSCIE */
		if(listener == this.exitItem || listener == this.buttonToolBarExit)
		{
			// T³umaczenia etykiet przycisków
			UIManager.put("OptionPane.yesButtonText", "Tak");
			UIManager.put("OptionPane.noButtonText", "Nie");
			
		    int confirmed = JOptionPane.showConfirmDialog(null, "Jesteœ pewien, ¿e chcesz wyjœæ z programu?", "Wyjœcie z programu",JOptionPane.YES_NO_OPTION);

		    if (confirmed == JOptionPane.YES_OPTION) 
		    {
		    	dispose();
		    }
		}
	}
		
	
	public static void main(String[] args) 
	{
		MainApplication app=new MainApplication();
		app.setVisible(true);
		
	}
}


