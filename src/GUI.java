import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class GUI extends JFrame {
	private final JList list_1 = new JList();
	private final JList list_2 = new JList();
	private final JComboBox<String> driver = new JComboBox();
	private FileModifier fileMod1;
	private FileModifier fileMod2;
	private final JList list = new JList();
	private final JLabel lblDriver = new JLabel("Driver");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JScrollPane scrollPane_1 = new JScrollPane();
	private String path;
	private final JScrollPane scrollPane_2 = new JScrollPane();
	private boolean ok = true;

	public GUI() {
		initialize();
	}

	private void initialize() {
		setTitle("File Manager");
		setVisible(true);
		setBounds(100, 100, 654, 458);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		scrollPane_2.setBounds(440, 43, 188, 365);

		getContentPane().add(scrollPane_2);
		scrollPane_2.setViewportView(list_2);
		driver.setModel(new DefaultComboBoxModel<String>(new String[] { " ", "c:\\", "d:\\", "e:\\" }));
		driver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (ok) {
					driver.setModel(new DefaultComboBoxModel<String>(new String[] { "c:\\", "d:\\", "e:\\" }));
					ok = false;
				}
				DefaultListModel<String> model = new DefaultListModel<String>();
				DefaultListModel model1 = new DefaultListModel();
				list_1.setModel(model1);
				fileMod1 = new FileModifier(driver.getSelectedItem().toString());

				for (File file : fileMod1.getFiles()) {
					model.addElement(file.getName());
				}
				list.setModel(model);
			}
		});
		driver.setBounds(65, 12, 50, 20);

		getContentPane().add(driver);
		scrollPane_1.setBounds(224, 43, 206, 365);

		getContentPane().add(scrollPane_1);
		list_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultListModel<String> model1 = new DefaultListModel<String>();
				model1.addElement("Name:");
				model1.addElement(fileMod2.getFile(list_1.getSelectedValue().toString()).getName());
				model1.addElement("");
				Date date = new Date(fileMod2.getFile(list_1.getSelectedValue().toString()).lastModified());
				model1.addElement("Last Modified:");
				model1.addElement("" + date);
				list_2.setModel(model1);
				if (e.getClickCount() == 2) {
					path += list_1.getSelectedValue().toString() + "/";
					DefaultListModel<String> model = new DefaultListModel<String>();
					fileMod2 = new FileModifier(path);

					for (File file : fileMod2.getFiles()) {
						model.addElement(file.getName());
					}
					list_1.setModel(model);
				}
			}
		});
		scrollPane_1.setViewportView(list_1);
		scrollPane.setBounds(10, 43, 203, 365);

		getContentPane().add(scrollPane);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				path = driver.getSelectedItem().toString() + list.getSelectedValue().toString() + "/";
				DefaultListModel<String> model = new DefaultListModel<String>();
				fileMod2 = new FileModifier(path);

				for (File file : fileMod2.getFiles()) {
					model.addElement(file.getName());
				}
				list_1.setModel(model);
			}
		});
		scrollPane.setViewportView(list);
		lblDriver.setFont(new Font("Arial", Font.BOLD, 14));
		lblDriver.setBounds(10, 14, 66, 14);

		getContentPane().add(lblDriver);
	}

	public static void main(String[] args) {
		new GUI();
	}
}
