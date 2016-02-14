import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.text.ParseException;
import java.awt.CardLayout;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;
import javax.swing.JPanel;
import javax.swing.DropMode;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author javeney
 * Last Modified 2/14/16
 *
 */

public class EditContactWindow {

	private JFrame mainFrame;
	private JTextField txtFirstName;
	private JTextField txtMiddleName;
	private JTextField txtLastName;
	private JTextField txtStreet;
	private JTextField txtApt;
	private JTextField txtCity;
	private JTextField txtZip;
	private JTextField txtEmail;
	private JTextField txtCompany;
	private JFormattedTextField phone1;
	private JFormattedTextField phone2;
	private JComboBox phoneType1;
	private JComboBox phoneType2;
	private JComboBox comboState;
	private JTextArea txtNotes;
	
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
	private String phoneOne;
	private String phoneOneType;
	private String phoneTwo;
	private String phoneTwoType;
	private String notes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditContactWindow window = new EditContactWindow();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * *************************************
	 * ALL TEXT FIELDS NEED TO BE PREFILLED BY EXISTING CONTACT
	 * ****************************************
	 */
	
	
	/**
	 * Create the application.
	 * @throws ParseException 
	 */
	public EditContactWindow() throws ParseException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ParseException 
	 */
	private void initialize() throws ParseException {
		mainFrame = new JFrame();
		mainFrame.setTitle("Edit contact");
		mainFrame.setResizable(false);
		mainFrame.setBounds(100, 100, 474, 669);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		
		MaskFormatter mskNumber = new MaskFormatter("+1(###)###-####");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLocation(10, 10);
		mainPanel.setSize(448, 619);
		mainPanel.setLayout(null);
		mainFrame.getContentPane().add(mainPanel);
		
		JPanel imagePanel = new JPanel();
		imagePanel.setBounds(15, 15, 135, 151);
		imagePanel.setLayout(null);
		mainPanel.add(imagePanel);
		
		/*
		 * TO BE COMPLETED
		 * - update label with selection
		 */
		JLabel defaultImg = new JLabel("");
		defaultImg.setIcon(new ImageIcon(NewContactWindow.class.getResource("/images/default-user-image.png")));
		defaultImg.setBounds(10, 11, 115, 129);
		imagePanel.add(defaultImg);
		
		/*
		 * TO BE COMPLETED
		 * - action to pull up dialog for selecting image
		 */
		JButton btnSelectImage = new JButton("Select image");
		btnSelectImage.setBounds(25, 170, 117, 28);
		mainPanel.add(btnSelectImage);
		
		txtFirstName = new JTextField();
		txtFirstName.setToolTipText("Enter first name here.");
		txtFirstName.setBounds(160, 26, 187, 20);
		txtFirstName.setColumns(10);
		mainPanel.add(txtFirstName);
		
		txtMiddleName = new JTextField();
		txtMiddleName.setBounds(160, 66, 187, 20);
		mainPanel.add(txtMiddleName);
		txtMiddleName.setColumns(10);
		
		txtLastName = new JTextField();
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
		
		JFormattedTextField phone1 = new JFormattedTextField(mskNumber);
		phone1.setFocusLostBehavior(JFormattedTextField.COMMIT);
		phone1.setBounds(15, 248, 111, 20);
		mainPanel.add(phone1);
		
		JFormattedTextField phone2 = new JFormattedTextField(mskNumber);
		phone2.setFocusLostBehavior(JFormattedTextField.COMMIT);
		phone2.setBounds(15, 209, 111, 20);
		mainPanel.add(phone2);
		
		JComboBox phoneType1 = new JComboBox();
		phoneType1.setModel(new DefaultComboBoxModel(new String[] {"Mobile", "Home", "Work", "Fax", "Other"}));
		phoneType1.setBounds(136, 209, 77, 20);
		mainPanel.add(phoneType1);
		
		JComboBox phoneType2 = new JComboBox();
		phoneType2.setModel(new DefaultComboBoxModel(new String[] {"Mobile", "Home", "Work", "Fax", "Other"}));
		phoneType2.setBounds(136, 248, 77, 20);
		mainPanel.add(phoneType2);
		
		JLabel lblPhone1 = new JLabel("Primary phone");
		lblPhone1.setBounds(15, 228, 84, 14);
		mainPanel.add(lblPhone1);
		
		JLabel lblPhone2 = new JLabel("Secondary phone");
		lblPhone2.setBounds(15, 268, 84, 14);
		mainPanel.add(lblPhone2);
		
		txtStreet = new JTextField();
		txtStreet.setBounds(15, 293, 258, 20);
		txtStreet.setColumns(10);
		mainPanel.add(txtStreet);
		
		txtApt = new JTextField();
		txtApt.setColumns(10);
		txtApt.setBounds(15, 333, 258, 20);
		mainPanel.add(txtApt);
		
		txtCity = new JTextField();
		txtCity.setBounds(15, 373, 135, 20);
		txtCity.setColumns(10);
		mainPanel.add(txtCity);
		
		JComboBox comboState = new JComboBox();
		comboState.setModel(new DefaultComboBoxModel(new String[] {"", "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "WA", "WV", "WI", "WY"}));
		comboState.setBounds(161, 373, 42, 20);
		mainPanel.add(comboState);
		
		txtZip = new JTextField();
		txtZip.setBounds(213, 373, 60, 20);
		txtZip.setColumns(10);
		mainPanel.add(txtZip);
		
		JLabel lblAddress1 = new JLabel("Street Address");
		lblAddress1.setBounds(15, 313, 84, 14);
		mainPanel.add(lblAddress1);
		
		JLabel lblAddress2 = new JLabel("Apt, Suite, Rm");
		lblAddress2.setBounds(15, 353, 84, 14);
		mainPanel.add(lblAddress2);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(15, 393, 84, 14);
		mainPanel.add(lblCity);
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(161, 393, 42, 14);
		mainPanel.add(lblState);
		
		JLabel lblZip = new JLabel("Zip code");
		lblZip.setBounds(213, 393, 42, 14);
		mainPanel.add(lblZip);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(15, 421, 258, 20);
		mainPanel.add(txtEmail);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(15, 441, 84, 14);
		mainPanel.add(lblEmail);
		
		txtCompany = new JTextField();
		txtCompany.setBounds(15, 461, 258, 20);
		txtCompany.setColumns(10);
		mainPanel.add(txtCompany);
		
		JLabel lblCompany = new JLabel("Company");
		lblCompany.setBounds(15, 481, 84, 14);
		mainPanel.add(lblCompany);
		
		JLabel lblLastname = new JLabel("Last Name");
		lblLastname.setBounds(160, 126, 84, 14);
		mainPanel.add(lblLastname);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 502, 319, 48);
		mainPanel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setRows(5);
		
		JPanel dialogPanel = new JPanel();
		dialogPanel.setBounds(10, 170, 428, 229);
		mainPanel.add(dialogPanel);
		dialogPanel.setLayout(null);
		
		/*
		 * TO BE COMPLETED
		 * - save contact
		 */
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(dialogPanel, "Contact saved");
				//save contact
				mainFrame.dispose();
			}
		});
		btnSave.setBounds(174, 578, 89, 30);
		mainPanel.add(btnSave);
		
		/*
		 * TO BE COMPLETED
		 * - delete command
		 */
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(dialogPanel, "Are you sure you want to delete this contact?", "WARNING",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				    JOptionPane.showMessageDialog(dialogPanel, "Contact deleted.");
				    //delete contact
				    mainFrame.dispose();
				} else {
				    // no option
				}
			}
		});
		btnDelete.setBounds(273, 578, 89, 30);
		mainPanel.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
			}
		});
		btnCancel.setBounds(75, 578, 89, 30);
		mainPanel.add(btnCancel);
	}
}


