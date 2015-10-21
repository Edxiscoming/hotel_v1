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
 * 页面：boardList.jsp
 * ClassName: DinnerTableServlet
 * @Description: TODO
 * @author Administrator
 * @date 2015年10月21日
 */
public class DinnerTableServlet extends HttpServlet {
	private IDinnerTableService service = BeanFactory.getInstance("dinnerTableService", IDinnerTableService.class);
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//servlet刚启动时，就初始化把所有的桌子都查询出来，加入到servlet上下文的“tabel”中
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
	//关键字查询
	private void search(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		if(keyword!=null){
			//调用service的根据关键字查询
			List<DinnerTable> list = service.query(keyword);
			//把关键字查询出来的结果保存到list属性中
			request.setAttribute("list",list);
			//又发回到/sys/board/boardList.jsp，在这个页面进行调用，显示出使用关键字查询出来的结果
			request.getRequestDispatcher("/sys/board/boardList.jsp").forward(request, response);
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		//在boardList.jsp页面点击删除按钮，根据get方法，传过来点击的那个桌子的id，根据id删除桌子
		String id = request.getParameter("id");
		service.delete(Integer.parseInt(id));
		//删除之后马上显示新的页面
		list(request, response);
		
	}
	//boardList.jsp页面中德退订按钮，改变它的预定状态
	private void update(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
		String id = request.getParameter("id");
		service.changeState(Integer.parseInt(id));
		list(request, response);
	}
	//查询出全部数据
	private void list(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{

		List<DinnerTable> list = service.query();
		request.setAttribute("list",list);
		
		//将餐桌列表存到context里传到前台显示  可以在其他的servlet中得到这个餐桌表
		request.getServletContext().setAttribute("table", list);
		
		request.getRequestDispatcher("/sys/board/boardList.jsp").forward(request, response);
	}
	
	//添加桌子  saveBoard.jsp这也页面发送桌子tableName到这里
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
