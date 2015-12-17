package controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vo.UserVO;


import dao.UserDao;




@Controller
public class CtrlLogin 
{
private UserDao userDao = null;
	

	public CtrlLogin( UserDao b){
		userDao = b;
		
	}
	
	
	@RequestMapping("/main.do")
	public String main() throws Exception {
		
		return "main";
	}
	
	@RequestMapping("/login.do")
	public String login(RedirectAttributes rec,@ModelAttribute UserVO user,HttpSession session) throws Exception {
		/*
		 * �Ʒ� ������ valid Ŭ������ ���� �и��غ���. (@Valid) 
		 */
		
		String userId=user.getUserId();
		String passwd=user.getPasswd();
		if(userId==null||"".equals(userId)||
				passwd==null||"".equals(passwd))
		{
			rec.addFlashAttribute("error", "���̵�� ����� ���������� �Էµ��� �ʾҽ��ϴ�.");	
			return "redirect:/main.do";
		}
		UserVO fvo=userDao.findByPk(userId);
		if(fvo==null){//�ش� ID ���� 
			rec.addFlashAttribute("error", "�ش� ���̵� �����ϴ�.");
			return "redirect:/main.do";
		}else{
			if(passwd.equals(fvo.getPasswd())){//�α��� ���� 
				session.setAttribute("UID", userId);
				return "redirect:/list.do";
			}else{
				rec.addFlashAttribute("error", "��й�ȣ�� Ʋ�Ƚ��ϴ�.");
				return "redirect:/main.do";
			}
		}
	}
	
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) throws Exception {
		session.removeAttribute("UID");
		return "redirect:main.do";
	}
	
	@RequestMapping("/reg.do")
	public String reg() throws Exception {
		
		return "reg";
	}
	@RequestMapping("/doublecheck.do")
	@ResponseBody
	public String check(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id=request.getParameter("id");
		System.out.println(id);
		UserVO vo= userDao.findByPk(id);
		String check="";
		if(vo==null){
			check="1";
		}else{
			check="0";
		}
		return check;
	}
	
	@RequestMapping("/reg_add.do")
	public String reg_add(@ModelAttribute UserVO user) throws Exception {

		if(user.getUserId()==null||"".equals(user.getUserId())
			||user.getPasswd()==null||"".equals(user.getPasswd())){
			return "redirect:main.do";
		}
		userDao.add(user);
		return "redirect:/main.do";
	}
	
}
