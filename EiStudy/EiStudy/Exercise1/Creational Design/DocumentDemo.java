import java.io.FileWriter;
import java.io.IOException;

interface Document {
    void create(String content);
}

class PDFDocument implements Document {
    @Override
    public void create(String content) {
        try (FileWriter writer = new FileWriter("document.pdf")) {
            writer.write("%PDF-1.4\n");
            writer.write("1 0 obj\n");
            writer.write("<< /Type /Catalog /Pages 2 0 R >>\n");
            writer.write("endobj\n");
            writer.write("2 0 obj\n");
            writer.write("<< /Type /Pages /Kids [3 0 R] /Count 1 >>\n");
            writer.write("endobj\n");
            writer.write("3 0 obj\n");
            writer.write("<< /Type /Page /Parent 2 0 R /Resources << /Font << /F1 4 0 R >> >> /MediaBox [0 0 300 144] /Contents 5 0 R >>\n");
            writer.write("endobj\n");
            writer.write("4 0 obj\n");
            writer.write("<< /Type /Font /Subtype /Type1 /Name /F1 /BaseFont /Helvetica >>\n");
            writer.write("endobj\n");
            writer.write("5 0 obj\n");
            writer.write("<< /Length 55 >>\n");
            writer.write("stream\n");
            writer.write("BT /F1 12 Tf 10 120 Td (" + content + ") Tj ET\n");
            writer.write("endstream\n");
            writer.write("endobj\n");
            writer.write("xref\n");
            writer.write("0 6\n");
            writer.write("0000000000 65535 f \n");
            writer.write("0000000009 00000 n \n");
            writer.write("0000000056 00000 n \n");
            writer.write("0000000111 00000 n \n");
            writer.write("0000000212 00000 n \n");
            writer.write("0000000282 00000 n \n");
            writer.write("trailer << /Size 6 /Root 1 0 R >>\n");
            writer.write("startxref\n");
            writer.write("406\n");
            writer.write("%%EOF\n");
            System.out.println("Created PDF document: document.pdf");
        } catch (IOException e) {
            System.out.println("An error occurred while creating the PDF document.");
            e.printStackTrace();
        }
    }
}

class WordDocument implements Document {
    @Override
    public void create(String content) {
        try (FileWriter writer = new FileWriter("document.doc")) {
            writer.write(content);
            System.out.println("Created Word document: document.doc");
        } catch (IOException e) {
            System.out.println("An error occurred while creating the Word document.");
            e.printStackTrace();
        }
    }
}

class TextDocument implements Document {
    @Override
    public void create(String content) {
        try (FileWriter writer = new FileWriter("document.txt")) {
            writer.write(content);
            System.out.println("Created Text document: document.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while creating the Text document.");
            e.printStackTrace();
        }
    }
}

abstract class DocumentCreator {
    public abstract Document createDocument();
    
    public void operateDocument(String content) {
        Document doc = createDocument();
        doc.create(content);
    }
}

class PDFCreator extends DocumentCreator {
    @Override
    public Document createDocument() {
        return new PDFDocument();
    }
}

class WordCreator extends DocumentCreator {
    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}

class TextCreator extends DocumentCreator {
    @Override
    public Document createDocument() {
        return new TextDocument();
    }
}

public class DocumentDemo {
    public static void main(String[] args) {
        DocumentCreator creator = new PDFCreator();
        creator.operateDocument("This is a PDF document.");
        
        creator = new WordCreator();
        creator.operateDocument("This is a Word document.");
        
        creator = new TextCreator();
        creator.operateDocument("This is a Text document.");
    }
}