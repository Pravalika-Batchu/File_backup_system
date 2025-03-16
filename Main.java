import java.io.*;
import javax.swing.*;

public class Main{
    public static void main(String[] args) {
        JFrame main_frame = new JFrame("File Backup with Progress Bar");
        main_frame.setSize(400, 400);
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel l1 = new JLabel("Select the file to backup");
        l1.setBounds(40, 50, 300, 30);
        main_frame.add(l1);

        JButton choose = new JButton("Choose a file");
        choose.setBounds(60, 80, 200, 20);
        
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(60, 110, 200, 20);
        exitButton.addActionListener(e -> {
            System.exit(0); 
        });
        choose.addActionListener(e -> {
            JFileChooser fchoose = new JFileChooser();
            int returnvalue = fchoose.showOpenDialog(null);
            if (returnvalue == JFileChooser.APPROVE_OPTION) {
                File selected_file = fchoose.getSelectedFile();
                JFrame newFrame = new JFrame("File Selected");
                newFrame.setSize(400, 200);
                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JLabel fileLabel = new JLabel("You selected: " + selected_file.getAbsolutePath());
                fileLabel.setBounds(40, 30, 300, 30);
                newFrame.add(fileLabel);

                JButton backb = new JButton("Go Back");
                backb.setBounds(30, 60, 150, 30);

                JButton confirm = new JButton("Confirm/Proceed");
                confirm.setBounds(200, 60, 150, 30);

                newFrame.add(backb);
                newFrame.add(confirm);
                newFrame.setLayout(null);
                newFrame.setVisible(true);
                main_frame.setVisible(false);

                backb.addActionListener(e1 -> {
                    newFrame.dispose();
                    main_frame.setVisible(true);
                });

                confirm.addActionListener(e1 -> {
                    JFrame bFrame = new JFrame("Backup Process");
                    bFrame.setSize(500, 300);
                    bFrame.setLayout(null);

                    JLabel nfilel = new JLabel("Enter the backup file name:");
                    nfilel.setBounds(30, 20, 400, 30);
                    bFrame.add(nfilel);

                    JTextField textField = new JTextField();
                    textField.setBounds(200, 20, 200, 30);
                    bFrame.add(textField);

                    JButton submitButton = new JButton("Submit");
                    submitButton.setBounds(150, 80, 100, 30);
                    bFrame.add(submitButton);

                    // Progress bar
                    JProgressBar progressBar = new JProgressBar();
                    progressBar.setBounds(50, 120, 400, 30);
                    progressBar.setStringPainted(true);
                    bFrame.add(progressBar);

                    JLabel resultLabel = new JLabel();
                    resultLabel.setBounds(50, 160, 300, 30);
                    bFrame.add(resultLabel);
                    JButton ebutton = new JButton("Exit");
                    ebutton.setBounds(60, 200, 200, 40);
                    ebutton.addActionListener(ef -> {
                        System.exit(0); 
                    });
                    bFrame.add(ebutton);
                    bFrame.setVisible(true);

                    submitButton.addActionListener(e2 -> {
                        String inputText = textField.getText();
                        if (inputText.trim().isEmpty()) {
                            resultLabel.setText("Please enter a valid backup file name.");
                            return;
                        }

                        File backup_file = new File("C:/Users/Pravalika/Desktop/backup/"+inputText);

                        try (FileInputStream fis = new FileInputStream(selected_file);
                             FileOutputStream fos = new FileOutputStream(backup_file)) {

                            long totalBytes = selected_file.length(); 
                            long bytesCopied = 0;

                            if (backup_file.createNewFile()) {
                                byte[] buffer = new byte[1024]; 
                                int length;
                                while ((length = fis.read(buffer)) > 0) {
                                    fos.write(buffer, 0, length);
                                    bytesCopied += length;
                                    boolean initialProgressSet = false;
                                    if (!initialProgressSet && bytesCopied >= totalBytes * 0.3) {
                                        progressBar.setValue(30); 
                                        initialProgressSet = true; 
                                    }
                                    int progress = (int) ((bytesCopied * 70) / totalBytes) + 30; 
                                    progressBar.setValue(progress);
                                    Thread.sleep(100);
                                }
                                resultLabel.setText("Backup completed.");
                            } else {
                                byte[] buffer = new byte[1024];
                                int length;
                                boolean initialProgressSet = false;
                                while ((length = fis.read(buffer)) > 0) {
                                    fos.write(buffer, 0, length);
                                    bytesCopied += length;
                                    if (!initialProgressSet && bytesCopied >= totalBytes * 0.3) {
                                        progressBar.setValue(30); 
                                        initialProgressSet = true; 
                                    }
                                   
                                    int progress = (int) ((bytesCopied * 70) / totalBytes) + 30; 
                                    progressBar.setValue(progress);

                                
                                    Thread.sleep(100);
                                }
                                resultLabel.setText("Backup completed.");
                            }
                        } catch (IOException | InterruptedException ex) {
                            resultLabel.setText("Error occurred during backup.");
                            ex.printStackTrace();
                        }
                    });
                });
            }
        });

        main_frame.add(choose);
        main_frame.add(exitButton);
        main_frame.setLayout(null);
        main_frame.setVisible(true);
    }
}