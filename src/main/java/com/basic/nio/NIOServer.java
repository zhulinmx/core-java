package com.basic.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * 从 I/O 设备读取数据时：
 *
 * 1）应用程序调用通道 Channel 的 read() 方法；
 * 2）通道往缓冲区 Buffer 中填入 I/O 设备中的数据，填充完成之后返回；
 * 3）应用程序从缓冲区 Buffer 中获取数据。
 *
 * 往 I/O 设备写数据时：
 * 1）应用程序往缓冲区 Buffer 中填入要写到 I/O 设备中的数据；
 * 2）调用通道 Channel 的 write() 方法，通道将数据传输至 I/O 设备。
 *
 * */

public class NIOServer {

    public static void main(String[] args) throws  Exception{
        //创建ServerSocketChannel，-->> ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(5555);
        serverSocketChannel.socket().bind(inetSocketAddress);
        serverSocketChannel.configureBlocking(false); //设置成非阻塞

        //开启selector,并注册accept事件
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while(true) {
            selector.select(2000);  //监听所有通道
            //遍历selectionKeys
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if(key.isAcceptable()) {  //处理连接事件
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);  //设置为非阻塞
                    System.out.println("client:" + socketChannel.getLocalAddress() + " is connect");
                    socketChannel.register(selector, SelectionKey.OP_READ); //注册客户端读取事件到selector
                } else if (key.isReadable()) {  //处理读取事件
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    SocketChannel channel = (SocketChannel) key.channel();
                    channel.read(byteBuffer);
                    System.out.println("client:" + channel.getLocalAddress() + " send " + new String(byteBuffer.array()));
                }
                iterator.remove();  //事件处理完毕，要记得清除
            }
        }

    }
}
