package com.bublik.drdrdr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Main {

    private static String getMessage(int m)
    {
        switch (m)
        {
            case 0: return "All pictures are on your Desktop in folder \"Lock screen pictures\"\nPress any key to close this window";
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
	// write your code here
        if (!System.getProperty("os.name").equals("Windows 10")) return;
        String user = System.getProperty("user.name");
        String path = "C:\\Users\\"+user + "\\AppData\\Local\\Packages\\Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy\\LocalState\\Assets";

        File folder = new File(path);
      //  System.out.println(folder.isDirectory());
        if (!folder.exists()) return;
        File[] files = folder.listFiles();
        if (files==null) return;
        String desktopPath = "C:\\Users\\" + user + "\\Desktop\\Lock screen pictures";
        File desktopFolder = new File(desktopPath);
        if (!(desktopFolder.exists() && desktopFolder.isDirectory()))
        {
            if (!desktopFolder.mkdir()) return;
        }
        for (int i = 0; i < files.length;i++)
        {
            File to = new File(desktopPath + "\\"+files[i].getName() + ".jpg");
            Files.copy(files[i].toPath(),(to).toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
       // System.out.println(getMessage(0));
      //  System.in.read();

        setWarningMsg(getMessage(0));


    }

    public static void setWarningMsg(String text){
        Toolkit.getDefaultToolkit().beep();
        JOptionPane optionPane = new JOptionPane(text,JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog("Windows 10 Lock Screen Images");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
        System.exit(0);
    }
}
