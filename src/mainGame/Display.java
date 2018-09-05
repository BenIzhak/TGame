package mainGame;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Display {
	
	private JFrame frame;
	private Canvas canvas;
	private JPanel panel;
	private JProgressBar healthBar, stanimaBar, expBar;
	private JLabel lblLevel;
	
	
	private String title;
	private int windowWidth, windowHeight;
	private int width, height;
	private int panelWidth, panelHeight;
	
	private Color bgColor;
	
	
	public Display(String title, int windowWidth, int windowHeight) {
		super();
		this.title = title;
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.panelWidth = windowWidth;
		this.panelHeight = 128;
		this.width = windowWidth - panelWidth;
		this.height = windowHeight - panelHeight;
		this.stanimaBar = new JProgressBar();
		this.healthBar = new JProgressBar();
		this.expBar = new JProgressBar();
		this.lblLevel = new JLabel("");
		this.bgColor = new Color(160, 230, 255);
		
		createDisplay();
	}

	// creates new display
	private void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(windowWidth, windowHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(bgColor);
		frame.setVisible(true);
		
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		createPanel();
		
		
		frame.add(canvas, BorderLayout.NORTH);
		frame.add(panel, BorderLayout.SOUTH);
		frame.pack();
		
		// set panel visibility to false
		this.setPanelVisibility(false);
	}
	
	private void createPanel(){
		// init the panel components.
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(panelWidth, panelHeight));
		panel.setMaximumSize(new Dimension(panelWidth, panelHeight));
		panel.setMinimumSize(new Dimension(panelWidth, panelHeight));
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setBackground(Color.lightGray);
		panel.setLayout(null);
		
		JLabel lblHP = new JLabel("HP");
		lblHP.setFont(new Font("Arial", Font.BOLD, 24));
		lblHP.setBounds(15, 15, 37, 30);
		
		JLabel lblSP = new JLabel("SP");
		lblSP.setFont(new Font("Arial", Font.BOLD, 24));
		lblSP.setBounds(15, 50, 37, 30);
		
		JLabel lblEXP = new JLabel("EXP");
		lblEXP.setFont(new Font("Arial", Font.BOLD, 24));
		lblEXP.setBounds(15, 85, 52, 30);
		
		JLabel lblLevelText = new JLabel("LEVEL");
		lblLevelText.setFont(new Font("Arial", Font.BOLD, 32));
		lblLevelText.setBounds(420, 30, 108, 38);
		
		lblLevel.setFont(new Font("Arial", Font.BOLD, 32));
		lblLevel.setBounds(535, 30, 108, 38);

		
		healthBar.setBounds(80, 20, 300, 22);
		healthBar.setMinimum(0);
		healthBar.setMaximum(100);
		healthBar.setStringPainted(true);
		healthBar.setForeground(Color.red);
		
		stanimaBar.setBounds(80, 54, 300, 22);
		stanimaBar.setMinimum(0);
		stanimaBar.setMaximum(100);
		stanimaBar.setStringPainted(true);
		stanimaBar.setForeground(new Color(10, 110, 250));
		
		expBar.setBounds(80, 88, 1180, 22);
		expBar.setMinimum(0);
		expBar.setMaximum(100);
		expBar.setStringPainted(true);
		expBar.setForeground(Color.orange);
		
		
		panel.add(lblEXP);
		panel.add(lblSP);
		panel.add(lblHP);
		panel.add(lblLevelText);
		panel.add(lblLevel);
		panel.add(expBar);
		panel.add(healthBar);
		panel.add(stanimaBar);
		
	}
	
	
	

	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getFrame(){
		return frame;
	}

	public int getHeight() {
		return height;
	}


	public int getWidth() {
		return width;
	}


	public JProgressBar getHealthBar() {
		return healthBar;
	}


	public JProgressBar getStanimaBar() {
		return stanimaBar;
	}


	public JProgressBar getExpBar() {
		return expBar;
	}


	public JLabel getLblLevel() {
		return lblLevel;
	}
	
	public void setPanelVisibility(boolean visibility){
		panel.setVisible(visibility);
	}
	
	public boolean getPanelVisibility(){
		return panel.isVisible();
	}
	
	
}
