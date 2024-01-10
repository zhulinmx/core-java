package com.basic.nio;

/**
 *
 * NIO（Non-blocking I/O）：是一种同步非阻塞的I/O模型，也是I/O多路复用的基础，已经被越来越多地应用到大型应用服务器，成为解决高并发与大量连接、I/O处理问题的有效方式。
 *
 * 事件驱动模型
 * 避免多线程
 * 单线程处理多任务
 * 非阻塞I/O，I/O读写不再阻塞，而是返回0
 * 基于block的传输，通常比基于流的传输更高效
 * 更高级的IO函数，zero-copy
 * IO【多路复用】大大提高了Java网络应用的可伸缩性和实用性
 *
 * 相关：
 * https://www.cnblogs.com/mikechenshare/p/16587635.html
 *
 * Java NIO 由以下几个核心部分组成：
 * Channels
 * Buffers
 * Selectors
 *
 *                         ｜------>Channel------>Buffer
 *                         ｜
 *  Thread------->Selector |------>|Channel------>Buffer
 *                         |
 *                         ｜------>Channel------>Buffer
 *
 * 主要Channel的实现（这些通道涵盖了UDP 和 TCP 网络IO，以及文件IO）：
 *
 * FileChannel
 * DatagramChannel
 * SocketChannel
 * ServerSocketChannel
 *
 * 关键的Buffer实现：
 * ByteBuffer
 * CharBuffer
 * DoubleBuffer
 * FloatBuffer
 * IntBuffer
 * LongBuffer
 * ShortBuffer
 * 这些Buffer覆盖了你能通过IO发送的基本数据类型：byte, short, int, long, float, double 和 char。
 *
 * 还有个【MappedByteBuffer】，则是存放在堆外的直接内存中，可以映射到文件。
 *
 * 
 */
public class IntroductionNIO {

}
