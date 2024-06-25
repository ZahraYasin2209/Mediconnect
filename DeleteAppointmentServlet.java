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

@WebServlet("/DeleteAppointmentServlet")
public class DeleteAppointmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("Unauthorized.jsp");
            return;
        }

        String role = (String) session.getAttribute("role");
        Integer userId = (Integer) session.getAttribute("userId");

        if (role == null || userId == null) {
            response.sendRedirect("Unauthorized.jsp");
            return;
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String appointmentIdStr = request.getParameter("id");

        if (appointmentIdStr == null || appointmentIdStr.isEmpty()) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Error: Appointment ID is missing.');");
            out.println("location='DeleteAppointment.jsp';");
            out.println("</script>");
            return;
        }

        try {
            int appointmentId = Integer.parseInt(appointmentIdStr);

            try (Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mediconnect", "root", "root");
                 PreparedStatement pst = con.prepareStatement("DELETE FROM appointments WHERE id = ? AND (patient_id = ? OR doctor_id = ? OR ? = 'admin')")) {

                Class.forName("com.mysql.jdbc.Driver");
                pst.setInt(1, appointmentId);
                pst.setInt(2, userId);
                pst.setInt(3, userId);
                pst.setString(4, role);

                int rs = pst.executeUpdate();

                if (rs > 0) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Appointment deleted successfully.');");
                    if ("doctor".equals(role)) {
                        out.println("location='Doctor.jsp';");
                    } else if ("patient".equals(role)) {
                        out.println("location='Patient.jsp';");
                    } else if ("admin".equals(role)) {
                        out.println("location='Admin.jsp';");
                    }
                    out.println("</script>");
                } else {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Failed to delete appointment. You may not have the required permissions.');");
                    out.println("location='DeleteAppointment.jsp';");
                    out.println("</script>");
                }
            } catch (Exception e) {
                e.printStackTrace(out);
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Error: " + e.getMessage() + "');");
                out.println("location='DeleteAppointment.jsp';");
                out.println("</script>");
            }
        } catch (NumberFormatException e) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Error: Appointment ID is not a valid number.');");
            out.println("location='DeleteAppointment.jsp';");
            out.println("</script>");
            e.printStackTrace(out);
        }
    }
}
