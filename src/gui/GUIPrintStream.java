/**
 * Restore By Windyboy 2020/08/14 00:49
 */
package gui;

import java.awt.Color;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author Pungin
 */
public class GUIPrintStream extends PrintStream {

    private final JTextPane mainComponent;
    private final JTextPane component;
    private final int type;
    private final int lineLimit;
    public static final int OUT = 0;
    public static final int ERR = 1;
    public static final int NOTICE = 2;
    public static final int PACKET = 3;

    public GUIPrintStream(OutputStream out, JTextPane mainComponent, JTextPane component, int type) {
        super(out);
        this.mainComponent = mainComponent;
        this.component = component;
        this.type = type;
        lineLimit = 100;
    }

    public GUIPrintStream(OutputStream out, JTextPane mainComponent, JTextPane component, int type, int lineLimit) {
        super(out);
        this.mainComponent = mainComponent;
        this.component = component;
        this.type = type;
        this.lineLimit = lineLimit;
    }

    @Override
    public void write(byte[] buf, int off, int len) {
        super.write(buf, off, len);
        final String message = new String(buf, off, len);
        final Color col;
        switch (type) {
            case OUT:
                col = Color.GREEN;
                break;
            case ERR:
                col = Color.RED;
                break;
            case NOTICE:
                col = Color.CYAN;
                break;
            case PACKET:
                col = Color.GRAY;
                break;
            default:
                col = Color.BLACK;
        }

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                SimpleAttributeSet attrSet = new SimpleAttributeSet();
                StyleConstants.setForeground(attrSet, col);

                Document doc = component.getDocument();
                Document docMain = mainComponent.getDocument();

                try {
                    String[] docMainInfo = docMain.getText(0, docMain.getLength()).split("\r\n");
                    String[] docInfo = doc.getText(0, doc.getLength()).split("\r\n");
                    if (docMainInfo.length >= lineLimit + 1) {
                        for (int i = 0 ; i <= docMainInfo.length - lineLimit - 1 ; i++) {
                            docMain.remove(0, docMainInfo[i].length() + 2);
                        }
                    }
                    if (docInfo.length >= lineLimit + 1) {
                        for (int i = 0 ; i <= docInfo.length - lineLimit - 1 ; i++) {
                            doc.remove(0, docInfo[i].length() + 2);
                        }
                    }
                    docMain.insertString(docMain.getLength(), message, attrSet);
                    doc.insertString(doc.getLength(), message, attrSet);
                } catch (BadLocationException e) {
                    component.setText("????????????:" + e + "\r\n??????:" + message + "\r\n??????:" + type);
                }
            }
        }
        );
    }
}
