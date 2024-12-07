package main;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class LoginServer {

	public void run(ServerSocket serverSocket) {
		 
		 System.out.println("MJU Server Start!");

         while (true) {
             try (Socket clientSocket = serverSocket.accept();
                  BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                  PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                 String requestType = in.readLine();

                 if ("LOGIN".equals(requestType)) {
                     String id = in.readLine();
                     String password = in.readLine();

                     String userName = authenticate(id, password);
                     if (userName != null) {
                         out.println(userName); // 로그인 성공 시 사용자 이름 전송
                     } else {
                         out.println(""); // 로그인 실패 시 빈 문자열 전송
                     }
                 } else if ("SIGNUP".equals(requestType)) {
                     String name = in.readLine();
                     String id = in.readLine();
                     String password = in.readLine();

                     boolean success = registerAccount(name, id, password);
                     if (success) {
                         out.println("회원가입 성공");
                     } else {
                         out.println("회원가입 실패");
                     }
                 }

             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
	        
	}
	//로그인 
	private static String authenticate(String id, String password) {
    	try (BufferedReader br = new BufferedReader(new FileReader("data/Account.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userInfo = line.split(" ");
                if (userInfo.length == 3) {
                    String storedId = userInfo[0];
                    String storedPassword = userInfo[1];
                    String userName = userInfo[2];
                    if (id.equals(storedId) && password.equals(storedPassword)) {
                        return userName; // 로그인 성공 시 사용자 이름 반환
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // 로그인 실패 시 null 반환
    }
	//회원가입
	private static boolean registerAccount(String name, String id, String password) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/Account.txt", true))) {
            bw.write(id + " " + password + " " + name);
            bw.newLine();
            bw.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
