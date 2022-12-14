/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import helper.DateTimeHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;
import model.TimeSheet;

/**
 *
 * @author Ngo Tung Son
 */
public class EmployeeDBContext extends DBContext {

    public ArrayList<Employee> getEmps(Date from, Date to) {
        ArrayList<Employee> emps = new ArrayList<>();
        try {
            String sql = "SELECT e.eid,e.ename,e.erole,ISNULL(t.tid,-1) tid,\n"
                    + "t.checkin,t.checkout\n"
                    + "FROM Employee e LEFT JOIN \n"
                    + "(SELECT * FROM TimeSheet WHERE checkin >= ?\n"
                    + "AND checkin <= ?) t\n"
                    + "ON e.eid = t.eid";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setTimestamp(1, DateTimeHelper.getTimeStamp(from));
            stm.setTimestamp(2, DateTimeHelper.getTimeStamp(to));
            ResultSet rs = stm.executeQuery();
            Employee currentEmp = new Employee();
            currentEmp.setId(-1);
            while (rs.next()) {
                int eid = rs.getInt("eid");
                if (eid != currentEmp.getId()) {
                    currentEmp = new Employee();
                    currentEmp.setId(eid);
                    currentEmp.setName(rs.getString("ename"));
                    currentEmp.setRole(rs.getString("erole"));
                    emps.add(currentEmp);
                }
                int tid = rs.getInt("tid");
                if (tid != -1) {
                    TimeSheet t = new TimeSheet();
                    t.setTid(tid);
                    t.setCheckin(rs.getTimestamp("checkin"));
                    t.setCheckout(rs.getTimestamp("checkout"));
                    t.setEmployee(currentEmp);
                    currentEmp.getTimesheets().add(t);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emps;
    }

    public void create(Employee e) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO [Employee]\n"
                    + "           ([ename]\n"
                    + "           ,[erole])\n"
                    + "     VALUES\n"
                    + "           (?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, e.getName());
            stm.setString(2, e.getRole());
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void edit(Employee e) {
//        try {
//            connection.setAutoCommit(false);
//            String sql = "UPDATE [Employee]\n"
//                    + "   SET [ename] = <ename, varchar(150),>\n"
//                    + "      ,[erole] = <erole, nchar(255),>\n"
//                    + " WHERE [eid] = <eid, int,>";
//            PreparedStatement stm = connection.prepareStatement(sql);
//            stm.setString(1, e.getName());
//            stm.setString(2, e.getRole());
//            stm.execute();
//            
//            String sql1 = "UPDATE [TimeSheet]\n"
//                    + "   SET [checkin] = <checkin, datetime,>\n"
//                    + "      ,[checkout] = <checkout, datetime,>\n"
//                    + " WHERE [eid] = <eid, int,>";
//            PreparedStatement stm1 = connection.prepareStatement(sql1);
//            ResultSet rs = stm.executeQuery();
//            TimeSheet t = new TimeSheet();
//            t.setTid(tid);
//            t.setCheckin(rs.getTimestamp("checkin"));
//            t.setCheckout(rs.getTimestamp("checkout"));
//            stm1.execute();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void delete(Employee e) {
        try {

            connection.setAutoCommit(false);
            String sql = "DELETE FROM [TimeSheet]\n"
                    + "      WHERE eid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, e.getId());
            stm.executeUpdate();

            String sql1 = "DELETE FROM [Employee]\n"
                    + "      WHERE eid = ?";
            PreparedStatement stm1 = connection.prepareStatement(sql1);
            stm1.setInt(1, e.getId());
            stm1.executeUpdate();
            connection.commit();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
                connection.rollback(); // n???u xu???t hi???n l???i th?? xo?? s???ch c??c thay ?????i
                // v?? tr??? v??? tr???ng th??i tr?????c khi c?? thay ?????i 
            } catch (SQLException ex1) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public Employee getEmployeeById(int id) {
        Employee employee = null;
        try {
            String sql = "SELECT [eid]\n"
                    + "      ,[ename]\n"
                    + "      ,[erole]\n"
                    + "  FROM [Employee]\n"
                    + "  WHERE eid = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                employee = new Employee();
                employee.setId(rs.getInt("eid"));
                employee.setName(rs.getString("ename"));
                employee.setRole(rs.getString("erole"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employee;
    }

    public void updateEmployee(int eid, String ename, String erole) {
         try {
        String sql = "UPDATE [Employee]\n"
                + "   SET [ename] = ?\n"
                + "      ,[erole] = ?\n"
                + " WHERE eid = ?";
       
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, ename);
            stm.setString(2, erole);
            stm.setInt(3, eid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
