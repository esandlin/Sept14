package assign3.esandlin.src.server;

import java.rmi.*;

import assign3.esandlin.src.client.Message;

/**
 * Copyright (c) 2019 Tim Lindquist,
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
 * Purpose: demonstrate using the RMI API
 * remote interface for the employee server.
 * <p/>
 * Ser321 Principles of Distributed Software Systems
 * @see <a href="http://pooh.poly.asu.edu/Ser321">Ser321 Home Page</a>
 * @author Tim Lindquist (Tim.Lindquist@asu.edu) CIDSE - Software Engineering
 *                       Ira Fulton Schools of Engineering, ASU Polytechnic
 * @date    January, 2019
 * @license See above
 */

public interface RMIServer extends Remote {
   public ServerClient getEmp(int id) throws RemoteException;
   public ServerClient addEmp(String name) throws RemoteException;
   public String[] getNames() throws RemoteException;
   
// do not implement sendClearText in Assign2.
   public boolean sendClearText(Message aMessage, String fromUser);

   // do not implement sendCipher in Assign2.
   public boolean sendCipher(Message aMessage, String fromUser);

   // getMessageFromHeaders returns a string array of message headers being sent to toAUserName.
   // Headers returned are of the form: (from user name @ server and message date)
   // e.g., a message from J Buffett with header: Jimmy.Buffet  Tue 18 Dec 5:32:29 2018
   public String[] getMessageFromHeaders(String toAUserName);

   // getMessage returns the Message having the corresponding header. Assume headers are unique.
   // As above, the header has includes (from user name - server and message date)
   public Message getMessage(String header);

   // deletes the message having the header (from user name - server and message date)
   public boolean deleteMessage(String header, String toAUserName);

}
