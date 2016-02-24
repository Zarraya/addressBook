import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.awt.Font;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

/**
 * 
 * @author javeney
 * Modified 2/14/16 did stuff.
 * Modified 2/15/16 organized previous stuff
 *
 */

public class MainWindow {

	//AddressBook object
	static AddressBook ab;
	private JFrame mainFrame;
	private JPanel panelSort;
	private JTextField txtSearch;
	private JPanel viewPanel;
	//text fields
	private JTextField txtFirst;
	private JTextField txtMiddle;
	private JTextField txtLast;
	private JTextField txtStreet;
	private JTextField txtApt;
	private JTextField txtCity;
	private JTextField txtZip;
	private JTextField txtEmail;
	private JTextField txtCompany;
	private JTextField txtPhone1Type;
	private JTextField txtPhone2Type;
	private JTextField txtState;
	private JTextField txtPhone2;
	private JTextField txtPhone1;
	//scrolling text area
	private JTextArea txtNotes;
	//instance variables
	private String first;
	private String middle;
	private String last;
	private String street;
	private String apt;
	private String city;
	private String zip;
	private String email;
	private String company;
	private String state;
	private String phone1;
	private String phone1Type;
	private String phone2;
	private String phone2Type;
	private String notes;
	private String image;
	
	private String keyword;
	private int srchCommand;
	private int sortType;
	private int sortCommand;
	private Entry entry;
	
	/**
	 * Create the application.
	 * @throws ParseException 
	 */
	public MainWindow(AddressBook ab) throws ParseException {
		initialize();
		ab = new AddressBook();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ParseException 
	 */
	public void initialize() throws ParseException {
		mainFrame = new JFrame();
		mainFrame.setResizable(false);
		mainFrame.setTitle("Address Book");
		mainFrame.setBounds(100, 100, 711, 650);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setLocationRelativeTo(null);
		
		viewPanel = new JPanel();
		viewPanel.setLocation(344, 25);
		viewPanel.setSize(351, 596);
		mainFrame.getContentPane().add(viewPanel);
		viewPanel.setLayout(null);
		
		initializeImage(image);
		initializeName(first, middle, last);
		initializePhone(phone1, phone2, phone1Type, phone2Type);
		initializeAddress(street, apt, city, state, zip);
		initializeEmail(email);
		initializeComapny(company);
		initializeNotes(notes);
		initializeButtons();
		initializeToolbar();
		initializeSidebar();
		initializeSearch();
		initializeSort();
		
		mainFrame.setVisible(true);
	}
	
	private void initializeImage(String image){
		JPanel imagePanel = new JPanel();
		imagePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		imagePanel.setBounds(10, 11, 135, 150);
		viewPanel.add(imagePanel);
		
		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(image));
		imagePanel.add(lblImage);
	}
	
	private void initializeName(String first, String middle, String last){
		txtFirst = new JTextField(first);
		txtFirst.setEditable(false);
		txtFirst.setBounds(155, 29, 187, 20);
		txtFirst.setToolTipText("Enter first name here.");
		txtFirst.setColumns(10);
		viewPanel.add(txtFirst);
		
		txtMiddle = new JTextField(middle);
		txtMiddle.setEditable(false);
		txtMiddle.setBounds(155, 69, 187, 20);
		txtMiddle.setColumns(10);
		viewPanel.add(txtMiddle);
		
		txtLast = new JTextField(last);
		txtLast.setEditable(false);
		txtLast.setBounds(155, 109, 187, 20);
		txtLast.setColumns(10);
		viewPanel.add(txtLast);
		
		JLabel lblFirst = new JLabel("First Name");
		lblFirst.setBounds(155, 49, 84, 14);
		viewPanel.add(lblFirst);
		
		JLabel lblMiddle = new JLabel("Middle Name");
		lblMiddle.setBounds(155, 89, 84, 14);
		viewPanel.add(lblMiddle);
		
		JLabel lblLast = new JLabel("Last Name");
		lblLast.setBounds(155, 129, 84, 14);
		viewPanel.add(lblLast);
	}
	
	private void initializePhone(String phone1, String phone2, String phone1Type, String phone2Type){
		txtPhone1 = new JTextField(phone1);
		txtPhone1.setEditable(false);
		txtPhone1.setBounds(10, 172, 111, 20);
		viewPanel.add(txtPhone1);
		
		txtPhone2 = new JTextField(phone2);
		txtPhone2.setEditable(false);
		txtPhone2.setBounds(10, 211, 111, 20);
		viewPanel.add(txtPhone2);
	
		txtPhone1Type = new JTextField(phone1Type);
		txtPhone1Type.setEditable(false);
		txtPhone1Type.setBounds(131, 172, 77, 20);
		viewPanel.add(txtPhone1Type);
		
		txtPhone2Type = new JTextField(phone2Type);
		txtPhone2Type.setEditable(false);
		txtPhone2Type.setBounds(131, 211, 77, 20);
		viewPanel.add(txtPhone2Type);
		
		JLabel lblPhone1 = new JLabel("Primary phone");
		lblPhone1.setBounds(10, 191, 111, 14);
		viewPanel.add(lblPhone1);
		
		JLabel lblPhone2 = new JLabel("Secondary phone");
		lblPhone2.setBounds(10, 231, 111, 14);
		viewPanel.add(lblPhone2);
	}
	
	private void initializeAddress(String street, String apt, String city, String state, String zip){
		txtStreet = new JTextField(street);
		txtStreet.setEditable(false);
		txtStreet.setBounds(10, 256, 258, 20);
		txtStreet.setColumns(10);
		viewPanel.add(txtStreet);
		
		txtApt = new JTextField(apt);
		txtApt.setEditable(false);
		txtApt.setBounds(10, 296, 258, 20);
		txtApt.setColumns(10);
		viewPanel.add(txtApt);
		
		txtCity = new JTextField(city);
		txtCity.setEditable(false);
		txtCity.setBounds(10, 336, 135, 20);
		txtCity.setColumns(10);
		viewPanel.add(txtCity);
		
		txtState = new JTextField(state);
		txtState.setEditable(false);
		txtState.setBounds(156, 336, 42, 20);
		viewPanel.add(txtState);
		
		txtZip = new JTextField(zip);
		txtZip.setEditable(false);
		txtZip.setBounds(208, 336, 60, 20);
		txtZip.setColumns(10);
		viewPanel.add(txtZip);
		
		JLabel lblStreet = new JLabel("Street Address");
		lblStreet.setBounds(10, 276, 135, 14);
		viewPanel.add(lblStreet);
		
		JLabel lblApt = new JLabel("Apt, Suite, Rm");
		lblApt.setBounds(10, 316, 135, 14);
		viewPanel.add(lblApt);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(10, 356, 84, 14);
		viewPanel.add(lblCity);
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(156, 356, 42, 14);
		viewPanel.add(lblState);
		
		JLabel lblZip = new JLabel("Zip code");
		lblZip.setBounds(208, 356, 60, 14);
		viewPanel.add(lblZip);
	}
	
	private void initializeEmail(String email){
		txtEmail = new JTextField(email);
		txtEmail.setEditable(false);
		txtEmail.setBounds(10, 384, 258, 20);
		txtEmail.setColumns(10);
		viewPanel.add(txtEmail);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 404, 84, 14);
		viewPanel.add(lblEmail);
	}
	
	private void initializeComapny(String company){
		txtCompany = new JTextField(company);
		txtCompany.setEditable(false);
		txtCompany.setBounds(10, 424, 258, 20);
		txtCompany.setColumns(10);
		viewPanel.add(txtCompany);
		
		JLabel lblCompany = new JLabel("Company");
		lblCompany.setBounds(10, 444, 84, 14);
		viewPanel.add(lblCompany);
	}
	
	private void initializeNotes(String notes){
		JScrollPane scrollNotes = new JScrollPane();
		scrollNotes.setBounds(10, 465, 309, 47);
		viewPanel.add(scrollNotes);
		
		txtNotes = new JTextArea(notes);
		txtNotes.setEditable(false);
		scrollNotes.setViewportView(txtNotes);
		txtNotes.setWrapStyleWord(true);
		txtNotes.setLineWrap(true);
		txtNotes.setRows(5);
	}
	
	private void initializeButtons(){
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					EditContactWindow edit = new EditContactWindow(ab, entry);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnEdit.setBounds(131, 523, 89, 30);
		viewPanel.add(btnEdit);
		
		/*
		 * TODO BE COMPLETED
		 * delete command
		 * update view pane
		 * update sidebar
		 */
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this contact?", "WARNING",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				    JOptionPane.showMessageDialog(null, "Contact deleted.");
				    //refresh pane.
				} else {
				    // no option
				}
			}
		});
		btnDelete.setBounds(230, 523, 89, 30);
		viewPanel.add(btnDelete);
		
		/*
		 * TODO BE COMPLETED:
		 * add file path
		 * print to pdf command
		 */
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showSaveDialog(viewPanel);
				
				if(returnVal == JFileChooser.APPROVE_OPTION){
					try {
						FileWriter fw = new FileWriter(chooser.getSelectedFile()+".txt");
						fw.write(first+ middle+ last);
						fw.write(phone1Type+ phone1);
						fw.write(phone2Type+ phone2);
						fw.write(street);
						fw.write(apt);
						fw.write(city+ state+ zip);
						fw.write(email);
						fw.write(company);
						fw.write(notes);		
						fw.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
		        }
				String savePath = chooser.getSelectedFile().getPath();
		        JOptionPane.showMessageDialog(viewPanel, "Contact saved to: " + savePath);
			}
		});
		btnPrint.setBounds(32, 523, 89, 30);
		viewPanel.add(btnPrint);
	}
	
	private void initializeSearch(){
		JPanel panelSearch = new JPanel();
		panelSearch.setBounds(5, 530, 335, 91);
		mainFrame.getContentPane().add(panelSearch);
		panelSearch.setLayout(null);
		
		ButtonGroup search = new ButtonGroup();
		
		JRadioButton rdbtnName = new JRadioButton("Name");
		rdbtnName.setBounds(20, 27, 85, 23);
		rdbtnName.setSelected(true);
		search.add(rdbtnName);
		panelSearch.add(rdbtnName);
		
		JRadioButton rdbtnCompany = new JRadioButton("Company");
		rdbtnCompany.setBounds(119, 27, 90, 23);
		search.add(rdbtnCompany);
		panelSearch.add(rdbtnCompany);
		
		JRadioButton rdbtnZipCode = new JRadioButton("Zip code");
		rdbtnZipCode.setBounds(224, 53, 87, 23);
		search.add(rdbtnZipCode);
		panelSearch.add(rdbtnZipCode);
		
		JRadioButton rdbtnPhone = new JRadioButton("Phone");
		rdbtnPhone.setBounds(20, 53, 85, 23);
		search.add(rdbtnPhone);
		panelSearch.add(rdbtnPhone);
		
		JRadioButton rdbtnCity = new JRadioButton("City");
		rdbtnCity.setBounds(224, 27, 87, 23);
		search.add(rdbtnCity);
		panelSearch.add(rdbtnCity);
		
		JRadioButton rdbtnState = new JRadioButton("State");
		rdbtnState.setBounds(119, 53, 90, 23);
		search.add(rdbtnState);
		panelSearch.add(rdbtnState);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(0, 0, 249, 23);
		panelSearch.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				keyword = txtSearch.getText();
				if(rdbtnName.isSelected()){
					srchCommand = 0;
				}else if(rdbtnPhone.isSelected()){
					srchCommand = 1;
				}else if(rdbtnCompany.isSelected()){
					srchCommand = 2;
				}else if(rdbtnState.isSelected()){
					srchCommand = 3;
				}else if(rdbtnCity.isSelected()){
					srchCommand = 4;
				}else if(rdbtnZipCode.isSelected()){
					srchCommand = 5;
				}
				
				ab.search(keyword, srchCommand);
			}
		});
		btnNewButton.setBounds(248, 0, 87, 23);
		panelSearch.add(btnNewButton);
	}
	
	private void initializeSort(){
		panelSort = new JPanel();
		panelSort.setBounds(5, 25, 335, 30);
		mainFrame.getContentPane().add(panelSort);
		panelSort.setLayout(null);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Last Name", "First Name", "Zipcode"}));
		comboBox.setBounds(158, 0, 98, 28);
		panelSort.add(comboBox);
		
		ButtonGroup sort = new ButtonGroup();
		
		JRadioButton rdbtnAscending = new JRadioButton("Ascend");
		rdbtnAscending.setBounds(6, 3, 74, 23);
		rdbtnAscending.setSelected(true);
		sort.add(rdbtnAscending);
		panelSort.add(rdbtnAscending);
		
		JRadioButton rdbtnDescending = new JRadioButton("Descend");
		rdbtnDescending.setBounds(80, 3, 79, 23);
		panelSort.add(rdbtnDescending);
		sort.add(rdbtnDescending);
		
		JButton btnSort = new JButton("Sort");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnAscending.isSelected()){
					sortType = 0;
				}else if(rdbtnDescending.isSelected()){
					sortType = 1;
				}
				
				if(comboBox.getSelectedItem().equals("Last Name")){
					sortCommand = 1;
				}else if(comboBox.getSelectedItem().equals("First Name")){
					sortCommand = 0;
				}else if(comboBox.getSelectedItem().equals("Zipcode")){
					sortCommand = 2;
				}
				
				ab.sort(sortType, sortCommand);
			}
		});
		btnSort.setBounds(254, 0, 81, 28);
		panelSort.add(btnSort);
	}
	
	private void initializeSidebar(){
		JScrollPane scrollSidebar = new JScrollPane();
		scrollSidebar.setBounds(5, 55, 335, 476);
		mainFrame.getContentPane().add(scrollSidebar);
		
		JPanel panelSidebar = new JPanel();
		scrollSidebar.setViewportView(panelSidebar);
		panelSidebar.setLayout(new BorderLayout(0, 0));
	}
	
	private void initializeToolbar(){
		JToolBar toolBar = new JToolBar();
		toolBar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toolBar.setBounds(5, 5, 716, 20);
		mainFrame.getContentPane().add(toolBar);
		
		JButton btnCreateANew = new JButton("New Contact");
		btnCreateANew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					NewContactWindow contact = new NewContactWindow(ab);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		toolBar.add(btnCreateANew);
	}
}
