package q1;

import java.io.Serializable;

/**
 * Encapsulates data for the connection between the Server and the Client.
 */
public class Data implements Serializable {
    private String[] clientList;
    private String msg;
    private final String sender;
    private final MsgType msgType;

    /**
     * Enum that represents different types of messages.
     */
    public enum MsgType {
        AddClientToServer,
        RemoveClientFromServer,
        NewChatBoxMessage,
        UpdatedClientList,
        None

    }

    /**
     * Three Constructors.
     */
    public Data(String sender, MsgType msgType, String[] participantList) {
        this.sender = sender;
        this.msgType = msgType;
        this.clientList = participantList;
    }

    public Data(String sender, MsgType msgType) {
        this.sender = sender;
        this.msgType = msgType;
    }

    public Data(String sender, String msg, MsgType msgType) {
        this.sender = sender;
        this.msg = msg;
        this.msgType = msgType;
    }

    /**
     * Getters
     */
    public MsgType getMsgType() {
        if (MsgType.AddClientToServer.equals(msgType)) return MsgType.AddClientToServer;
        else if (MsgType.RemoveClientFromServer.equals(msgType)) return MsgType.RemoveClientFromServer;
        else if (MsgType.NewChatBoxMessage.equals(msgType)) return MsgType.NewChatBoxMessage;
        else if (MsgType.UpdatedClientList.equals(msgType)) return MsgType.UpdatedClientList;
        return MsgType.None;
    }

    public String getSender() {
        return sender;
    }

    public String getMsg() {
        return msg;
    }

    public String[] getClientList() {
        return clientList.clone();
    }
}
