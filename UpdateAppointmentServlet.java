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

@WebServlet("/UpdateAppointmentServlet")
public class UpdateAppointmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
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

        String appointmentId = request.getParameter("appointment_id");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String description = request.getParameter("description");

        if (appointmentId == null || appointmentId.isEmpty()) {
            out.println("<h3>Error: Appointment ID is missing.</h3>");
            return;
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mediconnect", "root", "root");
                 PreparedStatement pst = con.prepareStatement("UPDATE appointments SET appointment_date = ?, appointment_time = ?, description = ? WHERE id = ? AND (patient_id = ? OR doctor_id = ? OR ? = 'admin')")) {

                pst.setString(1, date);
                pst.setString(2, time);
                pst.setString(3, description);
                pst.setString(4, appointmentId);
                pst.setInt(5, userId);
                pst.setInt(6, userId);
                pst.setString(7, role);

                int rs = pst.executeUpdate();
 
                if (rs > 0) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Appointment updated successfully.');");
                    if ("doctor".equals(role)) {
                        out.println("location='Doctor.jsp';");
                    } else if ("patient".equals(role)) {
                        out.println("location='Patient.jsp';");
                    }
                    out.println("</script>");
                } else {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Failed to update appointment. You may not have the required permissions.');");
                    out.println("location='UpdateAppointment.jsp';");
                    out.println("</script>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace(out);
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
