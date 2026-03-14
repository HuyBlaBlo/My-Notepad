package Notepad;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.undo.UndoManager;



public class NotepadView extends JFrame {
    private NotepadModel model;
    

    private JTextArea textArea;
    private JFileChooser fileChooser;
    private UndoManager undoManager; 


    private JMenuItem itemNew, itemOpen, itemSave, itemSaveAs, itemExit;
    private JMenuItem itemUndo, itemRedo, itemColor;
    private JCheckBoxMenuItem itemWordWrap; 
    
    private JMenu menuFont, menuFontSize;

    public NotepadView() {
        this.model = new NotepadModel();
        this.undoManager = new UndoManager();
        init();
        this.setVisible(true);
    }

    private void init() {
        this.setTitle("Notepad Pro - Untitled");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());


        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.getDocument().addUndoableEditListener(e -> undoManager.addEdit(e.getEdit()));
        JScrollPane scrollPane = new JScrollPane(textArea);
        this.add(scrollPane, BorderLayout.CENTER);
        
        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = new JMenu("File");
        itemNew = new JMenuItem("New");
        itemOpen = new JMenuItem("Open");
        itemSave = new JMenuItem("Save");
        itemSaveAs = new JMenuItem("Save As");
        itemExit = new JMenuItem("Exit");

        itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));

        menuFile.add(itemNew);
        menuFile.add(itemOpen);
        menuFile.add(itemSave);
        menuFile.add(itemSaveAs);
        menuFile.addSeparator();
        menuFile.add(itemExit);

        JMenu menuEdit = new JMenu("Edit");
        itemUndo = new JMenuItem("Undo");
        itemRedo = new JMenuItem("Redo");
        itemColor = new JMenuItem("Background Color...");
        itemWordWrap = new JCheckBoxMenuItem("Word Wrap");

        itemUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        itemRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));

        menuEdit.add(itemUndo);
        menuEdit.add(itemRedo);
        menuEdit.addSeparator();
        menuEdit.add(itemWordWrap);
        menuEdit.add(itemColor);


        menuFont = new JMenu("Font"); 

        menuFontSize = new JMenu("Font Size");

        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuFont);
        menuBar.add(menuFontSize);

        this.setJMenuBar(menuBar);
        this.fileChooser = new JFileChooser();

        NotepadControl ctl = new NotepadControl(this, model);

        itemNew.addActionListener(ctl);
        itemOpen.addActionListener(ctl);
        itemSave.addActionListener(ctl);
        itemSaveAs.addActionListener(ctl);
        itemExit.addActionListener(ctl);

        itemUndo.addActionListener(ctl);
        itemRedo.addActionListener(ctl);
        itemWordWrap.addActionListener(ctl);
        itemColor.addActionListener(ctl);
       
        textArea.addMouseWheelListener(ctl);
        
        ctl.initFontMenu(); 
        
        
    }

	public NotepadModel getModel() {
		return model;
	}

	public void setModel(NotepadModel model) {
		this.model = model;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JFileChooser getFileChooser() {
		return fileChooser;
	}

	public void setFileChooser(JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}

	public UndoManager getUndoManager() {
		return undoManager;
	}

	public void setUndoManager(UndoManager undoManager) {
		this.undoManager = undoManager;
	}

	public JMenuItem getItemNew() {
		return itemNew;
	}

	public void setItemNew(JMenuItem itemNew) {
		this.itemNew = itemNew;
	}

	public JMenuItem getItemOpen() {
		return itemOpen;
	}

	public void setItemOpen(JMenuItem itemOpen) {
		this.itemOpen = itemOpen;
	}

	public JMenuItem getItemSave() {
		return itemSave;
	}

	public void setItemSave(JMenuItem itemSave) {
		this.itemSave = itemSave;
	}

	public JMenuItem getItemSaveAs() {
		return itemSaveAs;
	}

	public void setItemSaveAs(JMenuItem itemSaveAs) {
		this.itemSaveAs = itemSaveAs;
	}

	public JMenuItem getItemExit() {
		return itemExit;
	}

	public void setItemExit(JMenuItem itemExit) {
		this.itemExit = itemExit;
	}

	public JMenuItem getItemUndo() {
		return itemUndo;
	}

	public void setItemUndo(JMenuItem itemUndo) {
		this.itemUndo = itemUndo;
	}

	public JMenuItem getItemRedo() {
		return itemRedo;
	}

	public void setItemRedo(JMenuItem itemRedo) {
		this.itemRedo = itemRedo;
	}

	public JMenuItem getItemColor() {
		return itemColor;
	}

	public void setItemColor(JMenuItem itemColor) {
		this.itemColor = itemColor;
	}

	public JCheckBoxMenuItem getItemWordWrap() {
		return itemWordWrap;
	}

	public void setItemWordWrap(JCheckBoxMenuItem itemWordWrap) {
		this.itemWordWrap = itemWordWrap;
	}

	public JMenu getMenuFont() {
		return menuFont;
	}

	public void setMenuFont(JMenu menuFont) {
		this.menuFont = menuFont;
	}

	public JMenu getMenuFontSize() {
		return menuFontSize;
	}

	public void setMenuFontSize(JMenu menuFontSize) {
		this.menuFontSize = menuFontSize;
	}

    
   
}