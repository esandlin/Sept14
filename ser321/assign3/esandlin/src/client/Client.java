package ser321.assign3.esandlin.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * Copyright (c) 2015 Tim Lindquist, Software Engineering, Arizona State
 * University at the Polytechnic campus
 * <p/>
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation version 2 of the License.
 * <p/>
 * This program is distributed in the hope that it will be useful, but without
 * any warranty or fitness for a particular purpose.
 * <p/>
 * Please review the GNU General Public License at:
 * http://www.gnu.org/licenses/gpl-2.0.html see also:
 * https://www.gnu.org/licenses/gpl-faq.html so you are aware of the terms and
 * your rights with regard to this software. Or, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301,USA
 * <p/>
 * Purpose: Sample Java Swing controller class. FolderBrowserGUI constructs the
 * view components for a sample GUI. This class is extends the GUI to provide
 * the control functionality. When the user does a tree node selection, this
 * valueChanged is called, but virtue of being a TreeSelectionListener and
 * adding itself as a listerner. FolderBrowser defines the call-backs for the
 * JButton as well. It contains sample control functions that respond to button
 * clicks and tree selects. This software is meant to run on Debian Wheezy Linux
 * <p/>
 * Ser321 Principles of Distributed Software Systems see
 * http://pooh.poly.asu.edu/Ser321
 * 
 * @author Tim Lindquist (Tim.Lindquist@asu.edu) CIDSE - Software Engineering,
 *         IAFSE, ASU at the Polytechnic campus
 * @file FolderBrowserGUI.java
 * @date July, 2015
 **/

public class Client {

	private static Socket socket;
	private MessageGUI theView;
	private Message theModel;
	
	/**
	 * @param theView
	 * @param theModel
	 */
	public Client(MessageGUI theView, Message theModel) {
		this.theView = theView;
		this.theModel = theModel;
		theView.setDateTextField(theView.getDateTextField());

		/*
		 * Tell the View that when ever the button is clicked to execute the
		 * actionPerformed method in the Listener inner class
		 */
		this.theView.addCypherListener(new Listener());
		this.theView.addDeleteListener(new Listener());
		this.theView.addSendListener(new Listener());
		this.theView.addReplyListener(new Listener());

	}
	
	/**
	 * The Main starts the program
	 * @param args
	 */
	public static void main(String[] args) {
		 try {
	         // The OSName.java
	         System.out.println(System.getProperty("os.name"));
	         System.out.println(InetAddress.getLocalHost().getCanonicalHostName());

	         // The MVC
	         MessageGUI theView = new MessageGUI();
	         Message theModel = new Message();
	         Client theController = new Client(theView, theModel);
	         theView.setVisible(true);

	         String host = "$localhost";
	         int port = 1099;
	         // if (args.length >= 2) {
	         // host = args[0];
	         // port = args[1];
	         // }

	         InetAddress address = InetAddress.getByName(host);
	         socket = new Socket(address, port);

	         // Send the message to the server
	         OutputStream os = socket.getOutputStream();
	         OutputStreamWriter osw = new OutputStreamWriter(os);
	         BufferedWriter bw = new BufferedWriter(osw);

	         // String number = "2";
	         //
	         // String sendMessage = number + "\n";
	         // bw.write(sendMessage);
	         // bw.flush();
	         // System.out.println("Message sent to the server : "+sendMessage);

	         // Get the return message from the server
	         InputStream is = socket.getInputStream();
	         InputStreamReader isr = new InputStreamReader(is);
	         System.out.println("Client obtained remote object reference to" + " the Server");
	         //BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
	         BufferedReader br = new BufferedReader(isr);
	         String message = br.readLine();
	         System.out.println("Message received from the server : " + message);

	         // System.setSecurityManager(new RMISecurityManager());
	         // RMIClientGui rmiclient = new RMIClientGui(host, port);

	     } catch (Exception e) {
	         e.printStackTrace();
	     } finally {
	         // Closing the socket
	         try {
	             socket.close();
	         } catch (Exception e) {
	             e.printStackTrace();
	         }
	     }
	 }

	/**
	 * @author ericsandlin listener class
	 *
	 */
	class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			JTextField to, from, subject, date;

			/*
			 * Surround interactions with the view with a try block just in case
			 */
			try {

				to = theView.getToTextField();
				from = theView.getFromTextField();
				subject = theView.getSubjectTextField();
				date = theView.getDateTextField();

				/*
				 * This is for Deleting nodes
				 */
				if (e.getActionCommand().equals("Delete")) {
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) theView.tree.getSelectionPath()
							.getLastPathComponent();

					if (selectedNode != theView.tree.getModel().getRoot()) {
						DefaultTreeModel model = (DefaultTreeModel) theView.tree.getModel();

						model.removeNodeFromParent(selectedNode);
						model.reload();
					}

					/*
					 * This is for creating new nodes
					 */
				} else if (e.getActionCommand().equals("Reply")) {

					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) theView.tree.getSelectionPath()
							.getLastPathComponent();

					// DefaultMutableTreeNode newNode = new DefaultMutableTreeNode();

					selectedNode.add(new DefaultMutableTreeNode(theView.getSubjectTextField().getText()));

					// reload jtree model
					DefaultTreeModel model = (DefaultTreeModel) theView.tree.getModel();
					model.reload();
					/*
					 * todo
					 */
				} else if (e.getActionCommand().equals("Send Text")) {

					/*
					 * todo
					 */
				} else if (e.getActionCommand().equals("Send Cipher")) {

				}

			} catch (NumberFormatException ex) {
				System.out.println(ex);
				theView.displayErrorMessage("Something went wrong.");
			}
		}
	}
}
