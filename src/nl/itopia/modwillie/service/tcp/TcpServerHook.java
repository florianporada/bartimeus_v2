/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.itopia.modwillie.service.tcp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A simple utility class for hooking into the TCP server and sending messages using it.
 * @author jimj316
 */
public class TcpServerHook
{
    private static final String PIPE_NAME = "/tmp/bartimeusfifo";

    private static RandomAccessFile pipe = null;

    /**
     * Sends a message to all the TCP server's connected clients.
     * @param message the message to send, as a UTF8 string.
     * @return true if the message reached the server correctly; false if not (most likely, the server is not running)
     */
    public static boolean sendMessage(String message)
    {
        if (pipe == null)
        {
            try
            {
                pipe=new RandomAccessFile( PIPE_NAME, "rw");
            } catch (FileNotFoundException ex)
            {
                Logger.getLogger(TcpServerHook.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }

        try
        {
            pipe.writeUTF(message+"\n");
            return true;
        } catch (IOException ex)
        {
            Logger.getLogger(TcpServerHook.class.getName()).log(Level.SEVERE, null, ex);
            pipe = null;
            return false;
        }

    }
}
