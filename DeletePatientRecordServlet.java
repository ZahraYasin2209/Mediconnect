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

@WebServlet("/DeletePatientRecordServlet")
public class DeletePatientRecordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("role") == null || !"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect("Unauthorized.jsp");
            return;
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Error: Patient ID is missing.');");
            out.println("location='DeletePatientRecord.jsp';");
            out.println("</script>");
            return;
        }

        Connection con = null;
        PreparedStatement pst = null;

        try {
            int id = Integer.parseInt(idStr);

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mediconnect", "root", "root");

            // Delete related reports first to satisfy foreign key constraints
            String deleteReportsQuery = "DELETE FROM reports WHERE patient_id = ?";
            pst = con.prepareStatement(deleteReportsQuery);
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();

            // Delete the patient record
            String deletePatientQuery = "DELETE FROM patients WHERE id = ?";
            pst = con.prepareStatement(deletePatientQuery);
            pst.setInt(1, id);
            int rs = pst.executeUpdate();

            if (rs > 0) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Record deleted successfully.');");
                out.println("location='Admin.jsp';");
                out.println("</script>");
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Failed to delete record. Please check the patient ID and try again.');");
                out.println("location='DeletePatientRecord.jsp';");
                out.println("</script>");
            }

        } catch (Exception e) {
            e.printStackTrace(out);
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Error: " + e.getMessage() + "');");
            out.println("location='DeletePatientRecord.jsp';");
            out.println("</script>");
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace(out);
            }
        }
    }
}
