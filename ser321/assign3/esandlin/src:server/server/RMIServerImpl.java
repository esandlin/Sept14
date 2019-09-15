package ser321.assign3.esandlin.server;

import java.rmi.server.*;
import java.rmi.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Set;
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
import ser321.assign3.esandlin.client.Client;
import ser321.assign3.esandlin.client.Message;
import ser321.assign3.esandlin.client.MessageGUI;

/**
 * Copyright (c) 2019 Tim Lindquist, Software Engineering, Arizona State
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
 * Purpose: demonstrate using the RMI API Implementation of employee server -
 * create a remote server object (with a couple of employees). Register the
 * remote server object with the rmi registry.
 * <p/>
 * Ser321 Principles of Distributed Software Systems
 * 
 * @see <a href="http://pooh.poly.asu.edu/Ser321">Ser321 Home Page</a>
 * @author Tim Lindquist (Tim.Lindquist@asu.edu) CIDSE - Software Engineering
 *         Ira Fulton Schools of Engineering, ASU Polytechnic
 * @date August, 2019
 * @license See above
 */
class RMIServerImpl extends UnicastRemoteObject implements RMIServer {

    private static final long serialVersionUID = -1086546752171984183L;
    /*
     * protected Emp empList[] = {new Emp("Bob", 100), new Emp("Sue", 200), new
     * Emp("Jen", 300), new Emp("Tom", 400)};
     */
    private static Socket socket;
    protected Hashtable<String, ServerClient> empList;
    protected static int empNo;

    public RMIServerImpl() throws RemoteException {
        empNo = 0;
        try {
            File inFile = new File("client.ser");
            if (inFile.exists()) {
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(inFile));
                empList = (Hashtable) is.readObject();
            } else {
                empList = new Hashtable<String, ServerClient>();
                empList.put("Bob.Marley", new ServerClient("Bob.Marley", 100));
                empList.put("Dick.Smothers", new ServerClient("Dick.Smothers", 200));
                empList.put("Tom.Smothers", new ServerClient("Tom.Smothers", 300));
                empList.put("Jen.Jenkins", new ServerClient("Jen.Jenkins", 400));
            }
        } catch (Exception e) {
            System.out.println("exception initializing employee store " + e.getMessage());
        }
    }

    public ServerClient getEmp(int id) throws RemoteException {
        ServerClient ret = null;
        for (Enumeration<ServerClient> e = empList.elements(); e.hasMoreElements();) {
            ret = (ServerClient) e.nextElement();
            if (ret.getId() == id) {
                System.out.println("Completing request for client " + id);
                break;
            }
        }
        return ret;
    }

    public ServerClient addEmp(String name) throws RemoteException {
        ServerClient emp = new ServerClient("Already Registered", -1);
        if (!empList.keySet().contains(name) && !name.equals("")) {
            emp = new ServerClient(name, empNo++);
            empList.put(name, emp);
            try {
                File outFile = new File("employees.ser");
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(outFile));
                os.writeObject(empList);
            } catch (Exception e) {
                System.out.println("exception initializing employee store " + e.getMessage());
            }
        }
        return emp;
    }

    public String[] getNames() throws RemoteException {
        String[] ret = ((Set<String>) empList.keySet()).toArray(new String[] {});
        return ret;
    }

    public static void main(String args[]) {
        try {
            String host = "localhost";
            int port = 1099;
//            if (args.length >= 2) {
//                hostId = args[0];
//                regPort = args[1];
//            }
            System.out.println("Server Started and listening to the port 1099");
            while(true)
            {
                //Reading the message from the client
                socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String number = br.readLine();
                System.out.println("Message received from client is "+number);
 
                //Multiplying the number by 2 and forming the return message
                String returnMessage;
                try
                {
                    int numberInIntFormat = Integer.parseInt(number);
                    int returnValue = numberInIntFormat*2;
                    returnMessage = String.valueOf(returnValue) + "\n";
                }
                catch(NumberFormatException e)
                {
                    //Input was not a number. Sending proper message back to client.
                    returnMessage = "Please send a proper number\n";
                }
 
                //Sending the response back to the client.
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(returnMessage);
                System.out.println("Message sent to the client is "+returnMessage);
                bw.flush();
            }
            
            RMIServer obj = new RMIServerImpl();
            Naming.rebind("rmi://" + host + ":" + port + "/ClientServer", obj);
            System.out.println("Server bound in registry as: " + "rmi://" + host + ":" + port + "/ClientServer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
