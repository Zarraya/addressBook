import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

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
	//image
	private ImageIcon userImg;
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
	
	static AddressBook ab;
	
	/**
	 * Create the application.
	 * @throws ParseException 
	 */
	public NewContactWindow(AddressBook ab) throws ParseException {
		initialize();
		NewContactWindow.ab = ab;
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ParseException 
	 */
	public void initialize() throws ParseException {
		mainFrame = new JFrame();
		mainFrame.setTitle("Create a new contact");
		mainFrame.setResizable(false);
		mainFrame.setBounds(100, 100, 474, 669);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setLocationRelativeTo(null);
		
		mainPanel = new JPanel();
		mainPanel.setLocation(10, 10);
		mainPanel.setSize(448, 619);
		mainPanel.setLayout(null);
		mainFrame.getContentPane().add(mainPanel);
		
		initializeName(first, middle, last);
		initializeImage();
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
		txtFirstName.setToolTipText("Enter first name here.");
		txtFirstName.setBounds(160, 26, 187, 20);
		txtFirstName.setColumns(10);
		mainPanel.add(txtFirstName);
		
		txtMiddleName = new JTextField(middle);
		txtMiddleName.setBounds(160, 66, 187, 20);
		txtMiddleName.setColumns(10);
		mainPanel.add(txtMiddleName);
		
		txtLastName = new JTextField(last);
		txtLastName.setBounds(160, 106, 187, 20);
		txtLastName.setColumns(10);
		mainPanel.add(txtLastName);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(160, 46, 84, 14);
		mainPanel.add(lblFirstName);
		
		JLabel lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setBounds(160, 86, 84, 14);
		mainPanel.add(lblMiddleName);
		mainPanel.add(lblFirstName);
		
		JLabel lblLastname = new JLabel("Last Name");
		lblLastname.setBounds(160, 126, 84, 14);
		mainPanel.add(lblLastname);
	}
	
	private void initializeImage(){
		JPanel imagePanel = new JPanel();
		imagePanel.setBounds(15, 15, 135, 150);
		imagePanel.setLayout(null);
		mainPanel.add(imagePanel);
		
		JLabel lblImg = new JLabel("");
		userImg = new ImageIcon(getClass().getResource("/images/default-user-image.png"));
		lblImg.setIcon(userImg);
		lblImg.setBounds(0, 0, 135, 150);
		imagePanel.add(lblImg);
	
		JButton btnSelectImage = new JButton("Select image");
		btnSelectImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			            ".png", "png");
			    chooser.setFileFilter(filter);
			    chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		        int returnVal = chooser.showOpenDialog(mainPanel);
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		           userImg = new ImageIcon(chooser.getSelectedFile().getPath());
		           lblImg.setIcon(userImg);
		        }
			}
		});
		btnSelectImage.setBounds(25, 170, 117, 28);
		mainPanel.add(btnSelectImage);
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
		txtPhone1.setBounds(15, 209, 111, 20);
		mainPanel.add(txtPhone1);
		
		txtPhone2 = new JFormattedTextField(mskNumber);
		txtPhone2.setValue(formattedNumber2);
		txtPhone2.setFocusLostBehavior(JFormattedTextField.COMMIT);
		txtPhone2.setBounds(15, 248, 111, 20);
		mainPanel.add(txtPhone2);
		
		txtPhone1Type = new JComboBox<String>();
		txtPhone1Type.setModel(new DefaultComboBoxModel<String>(new String[] {"Mobile", "Home", "Work", "Fax", "Other"}));
		if(phone1Type != null){
			txtPhone1Type.setSelectedItem(phone1Type);
		}
		txtPhone1Type.setBounds(136, 209, 77, 20);
		mainPanel.add(txtPhone1Type);
		
		txtPhone2Type = new JComboBox<String>();
		txtPhone2Type.setModel(new DefaultComboBoxModel<String>(new String[] {"Mobile", "Home", "Work", "Fax", "Other"}));
		if(phone2Type != null){
			txtPhone2Type.setSelectedItem(phone2Type);
		}
		txtPhone2Type.setBounds(136, 248, 77, 20);
		mainPanel.add(txtPhone2Type);
		
		JLabel lblPhone1 = new JLabel("Primary phone");
		lblPhone1.setBounds(15, 228, 105, 14);
		mainPanel.add(lblPhone1);
		
		JLabel lblPhone2 = new JLabel("Secondary phone");
		lblPhone2.setBounds(15, 268, 111, 14);
		mainPanel.add(lblPhone2);
	}
	
	private void initializeAddress(String street, String apt, String city, String state, String zip){
		txtStreet = new JTextField(street);
		txtStreet.setBounds(15, 293, 258, 20);
		txtStreet.setColumns(10);
		mainPanel.add(txtStreet);
		
		txtApt = new JTextField(apt);
		txtApt.setColumns(10);
		txtApt.setBounds(15, 333, 258, 20);
		mainPanel.add(txtApt);
		
		txtCity = new JTextField(city);
		txtCity.setBounds(15, 373, 135, 20);
		txtCity.setColumns(10);
		mainPanel.add(txtCity);
		
		txtState = new JComboBox<String>();
		txtState.setModel(new DefaultComboBoxModel<String>(new String[] {"", "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", 
				"GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", 
				"NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "WA", "WV", "WI", "WY"}));
		if(state != null){
			txtState.setSelectedItem(state);
		}
		txtState.setBounds(161, 373, 42, 20);
		mainPanel.add(txtState);
		
		txtZip = new JTextField(zip);
		txtZip.setBounds(213, 373, 60, 20);
		txtZip.setColumns(10);
		mainPanel.add(txtZip);
		
		JLabel lblAddress1 = new JLabel("Street Address");
		lblAddress1.setBounds(15, 313, 135, 14);
		mainPanel.add(lblAddress1);
		
		JLabel lblAddress2 = new JLabel("Apt, Suite, Rm");
		lblAddress2.setBounds(15, 353, 135, 14);
		mainPanel.add(lblAddress2);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(15, 393, 84, 14);
		mainPanel.add(lblCity);
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(161, 393, 42, 14);
		mainPanel.add(lblState);
		
		JLabel lblZip = new JLabel("Zip code");
		lblZip.setBounds(213, 393, 60, 14);
		mainPanel.add(lblZip);
	}
	
	private void initializeEmail(String email){
		txtEmail = new JTextField(email);
		txtEmail.setColumns(10);
		txtEmail.setBounds(15, 421, 258, 20);
		mainPanel.add(txtEmail);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(15, 441, 84, 14);
		mainPanel.add(lblEmail);
	}
	
	private void initializeCompany(String company){
		txtCompany = new JTextField(company);
		txtCompany.setBounds(15, 461, 258, 20);
		txtCompany.setColumns(10);
		mainPanel.add(txtCompany);
		
		JLabel lblCompany = new JLabel("Company");
		lblCompany.setBounds(15, 481, 84, 14);
		mainPanel.add(lblCompany);
	}
	
	private void initializeNotes(String notes){
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 502, 319, 48);
		mainPanel.add(scrollPane);
		
		txtNotes = new JTextArea(notes);
		scrollPane.setViewportView(txtNotes);
		txtNotes.setRows(5);
	}
	
	private void initializeButtons(){
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Changes saved.");
				//TODO store contact
				getInput();
				ab.addContact(first, middle, last, phone1, phone1Type, phone2, phone2Type, street, 
						apt, city, state, zip, email, company, notes, image);
				mainFrame.dispose();
			}
		});
		btnSubmit.setBounds(174, 578, 89, 30);
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
		btnClear.setBounds(273, 578, 89, 30);
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
		btnCancel.setBounds(75, 578, 89, 30);
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
		image = userImg.getDescription();
	}
}
