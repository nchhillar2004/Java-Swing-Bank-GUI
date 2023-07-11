package bank.management.system;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.JProgressBar;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import java.awt.Canvas;
import javax.swing.SwingConstants;
import java.awt.Color;

public class main {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
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
	public main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JList list = new JList();
		frame.getContentPane().add(list);
		
		JTree tree = new JTree();
		tree.setBorder(new BevelBorder(BevelBorder.LOWERED, UIManager.getColor("textHighlight"), null, null, null));
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("JTree") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("colors");
						node_1.add(new DefaultMutableTreeNode("blue"));
						node_1.add(new DefaultMutableTreeNode("violet"));
						node_1.add(new DefaultMutableTreeNode("red"));
						node_1.add(new DefaultMutableTreeNode("yellow"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("sports");
						node_1.add(new DefaultMutableTreeNode("basketball"));
						node_1.add(new DefaultMutableTreeNode("soccer"));
						node_1.add(new DefaultMutableTreeNode("football"));
						node_1.add(new DefaultMutableTreeNode("hockey"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("food");
						node_1.add(new DefaultMutableTreeNode("hot dogs"));
						node_1.add(new DefaultMutableTreeNode("pizza"));
						node_1.add(new DefaultMutableTreeNode("ravioli"));
						node_1.add(new DefaultMutableTreeNode("bananas"));
					add(node_1);
				}
			}
		));
		tree.setShowsRootHandles(true);
		frame.getContentPane().add(tree);
		
		JEditorPane editorPane = new JEditorPane();
		frame.getContentPane().add(editorPane);
		
		JSlider slider = new JSlider();
		slider.setToolTipText("vh");
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		frame.getContentPane().add(slider);
		
		JSeparator separator = new JSeparator();
		frame.getContentPane().add(separator);
		
		table = new JTable();
		frame.getContentPane().add(table);
		
		JList list_1 = new JList();
		frame.getContentPane().add(list_1);
		
		Canvas canvas = new Canvas();
		frame.getContentPane().add(canvas);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(UIManager.getColor("textHighlight"));
		progressBar.setBackground(UIManager.getColor("menuPressedItemB"));
		progressBar.setStringPainted(true);
		progressBar.setValue(100);
		frame.getContentPane().add(progressBar);
		
		JLabel lblNewLabel = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel);
		
		JFormattedTextField formattedTextField = new JFormattedTextField(20);
		formattedTextField.setForeground(new Color(0, 0, 0));
		formattedTextField.setEditable(false);
		formattedTextField.setToolTipText("1");
		formattedTextField.setText("+");
		frame.getContentPane().add(formattedTextField);
	}

}
