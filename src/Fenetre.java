import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Fenetre extends JFrame {
    JPanel panel = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();

    JTextField name = new JTextField(15);
    JTextField mark = new JTextField(15);

    JLabel nameLabel = new JLabel("Name");
    JLabel markLabel = new JLabel("Mark");
    JLabel showData = new JLabel();
    JButton add = new JButton("Add");
    JButton show = new JButton("Show");
    JButton delete = new JButton("Delete");
    public Fenetre(){


        this.setTitle("Fenetre");
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);

        panel.setLayout(new FlowLayout());
        panel2.setLayout(new FlowLayout());
        panel3.setLayout(new FlowLayout());
        this.setLayout(new BorderLayout());

        this.getContentPane().add(panel, BorderLayout.NORTH);
        this.getContentPane().add(panel2, BorderLayout.CENTER);
        this.getContentPane().add(panel3, BorderLayout.SOUTH);

       panel.add(nameLabel);
       panel.add(name);
       panel.add(markLabel);
       panel.add(mark);

       panel2.add(add);
       panel2.add(show);
       panel2.add(delete);

       panel3.add(showData);

        this.setVisible(true);

        //ajoute des données dans un fichier
        add.addActionListener(new AddAction());
        show.addActionListener(new ShowData());
        delete.addActionListener(new DeleteData());
    }
    //pour ajouter les nom et not ddans un fichier
    class AddAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(mark.getText() + " " + name.getText());
            double note = Double.parseDouble(mark.getText());
           String nom = name.getText();

           try{
               FileWriter fileWriter = new FileWriter("data.txt", true);
               BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
               bufferedWriter.write(nom + " "+note);
               bufferedWriter.newLine();
               bufferedWriter.close();
           }catch (IOException ex){
               System.out.println(ex.toString());
           }
        }
    }

    // afficher le contenu du fichier
    class ShowData implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                FileReader fileReader = new FileReader("data.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String row;
                String data = "";
                while ((row = bufferedReader.readLine()) != null){
                    data += row + " \n";

                    System.out.println(row);
                }
                showData.setText(data);
                bufferedReader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    class DeleteData implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                FileWriter fileWriter = new FileWriter("data.txt", false);
                PrintWriter printWriter = new PrintWriter(fileWriter, false);
                printWriter.flush();
                printWriter.close();
                fileWriter.close();
            }catch(Exception ec){
                System.out.println(ec.toString());
            }


        }
    }
    public static void main(String[] args){
        Fenetre fen = new Fenetre();
    }
}
