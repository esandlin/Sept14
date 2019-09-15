package ser321.assign3.esandlin.client;

/**
 * Copyright (c) 2015 Tim Lindquist,
 * Software Engineering,
 * Arizona State University at the Polytechnic campus
 * <p/>
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation version 2
 * of the License.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but without any warranty or fitness for a particular purpose.
 * <p/>
 * Please review the GNU General Public License at:
 * http://www.gnu.org/licenses/gpl-2.0.html
 * see also: https://www.gnu.org/licenses/gpl-faq.html
 * so you are aware of the terms and your rights with regard to this software.
 * Or, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301,USA
 * <p/>
 * Purpose: Sample Java Swing controller class. FolderBrowserGUI constructs the view components
 * for a sample GUI. This class is extends the GUI to provide the control functionality.
 * When the user does a tree node selection, this valueChanged is called, but virtue of being a
 * TreeSelectionListener and adding itself as a listerner. FolderBrowser defines the call-backs
 * for the JButton as well.
 * It contains sample control functions that respond to button clicks and tree
 * selects.
 * This software is meant to run on Debian Wheezy Linux
 * <p/>
 * Ser321 Principles of Distributed Software Systems
 * see http://pooh.poly.asu.edu/Ser321
 * @author Tim Lindquist (Tim.Lindquist@asu.edu) CIDSE - Software Engineering,
 *                       IAFSE, ASU at the Polytechnic campus
 * @file    FolderBrowserGUI.java
 * @date    July, 2015
 **/

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.awt.Font;
import java.awt.MenuBar;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.BorderLayout;

/**
 * @author ericsandlin
 *
 */
public class MessageGUI extends JFrame implements java.io.Serializable {

	/**
	 * Json serialization
	 */
	private static final long serialVersionUID = -5258674991466487784L;

	private JMenuBar menuBar = new JMenuBar();
	private JMenu file = new JMenu("File");
	private JMenu help = new JMenu("Help");
	private JMenuItem getMail = new JMenu("get mail");
	private JLabel lblMessagesForEric = new JLabel("Messages for Eric Sandlin:");
	private JTextField subjectTextField = new JTextField(10);
	private JLabel subjectLabel = new JLabel("Subject:");
	private JLabel toLabel = new JLabel("To:");
	private JLabel fromLabel = new JLabel("From:");
	private JLabel dateLabel = new JLabel("Date:");
	private JLabel statusLabel = new JLabel("Status:");
	private JTextField toTextField = new JTextField(10);
	private JButton replyButton = new JButton("Reply");
	private JButton sendButton = new JButton("Send Text");
	private JButton deleteButton = new JButton("Delete");
	private JButton cypherButton = new JButton("Send Cypher");
	private JTextField dateTextField = new JTextField(10);
	private JTextField fromTextField = new JTextField(10);
	private JTextField messageTextField = new JTextField();
	private JTextField statusTextField = new JTextField();
	private final JMenuBar menuBar_1 = new JMenuBar();
	private final JMenu fileMenu = new JMenu("File");
	private final JMenu helpMenu = new JMenu("Help");
	private final JMenuItem getMailMenuItem = new JMenuItem("Get Mail");

	DefaultMutableTreeNode parentNode;
	DefaultMutableTreeNode node;
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
	DefaultTreeModel model = new DefaultTreeModel(root);
	JTree tree = new JTree(model);
	JScrollPane scrollPane = new JScrollPane(tree);

	MessageGUI() {

		JPanel Panel = new JPanel();

		this.setTitle("Eric Sandlins Message");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(613, 336);
		getContentPane().add(menuBar_1, BorderLayout.NORTH);

		menuBar_1.add(fileMenu);
		fileMenu.add(getMailMenuItem);
		menuBar_1.add(helpMenu);
		Panel.setLayout(null);
		subjectTextField.setBounds(275, 93, 116, 22);
		Panel.add(subjectTextField);
		subjectLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		subjectLabel.setBounds(211, 95, 52, 16);
		Panel.add(subjectLabel);
		statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		statusLabel.setBounds(233, 231, 57, 33);
		Panel.add(statusLabel);
		toLabel.setBounds(243, 46, 20, 16);
		Panel.add(toLabel);
		fromLabel.setBounds(403, 47, 35, 16);
		Panel.add(fromLabel);
		dateLabel.setBounds(403, 96, 35, 16);
		Panel.add(dateLabel);
		messageTextField.setBounds(194, 127, 325, 81);
		Panel.add(messageTextField);
		messageTextField.setColumns(10);
		toTextField.setBounds(275, 43, 116, 22);
		Panel.add(toTextField);
		fromTextField.setBounds(450, 44, 116, 22);
		Panel.add(fromTextField);
		replyButton.setBounds(205, 5, 85, 25);
		Panel.add(replyButton);
		sendButton.setBounds(299, 5, 104, 25);
		Panel.add(sendButton);
		deleteButton.setBounds(108, 5, 85, 25);
		Panel.add(deleteButton);
		cypherButton.setBounds(415, 5, 116, 25);
		Panel.add(cypherButton);
		dateTextField.setBounds(450, 93, 116, 22);
		Panel.add(dateTextField);
		statusTextField.setColumns(10);
		statusTextField.setBounds(293, 231, 226, 33);
		Panel.add(statusTextField);
		lblMessagesForEric.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMessagesForEric.setBounds(12, 47, 181, 16);
		Panel.add(lblMessagesForEric);

		parentNode = (DefaultMutableTreeNode) model.getRoot();
		node = new DefaultMutableTreeNode("Eric Sandlin: Mon 16 Sept 22:54 1894");
		addNodeToDefaultTreeModel(model, parentNode, node);

		parentNode = node;
		node = new DefaultMutableTreeNode("Maybe I need help.");
		addNodeToDefaultTreeModel(model, parentNode, node);

		parentNode = (DefaultMutableTreeNode) model.getRoot();
		node = new DefaultMutableTreeNode("Eric Sandlin: Mon 16 Sept 22:54 1999");
		addNodeToDefaultTreeModel(model, parentNode, node);

		parentNode = node;
		node = new DefaultMutableTreeNode("Get your homework done.");
		addNodeToDefaultTreeModel(model, parentNode, node);

		parentNode = (DefaultMutableTreeNode) model.getRoot();
		node = new DefaultMutableTreeNode("Eric Sandlin: Mon 16 Sept 22:54 1894");
		addNodeToDefaultTreeModel(model, parentNode, node);

		parentNode = node;
		node = new DefaultMutableTreeNode("Don't forget to study.");
		addNodeToDefaultTreeModel(model, parentNode, node);

		parentNode = (DefaultMutableTreeNode) model.getRoot();
		node = new DefaultMutableTreeNode("Eric Sandlin: Mon 16 Sept 21:24 2012");
		addNodeToDefaultTreeModel(model, parentNode, node);

		parentNode = node;
		node = new DefaultMutableTreeNode("I can do this, yes I can.");
		addNodeToDefaultTreeModel(model, parentNode, node);

		parentNode = node;
		node = new DefaultMutableTreeNode("I'm starting to get this class");
		addNodeToDefaultTreeModel(model, parentNode, node);

		parentNode = node;
		node = new DefaultMutableTreeNode("I can do this, yes I can.");
		addNodeToDefaultTreeModel(model, parentNode, node);

		tree.setRootVisible(false);
		tree.setBounds(12, 67, 170, 219);
		Panel.add(tree);

		getContentPane().add(Panel);
		// End of setting up the components --------
	}

	/**
	 * @param treeModel
	 * @param parentNode
	 * @param node
	 */
	private static void addNodeToDefaultTreeModel(DefaultTreeModel treeModel, DefaultMutableTreeNode parentNode,
			DefaultMutableTreeNode node) {

		treeModel.insertNodeInto(node, parentNode, parentNode.getChildCount());

		if (parentNode == treeModel.getRoot()) {
			treeModel.nodeStructureChanged((TreeNode) treeModel.getRoot());
		}
	}

	/**
	 * @param menuBar
	 */
	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	/**
	 * @return
	 */
	protected JTextField getSubjectTextField() {
		return subjectTextField;
	}

	/**
	 * @param subjectTextField
	 */
	protected void setSubjectTextField(JTextField subjectTextField) {
		this.subjectTextField = subjectTextField;
	}

	/**
	 * @return
	 */
	protected JTextField getToTextField() {
		return toTextField;
	}

	/**
	 * @param toTextField
	 */
	protected void setToTextField(JTextField toTextField) {
		this.toTextField = toTextField;
	}

	/**
	 * @return
	 */
	protected JTextField getDateTextField() {
		DateFormat format = new SimpleDateFormat("MM/DD/YYYY");
		JFormattedTextField dateTextField = new JFormattedTextField(format);
		return dateTextField;
	}

	/**
	 * @param dateTextField
	 */
	protected void setDateTextField(JTextField dateTextField) {
		this.dateTextField = dateTextField;
	}

	/**
	 * @return
	 */
	protected JTextField getFromTextField() {
		return fromTextField;
	}

	/**
	 * @param fromTextField
	 */
	protected void setFromTextField(JTextField fromTextField) {
		this.fromTextField = fromTextField;
	}

	/**
	 * @return
	 */
	protected JTextField getMessageTextField() {
		return messageTextField;
	}

	/**
	 * @param messageTextField
	 */
	protected void setMessageTextField(JTextField messageTextField) {
		this.messageTextField = messageTextField;
	}

	/**
	 * @return
	 */
	protected JTextField getStatusTextField() {
		return statusTextField;
	}

	/**
	 * @param statusTextField
	 */
	protected void setStatusTextField(JTextField statusTextField) {
		this.statusTextField = statusTextField;
	}

	/**
	 * If the calculateButton is clicked execute a method in the Controller named
	 * actionPerformed
	 */
	void addReplyListener(ActionListener listenForReplyButton) {
		replyButton.addActionListener(listenForReplyButton);
	}

	/**
	 * If the calculateButton is clicked execute a method in the Controller named
	 * actionPerformed
	 */
	void addDeleteListener(ActionListener listenForDeleteButton) {
		deleteButton.addActionListener(listenForDeleteButton);
	}

	/**
	 * If the calculateButton is clicked execute a method in the Controller named
	 * actionPerformed
	 */
	void addSendListener(ActionListener listenForSendButton) {
		sendButton.addActionListener(listenForSendButton);
	}

	/**
	 * If the calculateButton is clicked execute a method in the Controller named
	 * actionPerformed
	 */
	void addCypherListener(ActionListener listenForCypherButton) {
		cypherButton.addActionListener(listenForCypherButton);
	}

	/**
	 * Open a popup that contains the error message passed
	 * 
	 * @param errorMessage
	 */
	void displayErrorMessage(String errorMessage) {

		JOptionPane.showMessageDialog(this, errorMessage);

	}
}
