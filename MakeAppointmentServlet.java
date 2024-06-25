import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/MakeAppointmentServlet")
public class MakeAppointmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String role = (String) session.getAttribute("role");
        Integer patientId = (Integer) session.getAttribute("userId");
        String email = (String) session.getAttribute("email"); // Get email from session

        if (role == null || patientId == null || !"patient".equals(role)) {
            response.sendRedirect("Unauthorized.jsp");
            return;
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String appointmentDate = request.getParameter("appointment_date");
        String appointmentTime = request.getParameter("appointment_time");
        String description = request.getParameter("description");

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/mediconnect";
            con = DriverManager.getConnection(url, "root", "root");

            // Find an available doctor
            String findAvailableDoctorQuery = "SELECT id FROM users WHERE role = 'doctor' AND id NOT IN (SELECT doctor_id FROM appointments WHERE appointment_date = ? AND appointment_time = ?)";
            pst = con.prepareStatement(findAvailableDoctorQuery);
            pst.setString(1, appointmentDate);            pst.setString(2, appointmentTime);
            rs = pst.executeQuery();

            if (rs.next()) {
                int doctorId = rs.getInt("id");

                // Insert the appointment
                String insertQuery = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time, description) VALUES (?, ?, ?, ?, ?)";
                pst = con.prepareStatement(insertQuery);
                pst.setInt(1, patientId);
                pst.setInt(2, doctorId);
                pst.setString(3, appointmentDate);
                pst.setString(4, appointmentTime);
                pst.setString(5, description);
                int rsInsert = pst.executeUpdate();

                if (rsInsert > 0) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Appointment made successfully.');");
                    out.println("location='Patient.jsp';");
                    out.println("</script>");
                } else {
                    out.println("<h3>Failed to make appointment.</h3>");
                }
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('No available doctor found for the selected time. Please choose a different time.');");
                out.println("location='MakeAppointment.jsp';");
                out.println("</script>");
            }

        } catch (Exception e) {
            e.printStackTrace(out);
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace(out);
            }
        }
    }
}




