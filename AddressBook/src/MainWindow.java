import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.text.MaskFormatter;

import java.awt.Font;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

    private JFrame frmAddressBook;
    private JTextField txtFirst;
    private JTextField txtMiddle;
    private JTextField lblFirst;
    private JTextField txtStreet;
    private JTextField txtStreet2;
    private JTextField txtCity;
    private JTextField txtZip;
    private JTextField textField_7;
    private JTextField textField_8;
    private JPanel panelSort;
    private JTextField textField_9;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainWindow window = new MainWindow();
                    window.frmAddressBook.setVisible(true);

                    AddressBook ab = new AddressBook();

                    Entry ent = new Entry("Austin", "R", "Nafziger", "", "5402208162", "", "hello@me", "F", "1", "or", "fl", "");

                    ab.addContact("Bubbles", "R", "Nafziger", "", "5402208162", "", "hello@me", "F", "1", "or", "fl", "");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     * @throws ParseException
     */
    public MainWindow() throws ParseException {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     * @throws ParseException
     */
    private void initialize() throws ParseException {
        frmAddressBook = new JFrame();
        frmAddressBook.setTitle("Address Book");
        frmAddressBook.setBounds(100, 100, 634, 650);
        frmAddressBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmAddressBook.getContentPane().setLayout(null);

        JPanel viewPanel = new JPanel();
        viewPanel.setLocation(238, 25);
        viewPanel.setSize(375, 580);
        frmAddressBook.getContentPane().add(viewPanel);
        viewPanel.setLayout(null);

        JPanel imagePanel = new JPanel();
        imagePanel.setBounds(10, 11, 135, 150);
        viewPanel.add(imagePanel);

        JLabel label_12 = new JLabel("");
//        label_12.setIcon(new ImageIcon(MainWindow.class.getResource("/images/default-user-image.png")));
        imagePanel.add(label_12);

        txtFirst = new JTextField();
        txtFirst.setEditable(false);
        txtFirst.setBounds(155, 29, 187, 20);
        txtFirst.setToolTipText("Enter first name here.");
        txtFirst.setColumns(10);
        viewPanel.add(txtFirst);

        txtMiddle = new JTextField();
        txtMiddle.setEditable(false);
        txtMiddle.setBounds(155, 69, 187, 20);
        txtMiddle.setColumns(10);
        viewPanel.add(txtMiddle);

        lblFirst = new JTextField();
        lblFirst.setEditable(false);
        lblFirst.setBounds(155, 109, 187, 20);
        lblFirst.setColumns(10);
        viewPanel.add(lblFirst);

        JLabel label = new JLabel("First Name");
        label.setBounds(155, 49, 84, 14);
        viewPanel.add(label);

        JLabel lblMiddle = new JLabel("Middle Name");
        lblMiddle.setBounds(155, 89, 84, 14);
        viewPanel.add(lblMiddle);

        MaskFormatter mskNumber = new MaskFormatter("+1(###)###-####");

        JFormattedTextField txtPhone1 = new JFormattedTextField(mskNumber);
        txtPhone1.setEditable(false);
        txtPhone1.setBounds(10, 211, 111, 20);
        viewPanel.add(txtPhone1);

        JFormattedTextField txtPhone2 = new JFormattedTextField(mskNumber);
        txtPhone2.setEditable(false);
        txtPhone2.setBounds(10, 172, 111, 20);
        viewPanel.add(txtPhone2);

        JTextField lblPhoneType1 = new JTextField();
        lblPhoneType1.setEditable(false);
        lblPhoneType1.setText("Home");
        lblPhoneType1.setBounds(131, 172, 77, 20);
        viewPanel.add(lblPhoneType1);

        JTextField lblPhoneType2 = new JTextField();
        lblPhoneType2.setEditable(false);
        lblPhoneType2.setText("Mobile ");
        lblPhoneType2.setBounds(131, 211, 77, 20);
        viewPanel.add(lblPhoneType2);

        JLabel lblPhone1 = new JLabel("Primary phone");
        lblPhone1.setBounds(10, 191, 84, 14);
        viewPanel.add(lblPhone1);

        JLabel lblPhone2 = new JLabel("Secondary phone");
        lblPhone2.setBounds(10, 231, 84, 14);
        viewPanel.add(lblPhone2);

        txtStreet = new JTextField();
        txtStreet.setEditable(false);
        txtStreet.setBounds(10, 256, 258, 20);
        txtStreet.setColumns(10);
        viewPanel.add(txtStreet);

        txtStreet2 = new JTextField();
        txtStreet2.setEditable(false);
        txtStreet2.setBounds(10, 296, 258, 20);
        txtStreet2.setColumns(10);
        viewPanel.add(txtStreet2);

        txtCity = new JTextField();
        txtCity.setEditable(false);
        txtCity.setBounds(10, 336, 135, 20);
        txtCity.setColumns(10);
        viewPanel.add(txtCity);

        JTextField comboState = new JTextField();
        comboState.setText("FL");
        comboState.setEditable(false);
        comboState.setBounds(156, 336, 42, 20);
        viewPanel.add(comboState);

        txtZip = new JTextField();
        txtZip.setEditable(false);
        txtZip.setBounds(208, 336, 60, 20);
        txtZip.setColumns(10);
        viewPanel.add(txtZip);

        JLabel lblStreet = new JLabel("Street Address");
        lblStreet.setBounds(10, 276, 84, 14);
        viewPanel.add(lblStreet);

        JLabel lblApt = new JLabel("Apt, Suite, Rm");
        lblApt.setBounds(10, 316, 84, 14);
        viewPanel.add(lblApt);

        JLabel lblCity = new JLabel("City");
        lblCity.setBounds(10, 356, 84, 14);
        viewPanel.add(lblCity);

        JLabel lblState = new JLabel("State");
        lblState.setBounds(156, 356, 42, 14);
        viewPanel.add(lblState);

        JLabel lblZip = new JLabel("Zip code");
        lblZip.setBounds(208, 356, 42, 14);
        viewPanel.add(lblZip);

        textField_7 = new JTextField();
        textField_7.setEditable(false);
        textField_7.setBounds(10, 384, 258, 20);
        textField_7.setColumns(10);
        viewPanel.add(textField_7);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(10, 404, 84, 14);
        viewPanel.add(lblEmail);

        textField_8 = new JTextField();
        textField_8.setEditable(false);
        textField_8.setBounds(10, 424, 258, 20);
        textField_8.setColumns(10);
        viewPanel.add(textField_8);

        JLabel lblCompany = new JLabel("Company");
        lblCompany.setBounds(10, 444, 84, 14);
        viewPanel.add(lblCompany);

        JLabel label_11 = new JLabel("Last Name");
        label_11.setBounds(155, 129, 84, 14);
        viewPanel.add(label_11);

        JButton btnEdit = new JButton("Edit");
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditContactWindow.main(null);
            }
        });
        btnEdit.setBounds(93, 523, 89, 30);
        viewPanel.add(btnEdit);

        JButton button_2 = new JButton("Delete");
        button_2.setBounds(192, 523, 89, 30);
        viewPanel.add(button_2);

        JScrollPane scrollNotes = new JScrollPane();
        scrollNotes.setBounds(10, 465, 309, 47);
        viewPanel.add(scrollNotes);

        JTextArea txtNotes = new JTextArea();
        txtNotes.setEditable(false);
        scrollNotes.setViewportView(txtNotes);
        txtNotes.setWrapStyleWord(true);
        txtNotes.setLineWrap(true);
        txtNotes.setRows(5);

        JScrollPane scrollSidebar = new JScrollPane();
        scrollSidebar.setBounds(5, 53, 232, 478);
        frmAddressBook.getContentPane().add(scrollSidebar);

        JPanel panelSidebar = new JPanel();
        scrollSidebar.setViewportView(panelSidebar);
        panelSidebar.setLayout(new BorderLayout(0, 0));

        JToolBar toolBar = new JToolBar();
        toolBar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        toolBar.setBounds(5, 5, 608, 20);
        frmAddressBook.getContentPane().add(toolBar);

        JButton btnCreateANew = new JButton("New Contact");
        btnCreateANew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NewContactWindow.main(null);
            }
        });
        toolBar.add(btnCreateANew);

        panelSort = new JPanel();
        panelSort.setBounds(5, 25, 232, 28);
        frmAddressBook.getContentPane().add(panelSort);
        panelSort.setLayout(null);

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"A-Z", "Z-A"}));
        comboBox.setBounds(107, 0, 125, 28);
        panelSort.add(comboBox);

        JLabel lblSortBy = new JLabel("Sort by");
        lblSortBy.setBounds(5, 0, 107, 28);
        panelSort.add(lblSortBy);

        JPanel panelSearch = new JPanel();
        panelSearch.setBounds(5, 530, 232, 75);
        frmAddressBook.getContentPane().add(panelSearch);
        panelSearch.setLayout(null);

        JRadioButton rdbtnName = new JRadioButton("Name");
        rdbtnName.setBounds(10, 27, 58, 23);
        panelSearch.add(rdbtnName);

        JRadioButton rdbtnCompany = new JRadioButton("Company");
        rdbtnCompany.setBounds(70, 27, 71, 23);
        panelSearch.add(rdbtnCompany);

        JRadioButton rdbtnZipCode = new JRadioButton("Zip code");
        rdbtnZipCode.setBounds(143, 53, 71, 23);
        panelSearch.add(rdbtnZipCode);

        JRadioButton rdbtnPhone = new JRadioButton("Phone");
        rdbtnPhone.setBounds(10, 53, 58, 23);
        panelSearch.add(rdbtnPhone);

        JRadioButton rdbtnCity = new JRadioButton("City");
        rdbtnCity.setBounds(143, 27, 58, 23);
        panelSearch.add(rdbtnCity);

        JRadioButton rdbtnState = new JRadioButton("State");
        rdbtnState.setBounds(70, 53, 71, 23);
        panelSearch.add(rdbtnState);

        textField_9 = new JTextField();
        textField_9.setBounds(0, 0, 156, 23);
        panelSearch.add(textField_9);
        textField_9.setColumns(10);

        JButton btnNewButton = new JButton("Search");
        btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
        btnNewButton.setBounds(155, 0, 77, 23);
        panelSearch.add(btnNewButton);

    }
}

