import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ViewAppointmentsServlet")
public class ViewAppointmentsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("Login.html");
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

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/mediconnect";
            con = DriverManager.getConnection(url, "root", "root");

            String query = null;

            if ("patient".equals(role)) {
                query = "SELECT a.id, a.patient_id, u1.username as patient_name, a.doctor_id, u2.username as doctor_name, a.appointment_date, a.appointment_time, a.description " +
                        "FROM appointments a " +
                        "JOIN users u1 ON a.patient_id = u1.id " +
                        "JOIN users u2 ON a.doctor_id = u2.id " +
                        "WHERE a.patient_id = ?";
            } else if ("doctor".equals(role)) {
                query = "SELECT a.id, a.patient_id, u1.username as patient_name, a.doctor_id, u2.username as doctor_name, a.appointment_date, a.appointment_time, a.description " +
                        "FROM appointments a " +
                        "JOIN users u1 ON a.patient_id = u1.id " +
                        "JOIN users u2 ON a.doctor_id = u2.id " +
                        "WHERE a.doctor_id = ?";
            } else if ("admin".equals(role)) {
                query = "SELECT a.id, a.patient_id, u1.username as patient_name, a.doctor_id, u2.username as doctor_name, a.appointment_date, a.appointment_time, a.description " +
                        "FROM appointments a " +
                        "JOIN users u1 ON a.patient_id = u1.id " +
                        "JOIN users u2 ON a.doctor_id = u2.id";
            }

            pst = con.prepareStatement(query);
            if (!"admin".equals(role)) {
                pst.setInt(1, userId);
            }
            rs = pst.executeQuery();

            out.println("<html><head>");
            out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'>");
            out.println("<style>");
            out.println("body { background-color: #f0f8ff; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; padding: 20px; }");
            out.println("h2 { text-align: center; color: #144848; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; font-weight: bolder; border-bottom: 2px solid #144848; padding-bottom: 10px; margin-bottom: 20px; }");
            out.println(".table { width: 100%; margin-top: 20px; border-collapse: collapse; }");
            out.println(".table th, .table td { padding: 10px; text-align: left; border-bottom: 1px solid #ddd; }");
            out.println(".table th { background-color: #144848; color: white; }");
            out.println(".table tr:nth-child(even) { background-color: #f2f2f2; }");
            out.println("</style>");
            out.println("</head><body>");
            out.println("<h2>Your Appointments</h2>");
            out.println("<table class='table table-bordered'>");
            out.println("<thead><tr><th>ID</th><th>Patient Name</th><th>Doctor Name</th><th>Date</th><th>Time</th><th>Description</th></tr></thead>");
            out.println("<tbody>");

            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

            while (rs.next()) {
                Date appointmentTime = rs.getTime("appointment_time");
                String formattedTime = timeFormat.format(appointmentTime);

                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("patient_name") + "</td>");
                out.println("<td>" + rs.getString("doctor_name") + "</td>");
                out.println("<td>" + rs.getDate("appointment_date") + "</td>");
                out.println("<td>" + formattedTime + "</td>");
                out.println("<td>" + rs.getString("description") + "</td>");
                out.println("</tr>");
            }

            out.println("</tbody></table>");
            out.println("</body></html>");

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
