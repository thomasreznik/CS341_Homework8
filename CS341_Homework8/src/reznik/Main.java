package reznik;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Main {

	private JFrame frame;
	public JTextField sku_textfield;
	public JTextField title_textfield;
	public JTextField price_textfield;
	public JTextField quantity_textfield;
	public JButton btnBuild;
	public JComboBox comboBox;
	public JTextArea textArea;
	public String[] options = { "Add Textbook to Inventory", "Remove Textbook by SKU",
			"Display Information of Textbook by SKU", "Display Inventory" };
	public String selectedOption;

	public ArrayList<Video> list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Sku:");
		lblNewLabel.setBounds(6, 19, 41, 16);
		frame.getContentPane().add(lblNewLabel);

		sku_textfield = new JTextField();
		sku_textfield.setBounds(44, 14, 153, 26);
		frame.getContentPane().add(sku_textfield);
		sku_textfield.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Title:");
		lblNewLabel_1.setBounds(209, 19, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);

		title_textfield = new JTextField();
		title_textfield.setBounds(261, 14, 170, 26);
		frame.getContentPane().add(title_textfield);
		title_textfield.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Price:");
		lblNewLabel_2.setBounds(6, 58, 41, 16);
		frame.getContentPane().add(lblNewLabel_2);

		price_textfield = new JTextField();
		price_textfield.setBounds(44, 53, 153, 26);
		frame.getContentPane().add(price_textfield);
		price_textfield.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Quantity: ");
		lblNewLabel_3.setBounds(198, 58, 70, 16);
		frame.getContentPane().add(lblNewLabel_3);

		quantity_textfield = new JTextField();
		quantity_textfield.setBounds(261, 53, 170, 26);
		frame.getContentPane().add(quantity_textfield);
		quantity_textfield.setColumns(10);

		comboBox = new JComboBox(options);
		comboBox.setBounds(6, 102, 438, 26);
		frame.getContentPane().add(comboBox);

		btnBuild = new JButton("Run");
		btnBuild.setBounds(163, 128, 117, 29);
		frame.getContentPane().add(btnBuild);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(261, 225, 4, 4);
		frame.getContentPane().add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 157, 438, 115);
		frame.getContentPane().add(scrollPane_1);

		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);

		list = new ArrayList<Video>();

	}

//Puts an action listener on the button
	public void createEvents() {

		btnBuild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				buildOutput();

			}
		});
	}

	public void buildOutput() {
		// puts what the user selected from the drop down into a String
		String selectedOption = (String) comboBox.getSelectedItem();

		// Displaying inventory option
		if (selectedOption.equals("Display Inventory")) {
			if (list.size() > 0) {
				textArea.setText(list.toString());
			} else {
				textArea.setText("Inventory is Empty");
			}
			return;
		}
		String sku = sku_textfield.getText();
		String q = quantity_textfield.getText();
		String p = price_textfield.getText();
		String title = title_textfield.getText();
		int quantity = 0;
		int price = 0;

		// Displaying info of textbook by SKU option
		if (selectedOption.equals("Display Information of Textbook by SKU")) {
			if (sku.isEmpty()) {
				textArea.setText("SKU input is empty, please input a SKU ");
				// Displays the textbook info by SKU if found within inventory
			} else {
				for (int i = 0; i < list.size(); i++) {
					if (sku.equals(list.get(i).getSku())) {
						textArea.setText(list.get(i).toString());
						return;
						// Other case is that the SKU value is valid but it doesn't exist within the
						// inventory
					}
				}

				textArea.setText("This SKU doesn't exist, try another one");
				return;
			}
		}

		// Add textbook to inventory option
		if (selectedOption.equals("Add Textbook to Inventory")) {

			// Check if any fields are empty
			if ((sku.isEmpty()) || title.isEmpty() || p.isEmpty() || q.isEmpty()) {
				textArea.setText(
						"There are empty input fields, fill them all out if you want to add a textbook to the inventory");
				return;
			}
			// Check if the input for price and quantity are non-integers
			if ((!q.matches("[0-9]+")) || (!p.matches("[0-9]+"))) {
				textArea.setText("The quantity or price you entered is not an integer");
				return;
			}
			
			// Check if SKU was already added, can't add the same textbook
			for (int i = 0; i < list.size(); i++) {
				if (sku.equals(list.get(i).getSku())) {
					textArea.setText("Textbook is already in the inventory, add a different SKU");
					return;

				}
			}

			// If test cases pass, continue to add the inventory
			quantity = Integer.parseInt(q);
			price = Integer.parseInt(p);
			Video inventory = new Video(sku, title, price, quantity);
			list.add(inventory);
			textArea.setText("Textbook with these parameters: "+ inventory + "was added to the inventory");
			return;

		}
		// Removing Textbook Option
		if (selectedOption.equals("Remove Textbook by SKU")) {
			// Checks if the SKU text field is empty
			if (sku.isEmpty()) {
				textArea.setText("The SKU input is empty, please input a SKU");
				// Removes the SKU if it is within the inventory
			} else {
				for (int i = 0; i < list.size(); i++) {
					if (sku.equals(list.get(i).getSku())) {
						list.remove(i);
						textArea.setText("Textbook was removed from the inventory");
						return;

					}
				}
				// Other case is that the SKU value is valid but it doesn't exist within the
				// inventory

				textArea.setText(
						"The SKU value you inputed doesn't exist within the inventory, please input a SKU value that is within the inventory");
						return;

			}

		}

	}
}
