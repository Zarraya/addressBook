import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;

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
	private JScrollPane scrollSidebar;
	private JList<Entry> list;
	private DefaultListModel<Entry> dlm;
	private MainWindow window;

	//search data and commands
	private String keyword;
	private int sortType;
	private int sortCommand;

	/**
	 * Create the main GUI of the application.
	 * @throws ParseException
	 */
	public MainWindow(AddressBook ab) throws ParseException {

		//set the local address book to the main instance of address book
		MainWindow.ab = ab;

		//get a reference to the main window
		window = this;

		//initialize the GUI
		initialize();
	}

	/**
	 * Initialize the contents of the MainWindow.
	 * @throws ParseException
	 */
	public void initialize() throws ParseException {

		//create the frame for the main window and set relevant parameters
		mainFrame = new JFrame();
		mainFrame.setResizable(false);
		mainFrame.setTitle("Address Book");
		mainFrame.setBounds(100, 100, 711, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setLocationRelativeTo(null);

		//create a panel to hold the components of the window
		viewPanel = new JPanel();
		viewPanel.setLocation(344, 25);
		viewPanel.setSize(351, 535);
		mainFrame.getContentPane().add(viewPanel);
		viewPanel.setLayout(null);

		//call individual initialization methods for each section of the GUI
		initializeName();
		initializePhone();
		initializeAddress();
		initializeEmail();
		initializeComapny();
		initializeNotes();
		initializeButtons();
		initializeToolbar();
		initializeSidebar();
		initializeSearch();
		initializeSort();

		//make the mainwindow visible
		mainFrame.setVisible(true);
	}

	/*
	A method to initialize the name sections of the content view area
	 */
	private void initializeName(){

		//create the first name text field
		txtFirst = new JTextField();
		txtFirst.setEditable(false);
		txtFirst.setBounds(10, 11, 187, 20);
		txtFirst.setColumns(10);
		viewPanel.add(txtFirst);

		//create the middle name text field
		txtMiddle = new JTextField();
		txtMiddle.setEditable(false);
		txtMiddle.setBounds(10, 51, 187, 20);
		txtMiddle.setColumns(10);
		viewPanel.add(txtMiddle);

		//create the last name text field
		txtLast = new JTextField();
		txtLast.setEditable(false);
		txtLast.setBounds(10, 91, 187, 20);
		txtLast.setColumns(10);
		viewPanel.add(txtLast);

		//add a first name label
		JLabel lblFirst = new JLabel("First Name");
		lblFirst.setBounds(10, 31, 84, 14);
		viewPanel.add(lblFirst);

		//add a middle name label
		JLabel lblMiddle = new JLabel("Middle Name");
		lblMiddle.setBounds(10, 71, 84, 14);
		viewPanel.add(lblMiddle);

		//add a last name label
		JLabel lblLast = new JLabel("Last Name");
		lblLast.setBounds(10, 111, 84, 14);
		viewPanel.add(lblLast);
	}

	/*
	a method to initialize the phone number fields of the contend display area
	 */
	private void initializePhone(){

		//create phone number 1 text field
		txtPhone1 = new JTextField();
		txtPhone1.setEditable(false);
		txtPhone1.setBounds(10, 136, 111, 20);
		viewPanel.add(txtPhone1);

		//create the phone number 2 text field
		txtPhone2 = new JTextField();
		txtPhone2.setEditable(false);
		txtPhone2.setBounds(10, 175, 111, 20);
		viewPanel.add(txtPhone2);

		//create the phone type 1 text field
		txtPhone1Type = new JTextField();
		txtPhone1Type.setEditable(false);
		txtPhone1Type.setBounds(131, 136, 77, 20);
		viewPanel.add(txtPhone1Type);

		//create the phone type 2 text field
		txtPhone2Type = new JTextField();
		txtPhone2Type.setEditable(false);
		txtPhone2Type.setBounds(131, 175, 77, 20);
		viewPanel.add(txtPhone2Type);

		//create a phone 1 label
		JLabel lblPhone1 = new JLabel("Primary phone");
		lblPhone1.setBounds(10, 155, 111, 14);
		viewPanel.add(lblPhone1);

		//create a phone 2 label
		JLabel lblPhone2 = new JLabel("Secondary phone");
		lblPhone2.setBounds(10, 195, 111, 14);
		viewPanel.add(lblPhone2);
	}

	/*
	a method to initialize the address fields of the content view area
	 */
	private void initializeAddress(){

		//create the field for street
		txtStreet = new JTextField();
		txtStreet.setEditable(false);
		txtStreet.setBounds(10, 220, 258, 20);
		txtStreet.setColumns(10);
		viewPanel.add(txtStreet);

		//create the field for apartment
		txtApt = new JTextField();
		txtApt.setEditable(false);
		txtApt.setBounds(10, 260, 258, 20);
		txtApt.setColumns(10);
		viewPanel.add(txtApt);

		//create the field for city
		txtCity = new JTextField();
		txtCity.setEditable(false);
		txtCity.setBounds(10, 300, 135, 20);
		txtCity.setColumns(10);
		viewPanel.add(txtCity);

		//create the field for state
		txtState = new JTextField();
		txtState.setEditable(false);
		txtState.setBounds(156, 300, 42, 20);
		viewPanel.add(txtState);

		//create the field for zip
		txtZip = new JTextField();
		txtZip.setEditable(false);
		txtZip.setBounds(208, 300, 60, 20);
		txtZip.setColumns(10);
		viewPanel.add(txtZip);

		//create a label for street
		JLabel lblStreet = new JLabel("Street Address");
		lblStreet.setBounds(10, 240, 135, 14);
		viewPanel.add(lblStreet);

		//create a label for apartment
		JLabel lblApt = new JLabel("Apt, Suite, Rm");
		lblApt.setBounds(10, 280, 135, 14);
		viewPanel.add(lblApt);

		//create a label for city
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(10, 320, 84, 14);
		viewPanel.add(lblCity);

		//create a label for state
		JLabel lblState = new JLabel("State");
		lblState.setBounds(156, 320, 42, 14);
		viewPanel.add(lblState);

		//create a label for zip
		JLabel lblZip = new JLabel("Zip code");
		lblZip.setBounds(208, 320, 60, 14);
		viewPanel.add(lblZip);
	}

	/*
	a method to initialize the email section of the content view area
	 */
	private void initializeEmail(){

		//create a field for the email
		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setBounds(10, 348, 258, 20);
		txtEmail.setColumns(10);
		viewPanel.add(txtEmail);

		//create a label for email
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 368, 84, 14);
		viewPanel.add(lblEmail);
	}

	/*
	a method to initialize the company area of the content view area
	 */
	private void initializeComapny(){

		//create a field for the company
		txtCompany = new JTextField();
		txtCompany.setEditable(false);
		txtCompany.setBounds(10, 388, 258, 20);
		txtCompany.setColumns(10);
		viewPanel.add(txtCompany);

		//create a label for company
		JLabel lblCompany = new JLabel("Company");
		lblCompany.setBounds(10, 408, 84, 14);
		viewPanel.add(lblCompany);
	}

	/*
	a method to initialize the notes area of the content view area
	 */
	private void initializeNotes(){

		//create a scroll pane for long notes
		JScrollPane scrollNotes = new JScrollPane();
		scrollNotes.setBounds(10, 429, 309, 47);
		viewPanel.add(scrollNotes);

		//create a field for notes
		txtNotes = new JTextArea();
		txtNotes.setEditable(false);
		scrollNotes.setViewportView(txtNotes);
		txtNotes.setWrapStyleWord(true);
		txtNotes.setLineWrap(true);
		txtNotes.setRows(5);
	}

	/*
	a method to initialize the buttons on below the content view area that are specific to the current selection
	 */
	private void initializeButtons(){

		//create the edit button and add an action listener to it
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					//on press the button will create an entry with the currently displayed information and then open the
					//edit contact window
					Entry temp = new Entry(txtFirst.getText(), txtMiddle.getText(), txtLast.getText(), txtPhone1.getText(),
							txtPhone1Type.getText(), txtPhone2.getText(),txtPhone2Type.getText(), txtStreet.getText(),
							txtApt.getText(), txtCity.getText(), txtState.getText(), txtZip.getText(), txtEmail.getText(),
							txtCompany.getText(), txtNotes.getText());

					EditContactWindow edit = new EditContactWindow(ab, temp, window);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEdit.setBounds(131, 487, 89, 30);
		viewPanel.add(btnEdit);

		//create the delete button and an action listener
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				//confirm that the user wants to delete the contact via a joptionpane
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this contact?", "WARNING",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

					//if the choice is yes then create an entry from the information that is displayed and remove it
					Entry temp = new Entry(txtFirst.getText(), txtMiddle.getText(), txtLast.getText(), txtPhone1.getText(),
							txtPhone1Type.getText(), txtPhone2.getText(),txtPhone2Type.getText(), txtStreet.getText(),
							txtApt.getText(), txtCity.getText(), txtState.getText(), txtZip.getText(), txtEmail.getText(),
							txtCompany.getText(), txtNotes.getText());
					ab.removeContact(temp);

					//confirm that the contact was deleted
					JOptionPane.showMessageDialog(null, "Contact deleted.");

					//update the list of contacts to show that the contact is gone
					update(1, temp);

				} else {
					// no option
				}
			}
		});
		btnDelete.setBounds(230, 487, 89, 30);
		viewPanel.add(btnDelete);

		//create the print button and an action listener
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//open a file dialog to choose the location to print to.
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showSaveDialog(viewPanel);

				//if a valid path is returned then print the information that is visible to the specified location
				if(returnVal == JFileChooser.APPROVE_OPTION){
					try {
						File file = new File(chooser.getSelectedFile() + ".txt");
						PrintWriter pw = new PrintWriter(file);
						pw.println("Name: "+txtFirst.getText() + txtMiddle.getText() + txtLast.getText());
						pw.println("Phone 1: "+txtPhone1Type.getText() + " : " + txtPhone1.getText());
						pw.println("Phone 2: "+txtPhone2Type.getText() + " : " + txtPhone2.getText());
						pw.println("Street: "+txtStreet.getText());
						pw.println("Apartment: "+txtApt.getText());
						pw.println("City: "+txtCity.getText() +" State: "+ txtState.getText() +" Zip: "+ txtZip.getText());
						pw.println("Email: "+txtEmail.getText());
						pw.println("Company: "+txtCompany.getText());
						pw.println("Notes: "+txtNotes.getText());
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

	/*
	a method to initialize the controls for sort
	 */
	private void initializeSort(){

		//create panel to hold the components
		panelSort = new JPanel();
		panelSort.setBounds(5, 25, 335, 30);
		mainFrame.getContentPane().add(panelSort);
		panelSort.setLayout(null);

		//create a combo box to choose what to sort by
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Last Name", "First Name", "Zipcode"}));
		comboBox.setBounds(160, 0, 96, 28);
		panelSort.add(comboBox);

		//create a button group to hold radio buttons for ascending or descending
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

		//create a button to activate the sort and an action listener
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

				//call the sort method in address book
				ab.sort(sortType, sortCommand);

				//update the list with the results of the sort.
				update(-1, null);
			}
		});
		btnSort.setBounds(254, 0, 81, 28);
		panelSort.add(btnSort);
	}

	/*
	a method to initialize the controls for searching the address book
	 */
	private void initializeSearch(){

		//create a panel to hold the components
		JPanel panelSearch = new JPanel();
		panelSearch.setBounds(5, 530, 335, 30);
		mainFrame.getContentPane().add(panelSearch);
		panelSearch.setLayout(null);

		//create a text field to get the search keyword
		txtSearch = new JTextField();
		txtSearch.setBounds(0, -1, 249, 31);
		panelSearch.add(txtSearch);
		txtSearch.setColumns(10);

		//create a button and aciton listener to activate the search
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				keyword = txtSearch.getText();

				//call the search method in addressbook
				ab.search(keyword, sortCommand, sortType);

				//update the list with the search results
				update(-1, null);
			}
		});

		btnNewButton.setBounds(248, -1, 87, 30);
		panelSearch.add(btnNewButton);
	}

	/*
	a method to create the sidebar or list area of the gui
	 */
	private void initializeSidebar() {

		//get the entires that are supposed to be visible
		ArrayList<Entry> entries = ab.getEntries();

		//convert the entires to an array
		Entry[] temp = new Entry[entries.size()];
		temp = entries.toArray(temp);

		//get the listmodel
		dlm = new DefaultListModel<Entry>();

		//create the list
		list = new JList<Entry>(temp);

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisible(true);
		list.setVisibleRowCount(-1);
		list.setModel(dlm);

		//add each entry to the list
		for(Entry e : entries){

			dlm.addElement(e);
		}

		//add an action listener to the list for an on changed event
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {

				//get the source of the click
				JList<Entry> temp = (JList<Entry>)e.getSource();
				Entry tempEntry = temp.getSelectedValue();

				//if the index is -1 then do nothing
				if(temp.getSelectedIndex() == -1){

					return;
				}

				//update the content view area with the information from the clicked item
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

		//create a scrollpane and add the list to it
		scrollSidebar = new JScrollPane(list);
		scrollSidebar.setBounds(5, 55, 335, 473);
		mainFrame.getContentPane().add(scrollSidebar);


	}

	/*
	a method to initialize the toolbar at the top of the screen
	 */
	private void initializeToolbar(){

		//create a toolbar
		JToolBar toolBar = new JToolBar();
		toolBar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toolBar.setBounds(5, 5, 716, 20);
		mainFrame.getContentPane().add(toolBar);

		//add a button to it to make a new contact
		JButton btnCreateANew = new JButton("New Contact");
		btnCreateANew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					//open a new contact window
					NewContactWindow contact = new NewContactWindow(ab, window);

				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		toolBar.add(btnCreateANew);
	}

	/*
	a method to update the list with new information
	 */
	public void update(int code, Entry entry){

		//get the arraylist of entires
		ArrayList<Entry> entries = ab.getEntries();

		//save the currently selected item
		int indexForDelete = list.getSelectedIndex();

		//clear the view list
		dlm.clear();

		//create variables to hold the index of the changed item and the count for the loop
		int indexOfChange = 0;
		int count = 0;

		//for every entry in the list
		for(Entry e : entries){

			//add each of the entires to the view list
			dlm.addElement(e);

			//if the code is not for edit, delete or new then do nothing
			if(code == -1){

				continue;
			}

			//select an entry in the view list that has been changed
			if(e.getNameFirst().equals(entry.getNameFirst()) &&
					e.getNameLast().equals(entry.getNameLast()) &&
					e.getNameMiddle().equals(entry.getNameMiddle())){

				indexOfChange = count;
			}

			count++;
		}

		//set the position of the selection in the view list for each of the cases that need it
		switch (code){

			case 0:
				list.setSelectedIndex(indexOfChange);
				break;
			case 1:
				list.setSelectedIndex(indexForDelete);
				break;
			case 2:
				list.setSelectedIndex(indexOfChange);
				break;
			default:
				break;
		}

		//repaint the list to update the graphics
		list.revalidate();
		list.repaint();

	}
}
