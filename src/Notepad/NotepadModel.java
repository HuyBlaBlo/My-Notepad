package Notepad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NotepadModel {
    private File currentFile; 
    private String content;

    public NotepadModel() {
        this.content = "";
    }

    public File getCurrentFile() { return currentFile; }
    public void setCurrentFile(File currentFile) { this.currentFile = currentFile; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public void saveFile(String text, File file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(text);
        writer.close();
        
        this.currentFile = file;
        this.content = text;
    }

    public void loadFile(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        
        this.currentFile = file;
        this.content = sb.toString();
    }
}