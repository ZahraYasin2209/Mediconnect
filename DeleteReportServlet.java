import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/DeleteReportServlet")
public class DeleteReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("Unauthorized.jsp");
            return;
        }

        String role = (String) session.getAttribute("role");
        Integer userId = (Integer) session.getAttribute("userId");

        if (role == null || (!role.equals("doctor") && !role.equals("admin")) || userId == null) {
            response.sendRedirect("Unauthorized.jsp");
            return;
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String reportIdStr = request.getParameter("id");

        if (reportIdStr == null || reportIdStr.isEmpty()) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Error: Report ID is missing.');");
            out.println("location='DeleteReport.jsp';");
            out.println("</script>");
            return;
        }

        try {
            int reportId = Integer.parseInt(reportIdStr);

            try (Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mediconnect", "root", "root");
                 PreparedStatement pst = con.prepareStatement(getDeleteQuery(role))) {

                Class.forName("com.mysql.jdbc.Driver");
                pst.setInt(1, reportId);

                if ("doctor".equals(role)) {
                    pst.setInt(2, userId);
                }

                int rs = pst.executeUpdate();

                if (rs > 0) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Report deleted successfully.');");
                    out.println("location='" + getRedirectPage(role) + "';");
                    out.println("</script>");
                } else {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Failed to delete report. Please check the report ID and try again.');");
                    out.println("location='DeleteReport.jsp';");
                    out.println("</script>");
                }
            } catch (Exception e) {
                e.printStackTrace(out);
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Error: " + e.getMessage() + "');");
                out.println("location='DeleteReport.jsp';");
                out.println("</script>");
            }
        } catch (NumberFormatException e) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Error: Report ID is not a valid number.');");
            out.println("location='DeleteReport.jsp';");
            out.println("</script>");
            e.printStackTrace(out);
        }
    }

    private String getDeleteQuery(String role) {
        if ("doctor".equals(role)) {
            return "DELETE FROM reports WHERE id = ? AND doctor_id = ?";
        } else {
            return "DELETE FROM reports WHERE id = ?";
        }
    }

    private String getRedirectPage(String role) {
        if ("doctor".equals(role)) {
            return "Doctor.jsp";
        } else if ("admin".equals(role)) {
            return "Admin.jsp";
        } else {
            return "Unauthorized.jsp";
        }
    }
}
