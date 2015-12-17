package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dao.Bang5Dao;
import dao.RecommandDao;
import dao.ReplyDao;
import dao.UserDao;

import sun.usagetracker.UsageTrackerClient;
import util.UUIDTest;
import vo.Bang5VO;
import vo.RecommandVO;
import vo.ReplyVO;
import vo.SearchVO;
import vo.UserVO;

/*	
Bang5Dao_OracleImpl.java
Bang5Dao.java
Bang5VO.java
가지고 온 다음...
 
 */
@Controller
public class CtrlBang {
	
	public CtrlBang(){ }//AOP를 위한 빈 생성자 추가
	
	
	
	private Bang5Dao bangDao = null;
	private UserDao userDao=null;
	private RecommandDao recommandDao=null;
	private ReplyDao replyDao=null;
	
	
	
	public void setBangDao(Bang5Dao bangDao) {
		this.bangDao = bangDao;

	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}



	public void setRecommandDao(RecommandDao recommandDao) {
		this.recommandDao = recommandDao;
	}



	public void setReplyDao(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}
	
	
	
	/*
	public CtrlBang(Bang5Dao bangDao, UserDao userDao,
			RecommandDao recommandDao, ReplyDao replyDao) {
		
		this.bangDao = bangDao;
		this.userDao = userDao;
		this.recommandDao = recommandDao;
		this.replyDao = replyDao;
	}*/



	@RequestMapping("/ping_bang.do")
	@ResponseBody
	public String ping() throws Exception{
		return bangDao.toString();
	}
	
	@RequestMapping("/like.do")
	public String like(HttpServletRequest request,HttpSession session,
			HttpServletResponse response, @RequestParam(value="no") int no) 
			throws Exception {
		
		UUIDTest.checkSession(session, response);
		String uid = (String)session.getAttribute("UID");
		String cp=request.getParameter("cp");
		int r=recommandDao.findbyID(no, uid);
		if(r==0){
			bangDao.updateRecommand(no);
			RecommandVO vo= new RecommandVO();
			vo.setBoardNo(no);
			vo.setUserId(uid);
			recommandDao.add(vo);
			
			return "redirect:/list.do?cp="+cp;
		}
		return "redirect:/list.do?cp="+cp;
	}
	
	@RequestMapping("/list.do")
	public ModelAndView list(HttpServletRequest request, @ModelAttribute SearchVO svo, HttpSession session, HttpServletResponse response,
			Map<String, Object> map, @RequestParam(value="cp" ,required=false) Integer cp)
			
					throws Exception{
		UUIDTest.checkSession(session, response);
		
		HashMap param=new HashMap();
		String field=request.getParameter("field");
		String searchWord=request.getParameter("searchword");
		
		if(cp==null){
			cp=new Integer(1);
		}else{
			searchWord=utf8(searchWord);
		}
		
		
		
		String uid=(String)session.getAttribute("UID");
		List<Bang5VO> ls=new ArrayList<Bang5VO>();
		
		
		int count=0 , blockStart=1, blockSize=5;//초기값
		System.out.println(searchWord);	
		//
		
		
		if(cp>1){//현재 page
			blockStart=((cp-1))*blockSize+1;//시작점
		}
		int blockEnd=blockStart+blockSize-1;//끝지점
		
		
		
		if(searchWord!=null){
			if(field!=null||searchWord!=null){
				param.put("field", field);
				param.put("searchWord", searchWord);
			}
		}
		
		
		if(param.containsKey("field")){
			
			count=bangDao.findSearchCount(param);
			ls=bangDao.findSearch(param,blockStart,blockEnd);
		}else{
			System.out.println("d1");
			count=bangDao.findAllCount();
			ls=bangDao.findAll(blockStart, blockEnd);
		}
		
		
		int np=((count-1)/blockSize)+1;// 페이징 갯수
		
		
		
		map.put("field", field);
		map.put("searchWord", searchWord);
		map.put("cp", cp);
		map.put("np", np);
		map.put("l", ls);
		map.put("count",count);
		map.put("uvo", userDao.findByPk(uid));
		return new ModelAndView("list",map);
	}
	
	@RequestMapping("/update2.do")
	public String update2(HttpServletRequest request,
			HttpSession session,	HttpServletResponse response, @ModelAttribute ReplyVO vo) throws Exception {
			UUIDTest.checkSession(session, response);
			replyDao.updateReply(vo);
			return "redirect:/view.do?no="+vo.getBang_no();
	}
	
	@RequestMapping("/add.do")
	public String add(	HttpServletRequest  request ) throws Exception{
		String value=request.getParameter("key");
		
		System.out.println(value);
		return "add";
	}
	
	public  String utf8( String l ){
		if( l == null || l.equals("") ){
			return null;
		}else{
			try{
				return new String( l.getBytes("8859_1") , 
					"utf-8");
			}
			catch( Exception e ){
				return null;
			}
		}
	}
	@RequestMapping("/add2.do")
	public String add2( @ModelAttribute  Bang5VO pvo ,BindingResult result,
			HttpServletRequest  request, HttpSession session,HttpServletResponse response) 
		throws Exception
	{
			UUIDTest.checkSession(session, response);
			MultipartFile mf=pvo.getOne();
			pvo.setOfn(mf.getOriginalFilename());
			String fsn=UUIDTest.uuid();
			pvo.setFsn(fsn);
			
			byte[] buf=new byte[1024*8];
			int len=0;
			
			OutputStream out =new FileOutputStream("D:\\upload\\"+fsn);
			InputStream in= mf.getInputStream();
			while((len=in.read(buf))!=-1){
				out.write(buf,0,len);
			}
			in.close();
			out.close();
			
			pvo.setClientIp(request.getRemoteAddr());
			bangDao.add( pvo );
			return "redirect:/list.do";
	}
	@RequestMapping("/board_delete.do")
	public String delete(HttpSession session,HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="no") int no) throws Exception {
			UUIDTest.checkSession(session, response);
			bangDao.delete(no);
			String cp=request.getParameter("cp");
			return "redirect:/list.do?cp="+cp;
	}
	
	@RequestMapping("/board_update.do")
	public ModelAndView board_updateForm(HttpSession session,HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value="no") int no) throws Exception {
		UUIDTest.checkSession(session, response);
		return new ModelAndView("board_update","vo", bangDao.findByNo(no));
	}
	
	public String board_update(@ModelAttribute  Bang5VO pvo, HttpSession session,HttpServletRequest request,
		HttpServletResponse response) throws Exception{
		UUIDTest.checkSession(session, response);
		bangDao.updateBoard(pvo);
		String cp=request.getParameter("cp");
		return "redirect:/list.do?cp="+cp;
	}
	
	@RequestMapping("/view.do")
	public ModelAndView view(HttpServletRequest request,Map<String, Object> map,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value="no") int no) 
			throws Exception {
		
		UUIDTest.checkSession(session, response);
		String uid=(String)session.getAttribute("UID");
		Bang5VO	vo=bangDao.findByNo(no);
		if(!vo.getUserId().equals(uid)){
			bangDao.updateCount(no);
		}
		map.put("cp", request.getParameter("cp"));
		map.put("id", uid);
		map.put("bvo", vo);
		map.put("uvo", userDao.findByPk(uid));
		map.put("rls", replyDao.findbyBn(no));
		return new ModelAndView("view",map);
	}
	@RequestMapping("/reply_delete.do")
	public ModelAndView re_delete(HttpServletRequest request,
			HttpServletResponse response,HttpSession session) throws Exception {
		UUIDTest.checkSession(session, response);
		ModelAndView mnv =new ModelAndView();
		
		String no=request.getParameter("no");
		String board_no=request.getParameter("board_no");
		Integer no2=Integer.parseInt(no);
		replyDao.delete(no2);
		mnv.setViewName("redirect:/view.do?no="+board_no);
		return mnv;
	}
	@RequestMapping("/reply_insert.do")
	public ModelAndView reply_insert(HttpSession session,HttpServletRequest request,
			HttpServletResponse response,@ModelAttribute ReplyVO vo) throws Exception {
		UUIDTest.checkSession(session, response);
		ModelAndView mnv =new ModelAndView();
		
		String board_no=request.getParameter("board_no");
		vo.setBang_no(Integer.parseInt(board_no));
		replyDao.add(vo);
		mnv.setViewName("redirect:/view.do?no="+board_no);
		return mnv;
	}
	
	
	
	@RequestMapping("/file_down.do")
	public ModelAndView handleRequest3(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UUIDTest.checkSession(session, response);
		ModelAndView mnv=new ModelAndView();
		String uid = (String)session.getAttribute("UID");
		
		String fsn = request.getParameter("fsn");
		String ofn = request.getParameter("ofn");
		String no = request.getParameter("no");
		
		Exception err = null;
		try{
			File f = new File("D:\\upload\\" + fsn );
			if( !f.exists() ){
				response.setStatus(404);
				return null;
			}

//			int no2 = Integer.parseInt( no );
			
//			int rc = userDao.movePoint( no2, uid );
//			
//			if( rc < 2 ){
//				response.setStatus(500);
//				return null;
//			}
//			else
//			{
				//	파일 다운로드시에 미리 통보하는 MIME TYPE 
				response.setContentType("application/octet-stream");
				response.setContentLength( (int)f.length() );
				response.setHeader("Content-Disposition",
					"attachment;filename=" + ofn );
				
				OutputStream out = response.getOutputStream();
				InputStream in = new FileInputStream( f );
				
				int len = 0;
				byte[] buf = new byte[1024*8];
				while( ( len = in.read( buf ) ) != -1 ){
					out.write( buf, 0, len );
					out.flush();
				}
				in.close();
				out.close();
			//}
		}
		catch( Exception e ){ err = e; }
		
		if( err != null ){
			response.setStatus(500 );
		}
		return null;
	}
}




