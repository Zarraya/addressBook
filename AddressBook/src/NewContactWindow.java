import javax.swing.JFrame;
import javax.swing.JTextField;
import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

/**
 *
 * @author javeney
 * Modified 2/14/16 : Linked buttons to dialog windows.
 * Modified 2/15/16 : Organized the hot mess of code.
 * 					  Altered the select image button to work.
 * 					  Input is ready to be sent
 *
 */

public class NewContactWindow {
	//frames and panels
	private JFrame mainFrame;
	private JPanel mainPanel;
	//text fields
	private JTextField txtFirstName;
	private JTextField txtMiddleName;
	private JTextField txtLastName;
	private JTextField txtStreet;
	private JTextField txtApt;
	private JTextField txtCity;
	private JTextField txtZip;
	private JTextField txtEmail;
	private JTextField txtCompany;
	private JFormattedTextField txtPhone1;
	private JFormattedTextField txtPhone2;
	//combo boxes
	private JComboBox<String> txtPhone1Type;
	private JComboBox<String> txtPhone2Type;
	private JComboBox<String> txtState;
	//text pane
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

	static AddressBook ab;

	/**
	 * Create the application.
	 * @throws ParseException
	 */
	public NewContactWindow(AddressBook ab) throws ParseException {
		NewContactWindow.ab = ab;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ParseException
	 */
	public void initialize() throws ParseException {
		mainFrame = new JFrame();
		mainFrame.setTitle("Create a new contact");
		mainFrame.setResizable(false);
		mainFrame.setBounds(100, 100, 384, 573);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setLocationRelativeTo(null);

		mainPanel = new JPanel();
		mainPanel.setLocation(10, 10);
		mainPanel.setSize(358, 523);
		mainPanel.setLayout(null);
		mainFrame.getContentPane().add(mainPanel);

		initializeName(first, middle, last);
		initializePhone(phone1, phone2, phone1Type, phone2Type);
		initializeAddress(street, apt, city, state, zip);
		initializeEmail(email);
		initializeCompany(company);
		initializeNotes(notes);
		initializeButtons();

		mainFrame.setVisible(true);
	}

	private boolean checkEmpty(){
		if((txtFirstName.getText().isEmpty()) && txtLastName.getText().isEmpty()){
			return true;
		}else{
			return false;
		}
	}

	private void initializeName(String first, String middle, String last){
		txtFirstName = new JTextField(first);
		txtFirstName.setBounds(15, 11, 258, 20);
		mainPanel.add(txtFirstName);

		txtMiddleName = new JTextField(middle);
		txtMiddleName.setBounds(15, 51, 258, 20);
		txtMiddleName.setColumns(10);
		mainPanel.add(txtMiddleName);

		txtLastName = new JTextField(last);
		txtLastName.setBounds(15, 91, 258, 20);
		txtLastName.setColumns(10);
		mainPanel.add(txtLastName);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(15, 31, 84, 14);
		mainPanel.add(lblFirstName);

		JLabel lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setBounds(15, 71, 84, 14);
		mainPanel.add(lblMiddleName);
		mainPanel.add(lblFirstName);

		JLabel lblLastname = new JLabel("Last Name");
		lblLastname.setBounds(15, 111, 84, 14);
		mainPanel.add(lblLastname);
	}

	private void initializePhone(String phone1, String phone2, String phone1Type, String phone2Type){
		MaskFormatter mskNumber = null;
		String formattedNumber1 = null;
		String formattedNumber2 = null;

		if((phone1 != null) || (phone2 != null)){
			formattedNumber1 = String.format("+1(%s) %s-%s",
					phone1.substring(0, 3),
					phone1.substring(3, 6),
					phone1.substring(6, 10));
			formattedNumber2 = String.format("+1(%s) %s-%s",
					phone2.substring(0, 3),
					phone2.substring(3, 6),
					phone2.substring(6, 10));
		}

		try {
			mskNumber = new MaskFormatter("+1(###) ###-####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtPhone1 = new JFormattedTextField(mskNumber);
		txtPhone1.setValue(formattedNumber1);
		txtPhone1.setFocusLostBehavior(JFormattedTextField.COMMIT);
		txtPhone1.setBounds(15, 136, 111, 20);
		mainPanel.add(txtPhone1);

		txtPhone2 = new JFormattedTextField(mskNumber);
		txtPhone2.setValue(formattedNumber2);
		txtPhone2.setFocusLostBehavior(JFormattedTextField.COMMIT);
		txtPhone2.setBounds(15, 175, 111, 20);
		mainPanel.add(txtPhone2);

		txtPhone1Type = new JComboBox<String>();
		txtPhone1Type.setModel(new DefaultComboBoxModel<String>(new String[] {"Mobile", "Home", "Work", "Fax", "Other"}));
		if(phone1Type != null){
			txtPhone1Type.setSelectedItem(phone1Type);
		}
		txtPhone1Type.setBounds(136, 136, 77, 20);
		mainPanel.add(txtPhone1Type);

		txtPhone2Type = new JComboBox<String>();
		txtPhone2Type.setModel(new DefaultComboBoxModel<String>(new String[] {"Mobile", "Home", "Work", "Fax", "Other"}));
		if(phone2Type != null){
			txtPhone2Type.setSelectedItem(phone2Type);
		}
		txtPhone2Type.setBounds(136, 175, 77, 20);
		mainPanel.add(txtPhone2Type);

		JLabel lblPhone1 = new JLabel("Primary phone");
		lblPhone1.setBounds(15, 155, 105, 14);
		mainPanel.add(lblPhone1);

		JLabel lblPhone2 = new JLabel("Secondary phone");
		lblPhone2.setBounds(15, 195, 111, 14);
		mainPanel.add(lblPhone2);
	}

	private void initializeAddress(String street, String apt, String city, String state, String zip){
		txtStreet = new JTextField(street);
		txtStreet.setBounds(15, 220, 258, 20);
		txtStreet.setColumns(10);
		mainPanel.add(txtStreet);

		txtApt = new JTextField(apt);
		txtApt.setColumns(10);
		txtApt.setBounds(15, 260, 258, 20);
		mainPanel.add(txtApt);

		txtCity = new JTextField(city);
		txtCity.setBounds(15, 300, 135, 20);
		txtCity.setColumns(10);
		mainPanel.add(txtCity);

		txtState = new JComboBox<String>();
		txtState.setModel(new DefaultComboBoxModel<String>(new String[] {"", "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL",
				"GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV",
				"NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "WA", "WV", "WI", "WY"}));
		if(state != null){
			txtState.setSelectedItem(state);
		}
		txtState.setBounds(161, 300, 42, 20);
		mainPanel.add(txtState);

		txtZip = new JTextField(zip);
		txtZip.setBounds(213, 300, 60, 20);
		txtZip.setColumns(10);
		mainPanel.add(txtZip);

		JLabel lblAddress1 = new JLabel("Street Address");
		lblAddress1.setBounds(15, 240, 135, 14);
		mainPanel.add(lblAddress1);

		JLabel lblAddress2 = new JLabel("Apt, Suite, Rm");
		lblAddress2.setBounds(15, 280, 135, 14);
		mainPanel.add(lblAddress2);

		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(15, 320, 84, 14);
		mainPanel.add(lblCity);

		JLabel lblState = new JLabel("State");
		lblState.setBounds(161, 320, 42, 14);
		mainPanel.add(lblState);

		JLabel lblZip = new JLabel("Zip code");
		lblZip.setBounds(213, 320, 60, 14);
		mainPanel.add(lblZip);
	}

	private void initializeEmail(String email){
		txtEmail = new JTextField(email);
		txtEmail.setColumns(10);
		txtEmail.setBounds(15, 348, 258, 20);
		mainPanel.add(txtEmail);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(15, 368, 84, 14);
		mainPanel.add(lblEmail);
	}

	private void initializeCompany(String company){
		txtCompany = new JTextField(company);
		txtCompany.setBounds(15, 388, 258, 20);
		txtCompany.setColumns(10);
		mainPanel.add(txtCompany);

		JLabel lblCompany = new JLabel("Company");
		lblCompany.setBounds(15, 408, 84, 14);
		mainPanel.add(lblCompany);
	}

	private void initializeNotes(String notes){
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 429, 319, 48);
		mainPanel.add(scrollPane);

		txtNotes = new JTextArea(notes);
		scrollPane.setViewportView(txtNotes);
		txtNotes.setRows(5);
	}

	private void initializeButtons(){
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getInput();
				if(checkSave()){
					ab.addContact(first, middle, last, phone1, phone1Type, phone2, phone2Type,
							street, apt, city, state, zip, email, company, notes);
					JOptionPane.showMessageDialog(null, "Changes saved.");
					mainFrame.dispose();
				}else{
					JOptionPane.showMessageDialog(null, "To save contact please add first and last name.");
				}

			}
		});
		btnSubmit.setBounds(134, 488, 89, 30);
		mainPanel.add(btnSubmit);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFirstName.setText("");
				txtMiddleName.setText("");
				txtLastName.setText("");
				txtStreet.setText("");
				txtApt.setText("");
				txtCity.setText("");
				txtZip.setText("");
				txtEmail.setText("");
				txtCompany.setText("");
				txtNotes.setText("");
				txtState.setSelectedIndex(0);
				txtPhone1.setValue(null);
				txtPhone2.setValue(null);
				txtPhone1Type.setSelectedIndex(0);
				txtPhone2Type.setSelectedIndex(0);
			}
		});
		btnClear.setBounds(233, 488, 89, 30);
		mainPanel.add(btnClear);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkEmpty()){
					mainFrame.dispose();
				}else{
					if(JOptionPane.showConfirmDialog(null, "Are you sure you want to discard changes?", "WARNING",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						mainFrame.dispose();
					}else{
						//do nothing
					}
				}
			}
		});
		btnCancel.setBounds(35, 488, 89, 30);
		mainPanel.add(btnCancel);
	}

	/**
	 * Assign all input to its own instance variable
	 */
	private void getInput(){
		first = txtFirstName.getText();
		middle = txtMiddleName.getText();
		last = txtLastName.getText();
		street = txtStreet.getText();
		zip = txtZip.getText();
		apt = txtApt.getText();
		email = txtEmail.getText();
		company = txtCompany.getText();
		phone1 = txtPhone1.getText();
		phone2 = txtPhone2.getText();
		notes = txtNotes.getText();
		phone1Type = (String) txtPhone1Type.getSelectedItem();
		phone2Type = (String) txtPhone2Type.getSelectedItem();
		city = txtCity.getText();
		state = (String) txtState.getSelectedItem();
	}

	private boolean checkSave(){
		if(first.isEmpty() || last.isEmpty()){
			return false;
		}else{ return true; }
	}
}
