package mx.com.eixy.utilities.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SwingButtons {

	public static void setIcon(JButton btnNewButton, String imagePath) {
		File ftp_off = new File(imagePath);
		BufferedImage image = null;
		try {

			image = ImageIO.read(ftp_off);
		} catch (IOException e) {
			System.out.println("A leer " + ftp_off);
			e.printStackTrace();
		}
		btnNewButton.setIcon(new ImageIcon(image));
	}

	public static void setDisabledIcon(JButton btnNewButton, String imagePath) {
		File ftp_off = new File(imagePath);
		BufferedImage image = null;
		try {
			image = ImageIO.read(ftp_off);
		} catch (IOException e) {
			e.printStackTrace();
		}
		btnNewButton.setDisabledIcon(new ImageIcon(image));
	}
}
