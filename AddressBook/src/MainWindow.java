import javax.swing.*;
import java.awt.Font;
import java.text.ParseException;
import java.util.ArrayList;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

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

	private String keyword;
	private int sortType;
	private int sortCommand;

	/**
	 * Create the application.
	 * @throws ParseException
	 */
	public MainWindow(AddressBook ab) throws ParseException {
		MainWindow.ab = ab;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ParseException
	 */
	public void initialize() throws ParseException {
		mainFrame = new JFrame();
		mainFrame.setResizable(false);
		mainFrame.setTitle("Address Book");
		mainFrame.setBounds(100, 100, 711, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setLocationRelativeTo(null);

		viewPanel = new JPanel();
		viewPanel.setLocation(344, 25);
		viewPanel.setSize(351, 535);
		mainFrame.getContentPane().add(viewPanel);
		viewPanel.setLayout(null);

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

	private void initializeName(String first, String middle, String last){
		txtFirst = new JTextField(first);
		txtFirst.setEditable(false);
		txtFirst.setBounds(10, 11, 187, 20);
		txtFirst.setColumns(10);
		viewPanel.add(txtFirst);

		txtMiddle = new JTextField(middle);
		txtMiddle.setEditable(false);
		txtMiddle.setBounds(10, 51, 187, 20);
		txtMiddle.setColumns(10);
		viewPanel.add(txtMiddle);

		txtLast = new JTextField(last);
		txtLast.setEditable(false);
		txtLast.setBounds(10, 91, 187, 20);
		txtLast.setColumns(10);
		viewPanel.add(txtLast);

		JLabel lblFirst = new JLabel("First Name");
		lblFirst.setBounds(10, 31, 84, 14);
		viewPanel.add(lblFirst);

		JLabel lblMiddle = new JLabel("Middle Name");
		lblMiddle.setBounds(10, 71, 84, 14);
		viewPanel.add(lblMiddle);

		JLabel lblLast = new JLabel("Last Name");
		lblLast.setBounds(10, 111, 84, 14);
		viewPanel.add(lblLast);
	}

	private void initializePhone(String phone1, String phone2, String phone1Type, String phone2Type){
		txtPhone1 = new JTextField(phone1);
		txtPhone1.setEditable(false);
		txtPhone1.setBounds(10, 136, 111, 20);
		viewPanel.add(txtPhone1);

		txtPhone2 = new JTextField(phone2);
		txtPhone2.setEditable(false);
		txtPhone2.setBounds(10, 175, 111, 20);
		viewPanel.add(txtPhone2);

		txtPhone1Type = new JTextField(phone1Type);
		txtPhone1Type.setEditable(false);
		txtPhone1Type.setBounds(131, 136, 77, 20);
		viewPanel.add(txtPhone1Type);

		txtPhone2Type = new JTextField(phone2Type);
		txtPhone2Type.setEditable(false);
		txtPhone2Type.setBounds(131, 175, 77, 20);
		viewPanel.add(txtPhone2Type);

		JLabel lblPhone1 = new JLabel("Primary phone");
		lblPhone1.setBounds(10, 155, 111, 14);
		viewPanel.add(lblPhone1);

		JLabel lblPhone2 = new JLabel("Secondary phone");
		lblPhone2.setBounds(10, 195, 111, 14);
		viewPanel.add(lblPhone2);
	}

	private void initializeAddress(String street, String apt, String city, String state, String zip){
		txtStreet = new JTextField(street);
		txtStreet.setEditable(false);
		txtStreet.setBounds(10, 220, 258, 20);
		txtStreet.setColumns(10);
		viewPanel.add(txtStreet);

		txtApt = new JTextField(apt);
		txtApt.setEditable(false);
		txtApt.setBounds(10, 260, 258, 20);
		txtApt.setColumns(10);
		viewPanel.add(txtApt);

		txtCity = new JTextField(city);
		txtCity.setEditable(false);
		txtCity.setBounds(10, 300, 135, 20);
		txtCity.setColumns(10);
		viewPanel.add(txtCity);

		txtState = new JTextField(state);
		txtState.setEditable(false);
		txtState.setBounds(156, 300, 42, 20);
		viewPanel.add(txtState);

		txtZip = new JTextField(zip);
		txtZip.setEditable(false);
		txtZip.setBounds(208, 300, 60, 20);
		txtZip.setColumns(10);
		viewPanel.add(txtZip);

		JLabel lblStreet = new JLabel("Street Address");
		lblStreet.setBounds(10, 240, 135, 14);
		viewPanel.add(lblStreet);

		JLabel lblApt = new JLabel("Apt, Suite, Rm");
		lblApt.setBounds(10, 280, 135, 14);
		viewPanel.add(lblApt);

		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(10, 320, 84, 14);
		viewPanel.add(lblCity);

		JLabel lblState = new JLabel("State");
		lblState.setBounds(156, 320, 42, 14);
		viewPanel.add(lblState);

		JLabel lblZip = new JLabel("Zip code");
		lblZip.setBounds(208, 320, 60, 14);
		viewPanel.add(lblZip);
	}

	private void initializeEmail(String email){
		txtEmail = new JTextField(email);
		txtEmail.setEditable(false);
		txtEmail.setBounds(10, 348, 258, 20);
		txtEmail.setColumns(10);
		viewPanel.add(txtEmail);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 368, 84, 14);
		viewPanel.add(lblEmail);
	}

	private void initializeComapny(String company){
		txtCompany = new JTextField(company);
		txtCompany.setEditable(false);
		txtCompany.setBounds(10, 388, 258, 20);
		txtCompany.setColumns(10);
		viewPanel.add(txtCompany);

		JLabel lblCompany = new JLabel("Company");
		lblCompany.setBounds(10, 408, 84, 14);
		viewPanel.add(lblCompany);
	}

	private void initializeNotes(String notes){
		JScrollPane scrollNotes = new JScrollPane();
		scrollNotes.setBounds(10, 429, 309, 47);
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

					Entry temp = new Entry(txtFirst.getText(), txtMiddle.getText(), txtLast.getText(), txtPhone1.getText(),
							txtPhone1Type.getText(), txtPhone2.getText(),txtPhone2Type.getText(), txtStreet.getText(),
							txtApt.getText(), txtCity.getText(), txtState.getText(), txtZip.getText(), txtEmail.getText(),
							txtCompany.getText(), txtNotes.getText());

					EditContactWindow edit = new EditContactWindow(ab, temp);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}


			}
		});
		btnEdit.setBounds(131, 487, 89, 30);
		viewPanel.add(btnEdit);

		/*
		 * TODO BE COMPLETED
		 * update view pane
		 * update sidebar
		 */
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this contact?", "WARNING",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

					Entry temp = new Entry(txtFirst.getText(), txtMiddle.getText(), txtLast.getText(), txtPhone1.getText(),
							txtPhone1Type.getText(), txtPhone2.getText(),txtPhone2Type.getText(), txtStreet.getText(),
							txtApt.getText(), txtCity.getText(), txtState.getText(), txtZip.getText(), txtEmail.getText(),
							txtCompany.getText(), txtNotes.getText());
					ab.removeContact(temp);
					JOptionPane.showMessageDialog(null, "Contact deleted.");
					//refresh pane.
				} else {
					// no option
				}
			}
		});
		btnDelete.setBounds(230, 487, 89, 30);
		viewPanel.add(btnDelete);

		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showSaveDialog(viewPanel);

				if(returnVal == JFileChooser.APPROVE_OPTION){
					try {
						File file = new File(chooser.getSelectedFile() + ".txt");
						PrintWriter pw = new PrintWriter(file);
						pw.println(first+ middle+ last);
						pw.println(phone1Type+ phone1);
						pw.println(phone2Type+ phone2);
						pw.println(street);
						pw.println(apt);
						pw.println(city+ state+ zip);
						pw.println(email);
						pw.println(company);
						pw.println(notes);
						pw.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					String savePath = chooser.getSelectedFile().getPath();
					JOptionPane.showMessageDialog(viewPanel, "Contact saved to: " + savePath);
				}

			}
		});
		btnPrint.setBounds(32, 487, 89, 30);
		viewPanel.add(btnPrint);
	}

	private void initializeSort(){
		panelSort = new JPanel();
		panelSort.setBounds(5, 25, 335, 30);
		mainFrame.getContentPane().add(panelSort);
		panelSort.setLayout(null);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Last Name", "First Name", "Zipcode"}));
		comboBox.setBounds(160, 0, 96, 28);
		panelSort.add(comboBox);

		ButtonGroup sort = new ButtonGroup();

		JRadioButton rdbtnAscending = new JRadioButton("Ascend");
		rdbtnAscending.setBounds(6, 3, 74, 23);
		rdbtnAscending.setSelected(true);
		sort.add(rdbtnAscending);
		panelSort.add(rdbtnAscending);

		JRadioButton rdbtnDescending = new JRadioButton("Descend");
		rdbtnDescending.setBounds(80, 3, 81, 23);
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

	private void initializeSearch(){
		JPanel panelSearch = new JPanel();
		panelSearch.setBounds(5, 530, 335, 30);
		mainFrame.getContentPane().add(panelSearch);
		panelSearch.setLayout(null);

		txtSearch = new JTextField();
		txtSearch.setBounds(0, -1, 249, 31);
		panelSearch.add(txtSearch);
		txtSearch.setColumns(10);

		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				keyword = txtSearch.getText();
				ab.search(keyword, sortCommand, sortType);
			}
		});
		btnNewButton.setBounds(248, -1, 87, 30);
		panelSearch.add(btnNewButton);
	}

	private void initializeSidebar(){
		JScrollPane scrollSidebar = new JScrollPane();
		scrollSidebar.setBounds(5, 55, 335, 473);
		mainFrame.getContentPane().add(scrollSidebar);

		JPanel panelSidebar = new JPanel();
		scrollSidebar.setViewportView(panelSidebar);
		panelSidebar.setLayout(new BoxLayout(panelSidebar, BoxLayout.Y_AXIS));

		ArrayList<Entry> entries = ab.getEntries();

		for(Entry e: entries){
			String name = e.getNameLast() + ", " + e.getNameFirst() + " " + e.getNameMiddle().charAt(0);
			ReferencingButton<Entry> btn = new ReferencingButton<Entry>();
			btn.setText(name);
			btn.setValue(e);
			btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ReferencingButton<Entry> temp = (ReferencingButton<Entry>)e.getSource();
					Entry tempEntry = temp.getValue();

					txtFirst.setText(tempEntry.getNameFirst());
					txtMiddle.setText(tempEntry.getNameMiddle().substring(0));
					txtLast.setText(tempEntry.getNameLast());
					txtPhone1.setText(tempEntry.getPhoneNumber1());
					txtPhone1Type.setText(tempEntry.getPhoneType1());
					txtPhone2.setText(tempEntry.getPhoneNumber2());
					txtPhone2Type.setText(tempEntry.getPhoneType2());
					txtStreet.setText(tempEntry.getStreet());
					txtApt.setText(tempEntry.getApt());
					txtCity.setText(tempEntry.getCity());
					txtState.setText(tempEntry.getState());
					txtZip.setText(tempEntry.getZip());
					txtEmail.setText(tempEntry.getEmail());
					txtCompany.setText(tempEntry.getCompany());
					txtNotes.setText(tempEntry.getNotes());
				}
			});
			panelSidebar.add(btn);
		}

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
