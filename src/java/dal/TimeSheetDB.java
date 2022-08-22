/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class TimeSheetDB extends DBContext {

    public void addWorkingDate(int eid, String checkin, String checkout) {
         try {
        String sql = "INSERT INTO [TimeSheet]\n"
                + "           ([eid]\n"
                + "           ,[checkin]\n"
                + "           ,[checkout])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";
       
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, eid);
            stm.setString(2, checkin);
            stm.setString(3, checkout);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TimeSheetDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
