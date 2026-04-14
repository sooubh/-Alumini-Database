import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class AlumniDAO {
    public boolean addAlumni(String name, String rollNo, int year, String branch, String email, String phone, String job) {
        String sql = "INSERT INTO alumni(name, roll_no, year, branch, email, phone, job) VALUES(?,?,?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, rollNo);
            ps.setInt(3, year);
            ps.setString(4, branch);
            ps.setString(5, email);
            ps.setString(6, phone);
            ps.setString(7, job);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet getAllAlumni() {
        String sql = "SELECT * FROM alumni ORDER BY id DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            CachedRowSet rowSet = RowSetProvider.newFactory().createCachedRowSet();
            rowSet.populate(rs);
            return rowSet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet searchAlumni(String keyword) {
        String sql = "SELECT * FROM alumni WHERE name LIKE ? OR roll_no LIKE ? OR branch LIKE ? OR email LIKE ? OR phone LIKE ? OR job LIKE ? OR CAST(year AS CHAR) LIKE ? ORDER BY id DESC";
        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            String k = "%" + keyword + "%";
            for (int i = 1; i <= 7; i++) {
                ps.setString(i, k);
            }
            try (ResultSet rs = ps.executeQuery()) {
                CachedRowSet rowSet = RowSetProvider.newFactory().createCachedRowSet();
                rowSet.populate(rs);
                return rowSet;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteAlumni(int id) {
        String sql = "DELETE FROM alumni WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
