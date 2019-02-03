package nioDemos.bio;

import nioDemos.commons.ServerInfo;
import sun.misc.Cleaner;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class EchoServerHandle  implements AutoCloseable{
    private ServerSocket serverSocket;

    public EchoServerHandle() throws Exception {
        this.serverSocket = new ServerSocket(ServerInfo.PORT);
        System.out.println("ECHO服務端已經啓動，該服務在"+ServerInfo.PORT+"端口監聽");
        this.clientConnect();
    }

    private void clientConnect() throws IOException {
        boolean serverFlag = true;
        while (serverFlag) {
            Socket client = this.serverSocket.accept();
            Thread clientThread = new Thread(() -> {
                try {
                    //  服务器端输入为客户端输出
                    Scanner scanner = new Scanner(client.getInputStream());
                    //服务器端的输出为客户端的输入
                    PrintStream out = new PrintStream(client.getOutputStream());
                    //设置分隔符
                    scanner.useDelimiter("\n");
                    while (serverFlag) {
                        if (scanner.hasNext()) {
                            String next = scanner.next();
                            if ("exit".equalsIgnoreCase(next)) {
                                //一定要提供一个换行机制,否则scanner不好读取
                                out.println("echo bye bye....kiss");
                            } else {
                                out.println("echo" + next);
                                System.out.println(next);
                            }
                        }
                    }
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            clientThread.start();
        }
    }

    @Override
    public void close() throws Exception {
        this.serverSocket.close();
    }
}

/**
 * 实现服务端的编写开发,采用bio(同步阻塞)实现开发的基础结构
 */
public class EchoServer {
    public static void main(String[] args) throws Exception {
        new EchoServerHandle();


    }
}
