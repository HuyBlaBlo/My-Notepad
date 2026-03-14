package Notepad;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class NotepadControl implements ActionListener, MouseWheelListener {
    private NotepadView view;
    private NotepadModel model;

    public NotepadControl(NotepadView view, NotepadModel model) {
        this.view = view;
        this.model = model;
    }

    public void initFontMenu() {
        String[] fonts = {"Arial","Times New Roman","Calibri"};
        for (String f : fonts) {
            JMenuItem item = new JMenuItem(f);
            item.addActionListener(e -> setFontName(f));
            view.getMenuFont().add(item);
        }


        int[] sizes = {5,6,7,8,9,10,11,12,13,14,16,18,20,24,28,32,48,72};
        for (int s : sizes) {
            JMenuItem item = new JMenuItem(String.valueOf(s));
            item.addActionListener(e -> setFontSize(s));
            view.getMenuFontSize().add(item);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src.equals(view.getItemNew())) {
            view.getTextArea().setText("");
            model.setCurrentFile(null);
            view.setTitle("Notepad Pro - Untitled");
            view.getUndoManager().discardAllEdits(); 
        }
        else if (src.equals(view.getItemOpen())) {
            processOpen();
        }
        else if (src.equals(view.getItemSave())) {
            processSave(false); 
        }
        else if (src.equals(view.getItemSaveAs())) {
            processSave(true); 
        }
        else if (src.equals(view.getItemExit())) {
            System.exit(0);
        }


        else if (src.equals(view.getItemUndo())) {
            try {
                if (view.getUndoManager().canUndo()) view.getUndoManager().undo();
            } catch (CannotUndoException ex) {}
        }
        else if (src.equals(view.getItemRedo())) {
            try {
                if (view.getUndoManager().canRedo()) view.getUndoManager().redo();
            } catch (CannotRedoException ex) {}
        }
        else if (src.equals(view.getItemWordWrap())) {
            boolean wrap = view.getItemWordWrap().isSelected();
            view.getTextArea().setLineWrap(wrap);
            view.getTextArea().setWrapStyleWord(wrap);
        }
        else if (src.equals(view.getItemColor())) {
            Color c = JColorChooser.showDialog(view, "Chọn màu nền", Color.WHITE);
            if (c != null) {
                view.getTextArea().setBackground(c);
            }
        }
    }
    

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.isControlDown()) {
            Font currentFont = view.getTextArea().getFont();
            int size = currentFont.getSize();
            if (e.getWheelRotation() < 0) {
                size += 2;
            } else {
                size -= 2;
            }
            
            if (size < 8) size = 8;
            setFontSize(size);
        }
    }
    private void setFontName(String name) {
        Font f = view.getTextArea().getFont();
        view.getTextArea().setFont(new Font(name, f.getStyle(), f.getSize()));
    }

    private void setFontSize(int size) {
        Font f = view.getTextArea().getFont();
        view.getTextArea().setFont(new Font(f.getName(), f.getStyle(), size));
    }

    private void processOpen() {
        int returnVal = view.getFileChooser().showOpenDialog(view);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = view.getFileChooser().getSelectedFile();
            try {
                model.loadFile(file);
                view.getTextArea().setText(model.getContent());
                view.setTitle("Notepad Pro - " + file.getName());
                view.getUndoManager().discardAllEdits(); 
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Lỗi đọc file!");
            }
        }
    }

    private void processSave(boolean isSaveAs) {
        if (isSaveAs || model.getCurrentFile() == null) {
            int returnVal = view.getFileChooser().showSaveDialog(view);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = view.getFileChooser().getSelectedFile();
                model.setCurrentFile(file);
                view.setTitle("Notepad Pro - " + file.getName());
            } else {
                return; 
            }
        }
        
        try {
            model.saveFile(view.getTextArea().getText(), model.getCurrentFile());
            if(isSaveAs) JOptionPane.showMessageDialog(view, "Lưu thành công!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Lỗi ghi file!");
        }
    }
}