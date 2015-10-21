package cn.itcast.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.entity.DinnerTable;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.IDinnerTableService;
/**
 * ҳ�棺boardList.jsp
 * ClassName: DinnerTableServlet
 * @Description: TODO
 * @author Administrator
 * @date 2015��10��21��
 */
public class DinnerTableServlet extends HttpServlet {
	private IDinnerTableService service = BeanFactory.getInstance("dinnerTableService", IDinnerTableService.class);
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//servlet������ʱ���ͳ�ʼ�������е����Ӷ���ѯ���������뵽servlet�����ĵġ�tabel����
		List<DinnerTable> list = service.query();
		config.getServletContext().setAttribute("table", list);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if("add".equals(method)){
			add(request,response);
		}else if("list".equals(method)){
			list(request,response);
		}else if("up1".equals(method)){
			update(request,response);
		}else if("delete".equals(method)){
			delete(request,response);
		}else if("search".equals(method)){
			search(request,response);
		}
		
	}
	//�ؼ��ֲ�ѯ
	private void search(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		if(keyword!=null){
			//����service�ĸ��ݹؼ��ֲ�ѯ
			List<DinnerTable> list = service.query(keyword);
			//�ѹؼ��ֲ�ѯ�����Ľ�����浽list������
			request.setAttribute("list",list);
			//�ַ��ص�/sys/board/boardList.jsp�������ҳ����е��ã���ʾ��ʹ�ùؼ��ֲ�ѯ�����Ľ��
			request.getRequestDispatcher("/sys/board/boardList.jsp").forward(request, response);
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		//��boardList.jspҳ����ɾ����ť������get������������������Ǹ����ӵ�id������idɾ������
		String id = request.getParameter("id");
		service.delete(Integer.parseInt(id));
		//ɾ��֮��������ʾ�µ�ҳ��
		list(request, response);
		
	}
	//boardList.jspҳ���е��˶���ť���ı�����Ԥ��״̬
	private void update(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
		String id = request.getParameter("id");
		service.changeState(Integer.parseInt(id));
		list(request, response);
	}
	//��ѯ��ȫ������
	private void list(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{

		List<DinnerTable> list = service.query();
		request.setAttribute("list",list);
		
		//�������б�浽context�ﴫ��ǰ̨��ʾ  ������������servlet�еõ����������
		request.getServletContext().setAttribute("table", list);
		
		request.getRequestDispatcher("/sys/board/boardList.jsp").forward(request, response);
	}
	
	//�������  saveBoard.jsp��Ҳҳ�淢������tableName������
	private void add(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String tableName = request.getParameter("tableName");
		if(tableName!=null){
			DinnerTable dt = new DinnerTable();
			dt.setTableName(tableName);
			service.add(dt);
			list(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
