import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIMain
{
	JFrame window;
	Container windowContent;

	public GUIMain()
	{
		window = new JFrame("FRC CSV Reader Interface");
		windowContent = window.getContentPane();
	
		initComponents();
		window.setDefaultCloseOperation(3);
		window.setSize(600,450);
		window.setVisible(true);
	}

	//initialize gui components
	public void initComponents()
	{
		//create the menubar
		JMenuBar menuBar = new JMenuBar();
			//fileMenu
			JMenu fileMenu = new JMenu("File");
				JMenuItem open = new JMenuItem("open");
					open.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							System.out.println("Opening new File");
							GUIManager.open();//later add method call to open a new file
						}
					});
				fileMenu.add(open);
				JMenuItem close = new JMenuItem("close");
					close.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							System.out.println("closing file");
							GUIManager.close();//later add method call to open a new file
						}
					});
				fileMenu.add(close);
				JMenuItem newFile = new JMenuItem("new");
					newFile.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							System.out.println("creating new file");
							GUIManager.newFile();//later add method call to open a new file
						}
					});
				fileMenu.add(newFile);
				JMenuItem save = new JMenuItem("save");
					save.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							System.out.println("saving file");
							GUIManager.save();//later add method call to open a new file
						}
					});
				fileMenu.add(save);
				JMenuItem saveCopy = new JMenuItem("save a copy");
					saveCopy.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							System.out.println("saving file copy");
							GUIManager.save();//later add method call to open a new file
						}
					});
				fileMenu.add(saveCopy);
				JMenuItem saveAs = new JMenuItem("saveAs");
					saveAs.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							System.out.println("saving as file");
							GUIManager.saveAs();//later add method call to open a new file
						}
					});
				fileMenu.add(saveAs);
				JMenuItem exit = new JMenuItem("exit");
					exit.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							System.out.println("exiting program");
							GUIManager.exit();//later add method call to open a new file
						}
					});
				fileMenu.add(exit);
			menuBar.add(fileMenu);
			//frcMenu
			JMenu frcMenu = new JMenu("FRC");
				JMenuItem newRobotSheet = new JMenuItem("New Roobt Sheet");
					newRobotSheet.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							System.out.println("create new robot sheet");
							GUIManager.newRobotSheet();
						} 
					});
				frcMenu.add(newRobotSheet);
			menuBar.add(frcMenu);
			JMenu helpMenu = new JMenu("Help");
				JMenuItem about = new JMenuItem("about");
					about.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							System.out.println("display about menu");
							GUIManager.showAbout();//later add method call to open a new file
						}
					});
				helpMenu.add(about);
				JMenuItem manual = new JMenuItem("manual");
					manual.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							System.out.println("opening help menu");
							GUIManager.showHelp();//later add method call to open a new file
						}
					});
				helpMenu.add(manual);
			menuBar.add(helpMenu);
		window.setJMenuBar(menuBar);
	}
}	

