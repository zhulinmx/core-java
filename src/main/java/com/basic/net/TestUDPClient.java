package com.basic.net;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.*;

public class TestUDPClient {
    public static void main(String args[]) throws Exception {
        String[] strs = {"Hello", "在", "吗"};
        DatagramSocket ds = new DatagramSocket(9999);
        for (String str : strs) {
            byte[] buf = str.getBytes();
            DatagramPacket dp = new DatagramPacket(buf, buf.length, new InetSocketAddress("127.0.0.1", 5678));
            ds.send(dp);
            Thread.sleep(1000);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeChars("A");

        byte[] buf = baos.toByteArray();
        DatagramPacket dp = new DatagramPacket(buf, buf.length,
                new InetSocketAddress("127.0.0.1", 5678)
        );
        ds.send(dp);
        ds.close();
    }
}