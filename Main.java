import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main extends JFrame {
    private final AlumniDAO dao = new AlumniDAO();

    private final JTextField name = new JTextField();
    private final JTextField roll = new JTextField();
    private final JTextField year = new JTextField();
    private final JTextField branch = new JTextField();
    private final JTextField email = new JTextField();
    private final JTextField phone = new JTextField();
    private final JTextField job = new JTextField();
    private final JTextField searchField = new JTextField();

    private final JTable viewTable = new JTable();
    private final JTable searchTable = new JTable();

    public Main() {
        setTitle("Alumni Database");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Add", addTab());
        tabs.add("View", viewTab());
        tabs.add("Search", searchTab());
        setContentPane(tabs);

        loadAll();
    }

    private JPanel addTab() {
        JPanel p = new JPanel(new GridLayout(8, 2, 8, 8));
        p.setBorder(BorderFactory.createEmptyBorder(20, 120, 20, 120));
        addRow(p, "Name", name);
        addRow(p, "Roll No", roll);
        addRow(p, "Year", year);
        addRow(p, "Branch", branch);
        addRow(p, "Email", email);
        addRow(p, "Phone", phone);
        addRow(p, "Job", job);
        JButton save = new JButton("Save");
        save.addActionListener(e -> saveData());
        p.add(new JLabel(""));
        p.add(save);
        return p;
    }

    private JPanel viewTab() {
        JPanel p = new JPanel(new BorderLayout(8, 8));
        styleTable(viewTable);
        p.add(new JScrollPane(viewTable), BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton refresh = new JButton("Refresh");
        JButton delete = new JButton("Delete");
        refresh.addActionListener(e -> loadAll());
        delete.addActionListener(e -> deleteSelected());
        actions.add(refresh);
        actions.add(delete);
        p.add(actions, BorderLayout.SOUTH);
        return p;
    }

    private JPanel searchTab() {
        JPanel p = new JPanel(new BorderLayout(8, 8));
        JPanel top = new JPanel(new BorderLayout(8, 8));
        JButton searchBtn = new JButton("Search");
        searchBtn.addActionListener(e -> searchData());
        top.add(searchField, BorderLayout.CENTER);
        top.add(searchBtn, BorderLayout.EAST);
        p.add(top, BorderLayout.NORTH);
        styleTable(searchTable);
        p.add(new JScrollPane(searchTable), BorderLayout.CENTER);
        return p;
    }

    private void addRow(JPanel p, String label, JTextField field) {
        p.add(new JLabel(label));
        p.add(field);
    }

    private void styleTable(JTable table) {
        table.setModel(new DefaultTableModel(new Object[]{"ID", "Name", "Roll No", "Year", "Branch", "Email", "Phone", "Job"}, 0));
    }

    private void saveData() {
        try {
            if (name.getText().trim().isEmpty() || roll.getText().trim().isEmpty() || year.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name, Roll No and Year are required");
                return;
            }

            boolean ok = dao.addAlumni(
                    name.getText().trim(),
                    roll.getText().trim(),
                    Integer.parseInt(year.getText().trim()),
                    branch.getText().trim(),
                    email.getText().trim(),
                    phone.getText().trim(),
                    job.getText().trim()
            );

            JOptionPane.showMessageDialog(this, ok ? "Saved" : "Not saved");
            if (ok) {
                name.setText("");
                roll.setText("");
                year.setText("");
                branch.setText("");
                email.setText("");
                phone.setText("");
                job.setText("");
                loadAll();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Enter valid Year");
        }
    }

    private void loadAll() {
        fillTable(viewTable, dao.getAllAlumni());
    }

    private void searchData() {
        fillTable(searchTable, dao.searchAlumni(searchField.getText().trim()));
    }

    private void deleteSelected() {
        int row = viewTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a row first");
            return;
        }
        int id = Integer.parseInt(viewTable.getValueAt(row, 0).toString());
        boolean ok = dao.deleteAlumni(id);
        JOptionPane.showMessageDialog(this, ok ? "Deleted" : "Not deleted");
        if (ok) {
            loadAll();
        }
    }

    private void fillTable(JTable table, ResultSet rs) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        if (rs == null) {
            return;
        }

        Statement st = null;
        Connection con = null;
        try {
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("roll_no"),
                        rs.getInt("year"),
                        rs.getString("branch"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("job")
                });
            }
            st = rs.getStatement();
            if (st != null) {
                con = st.getConnection();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (Exception ignored) {
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception ignored) {
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception ignored) {
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
