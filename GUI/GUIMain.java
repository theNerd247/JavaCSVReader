import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUIMain
{
	JFrame window;
	Container windowContent;

	public GUIMain()
	{
		window = new JFrame("FRC CSV Reader Interface");
		windowContent = window.getContentPane();
	
		initMenu();
		initComponents();
		addClosingOperation();
		window.setSize(600,450);
		window.setVisible(true);
	}

	//create custom default closing op
	private void addClosingOperation()
	{
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				int opt = GUIManager.exit();
				if(opt > 0) System.exit(0);
			}
		});
	}

	//initialize gui components
	public void initComponents()
	{
		window.add(GUIManager.getGUIItems());
	}
	//initialize gui menu 
	public void initMenu()
	{
		//create the menubar
		JMenuBar menuBar = new JMenuBar();
			//fileMenu
			JMenu fileMenu = new JMenu("File");
				JMenuItem open = new JMenuItem("open");
					open.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							GUIManager.open();
						}
					});
				fileMenu.add(open);
				JMenuItem close = new JMenuItem("close");
					close.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							GUIManager.close();
						}
					});
				fileMenu.add(close);
				JMenuItem newFile = new JMenuItem("new");
					newFile.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							GUIManager.newFile();
						}
					});
				fileMenu.add(newFile);
				JMenuItem save = new JMenuItem("save");
					save.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							GUIManager.save();
						}
					});
				fileMenu.add(save);
				JMenuItem saveCopy = new JMenuItem("save a copy");
					saveCopy.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							GUIManager.saveCopy();
						}
					});
				fileMenu.add(saveCopy);
				JMenuItem saveAs = new JMenuItem("saveAs");
					saveAs.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							GUIManager.saveAs();
						}
					});
				fileMenu.add(saveAs);
				JMenuItem exit = new JMenuItem("exit");
					exit.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							GUIManager.exit(true);
						}
					});
				fileMenu.add(exit);
			menuBar.add(fileMenu);
			//frcMenu
			JMenu frcMenu = new JMenu("FRC");
				JMenuItem newRobotSheet = new JMenuItem("New Roobt Sheet");
					newRobotSheet.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							GUIManager.newRobotSheet();
						} 
					});
				frcMenu.add(newRobotSheet);
			menuBar.add(frcMenu);
			JMenu helpMenu = new JMenu("Help");
				JMenuItem about = new JMenuItem("about");
					about.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							GUIManager.showAbout();
						}
					});
				helpMenu.add(about);
				JMenuItem manual = new JMenuItem("manual");
					manual.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							GUIManager.showHelp();
						}
					});
				helpMenu.add(manual);
			menuBar.add(helpMenu);
		window.setJMenuBar(menuBar);
	}
}	

