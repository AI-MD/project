package util;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UUIDTest {
	
	public static void checkSession(HttpSession session, HttpServletResponse response) throws IOException{
		if(session.getAttribute("UID")==null){
			response.sendRedirect("main.do");
		}
	}
	
	
	public static String uuid() {
		UUID uuid = UUID.randomUUID();
		String l=uuid.toString();
		return noMinus(l);
		
	}
	public static String noMinus(String l){
		char[] l2=new char[32];
		char[] cs=l.toCharArray();
		int j=0;
		for(int i=0;i<36;i++){
			if(cs[i]!='-' ){
				l2[j++]=cs[i];
			}
		}
		return	new String(l2);
	}
}
