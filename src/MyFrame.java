import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class MyFrame extends JFrame implements ActionListener {
    JButton importCSV;
    Mapper mapper;
    File file;

    MyFrame(Mapper mapper) {
        this.mapper = mapper;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        importCSV = new JButton("Import CSV File");
        importCSV.addActionListener(this);
        this.add(importCSV);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == importCSV){
            try { mapper.dropTable("product");
                System.out.println("Table Detected -- Replacing Table...");
            } catch (SQLException ignored) {
                System.out.println("No Current Table -- Adding New Table...");
            }
            JFileChooser fileChooser=new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            int response = fileChooser.showOpenDialog(null);//select file to open.
            if (response == JFileChooser.APPROVE_OPTION){
                file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                System.out.println(file);
                try {
                    CSV_Import.readFile(mapper, file);
                } catch (FileNotFoundException | SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
    }

}
