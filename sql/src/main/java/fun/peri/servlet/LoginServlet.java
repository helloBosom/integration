package fun.peri.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        execute(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        execute(request, response);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
         * enumeration public static <T> Enumeration<T>
         * enumeration(Collection<T> c)返回一个指定 collection 上的枚举。这提供了与遗留 API
         * 的互操作性，遗留 API 需要一个作为输入的枚举
         * getParameterNames() Returns an Enumeration
         * of String objects containing the names of the parameters contained in
         * this request.
         */
        Enumeration<String> parameterNames = request.getParameterNames();
        for (Enumeration e = parameterNames; e.hasMoreElements(); ) {
            String elementName = e.nextElement().toString();
            System.out.println("name:" + elementName + " " + "value:" + request.getParameter(elementName));
        }

        String username = request.getParameter("username");
        String password = request.getParameter("pwd");

        Cookie cookieUname = new Cookie("uname", username);
        Cookie cookiePwd = new Cookie("pwd", password);
        // cookieUname.setPath("/");
        // cookiePwd.setPath("/");

        cookieUname.setMaxAge(60 * 60 * 24 * 7);
        cookiePwd.setMaxAge(60 * 60 * 24 * 7);

        response.addCookie(cookieUname);
        response.addCookie(cookiePwd);

        HttpSession session = request.getSession();
        session.invalidate();
        // 重定向跳转到success.jsp
        response.sendRedirect("success.jsp");
        request.setAttribute("username", username);
        // 转发
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("success.jsp");
        requestDispatcher.forward(request, response);
        // String rs = "<span style='color :red;font-size:30px;'> hello" +
        // username + "</span>";

        // response.setContentType("text/html:charset=utf-8");
        // PrintWriter out = response.getWriter();
        // out.print(rs);
        // out.close();
    }
}
