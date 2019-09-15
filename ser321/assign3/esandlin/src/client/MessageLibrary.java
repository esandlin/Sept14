package ser321.assign3.esandlin.client;

public interface MessageLibrary{

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
