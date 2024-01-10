package com.basic.nio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * zero copy "零拷贝"
 */
public class ZeroCopyServer {
    ServerSocketChannel listener = null;

    protected void mySetup() {
        InetSocketAddress listenAddr = new InetSocketAddress(9026);
        try {
            listener = ServerSocketChannel.open();
            ServerSocket ss = listener.socket();
            ss.setReuseAddress(true);
            ss.bind(listenAddr);
            System.out.println("Listening on port : " + listenAddr.toString());
        } catch (IOException e) {
            System.out.println("Failed to bind, is port : " + listenAddr.toString() + " already in use ? Error Msg : "
                    + e.getMessage());
            e.printStackTrace();
        }

    }

    private void readData() {
        ByteBuffer dst = ByteBuffer.allocate(4096);
        FileOutputStream fileOutputStream = null;
        try {
            while (true) {
                SocketChannel conn = listener.accept();
                System.out.println("Accepted : " + conn);
                conn.configureBlocking(true);
                // 打开文件输出流
                int nread = 0;
                while (nread != -1) {
                    if(fileOutputStream == null) {
                        fileOutputStream = new FileOutputStream(new File("src/main/java/com/basic/nio/ztest-copy.txt"));
                    }
                    try {
                        nread = conn.read(dst);
                        // 获取字节数组
                        byte[] bytes = new byte[dst.remaining()];
                        dst.get(bytes);
                        // 将字节数组写入文件
                        fileOutputStream.write(bytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                        conn.socket().close();
                        nread = -1;
                    }
                    dst.rewind();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fileOutputStream) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ZeroCopyServer dns = new ZeroCopyServer();
        dns.mySetup();
        dns.readData();
    }
}
