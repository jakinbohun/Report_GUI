package Rep_GUI_Pkg;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import net.miginfocom.swing.MigLayout;


public class Rep_GUI {

	JFrame frame = new JFrame("Report Generator");
	JPanel panel =  new JPanel(); //Title panel

	//Title components
	private JLabel ttlIcon = new JLabel("");
	private JLabel title = new JLabel("Daily Report");
	private JSeparator separator = new JSeparator();
	
	//Report elements components
	private JLabel repTtlLbl = new JLabel("Title: ");
	private JTextField repTtlTxF = new JTextField(12);
	private JLabel fromLbl = new JLabel("From: ");
	private JTextField fromTxF = new JTextField(12);
	private JLabel toLbl = new JLabel("To: ");
	private JTextField toTxF = new JTextField(12);
	private JLabel dateLbl = new JLabel("Date: ");
	private JTextField dateTxF = new JTextField(12);
	
	//Counter  components
	private JLabel relTx = new JLabel("Items Marked Relevant: ");
	private JLabel relCntTx = new JLabel();
	private JButton relCntBtn = new JButton("Add");
	private JLabel irrelTx = new JLabel("Items Marked Irrelevant: ");
	private JLabel irrelCntTx = new JLabel();
	private JButton irrelCntBtn = new JButton("Add");
	private JLabel disTx = new JLabel("Items Marked Dismissed: ");
	private JLabel disCntTx = new JLabel();
	private JButton disCntBtn = new JButton("Add");
	private JLabel movTx = new JLabel("Items Marked Moved: ");
	private JLabel movCntTx = new JLabel();
	private JButton movCntBtn = new JButton("Add");
	private JButton clrCntBtn = new JButton("Clear Count");

	
	//Trending Stories components
	private JTextField trndStr = new JTextField("Please Add Title to Field");
	private JTextArea trndTxArea = new JTextArea(5,50);
	private JScrollPane trndScrPne = new JScrollPane(trndTxArea);
	
	//Analysis components
	private JTextField sisStr = new JTextField("Please Add Title to Field");
	private JTextArea sisTxArea = new JTextArea(5,50);
	private JScrollPane sisScrPne = new JScrollPane(sisTxArea);
	private JButton gnrRprtPDFBtn = new JButton("Generate Report");
	private JButton rstRprtPDFBtn = new JButton("Reset Report");
	
	//Storage variables
	private int relCnt = 0;
	private int irrelCnt = 0;
	private int disCnt = 0;
	private int movCnt = 0;

    //create new java.util.Date object
    private Date date = new Date();
    //Format Date object
    private DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
    private DateFormat dateFormatVis = new SimpleDateFormat("MM/dd/yyyy");
     
    //to convert Date to String, use format method of SimpleDateFormat class.
    String strDate = dateFormat.format(date);
    String strDateVis = dateFormatVis.format(date);
	String myDoc = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();

   
	public Rep_GUI() {
		//Sets layout
		panel.setLayout(new MigLayout()); //Live version
		//panel.setLayout(new MigLayout("debug"));//Testing
		frame.add(panel);
		
		//Adds icon and sets constraints
		ttlIcon.setIcon(new ImageIcon("C:/Users/Public/Pictures/report_icon.jpg"));
		panel.add(ttlIcon, "north,wrap,gapy 20");
		ttlIcon.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Title components
		panel.add(title, "north,wrap, gapx 25px 25px,gapy 25px 15px");
		title.setFont(new Font("Consolas",Font.BOLD,32));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(separator,"growx,gapx 15px 15px, wrap");
		
		//Report label elements
		panel.add(repTtlLbl,"width 35,split2, pushx, gapx 20px 10px, gapy 10px");
		panel.add(repTtlTxF,"pushx, wrap 10,gapx 20px 10px");
		panel.add(fromLbl,"width 35,split2, pushx, gapx 20px 10px, gapy 10px");
		panel.add(fromTxF,"pushx, wrap 10,gapx 20px 10px");
		panel.add(toLbl,"width 35, split2, pushx,gapx 20px 10px");
		panel.add(toTxF, "pushx, wrap 10,gapx 20px 10px");
		panel.add(dateLbl,"width 35,split2,gapx 20px 10px");
		panel.add(dateTxF,"width 75,gapx 20px 10px,wrap");
		dateTxF.setText(strDateVis);
		
		//Alert counts		
		panel.add(relTx, "gapx 20px 10px,cell 0 5,width 160,split3");
		/**relTx.setPreferredSize(new Dimension(10, 10));**/
		relCntTx.setText((Integer.toString(relCnt)));
		panel.add(relCntTx,"sg count,cell 1 5,width 30");
		panel.add(relCntBtn,"wrap,gapx 20px 10px, gapy 5px 5px,width 75");
		
		panel.add(irrelTx, "gapx 20px 10px,width 160,split3");
		irrelCntTx.setText((Integer.toString(relCnt)));
		panel.add(irrelCntTx,"sg count");
		panel.add(irrelCntBtn,"wrap,gapx 20px 10px,gapy 5px 5px,width 75");
		
		panel.add(disTx, "gapx 20px 10px,width 160,split3");
		disCntTx.setText((Integer.toString(relCnt)));
		panel.add(disCntTx,"sg count");
		panel.add(disCntBtn,"wrap,gapx 20px 10px,gapy 5px 5px,width 75");
		
		panel.add(movTx, "gapx 20px 10px,width 160,split3");
		movCntTx.setText((Integer.toString(relCnt)));
		panel.add(movCntTx,"sg count");
		panel.add(movCntBtn,"gapx 20px 25px,gapy 5px 5px, wrap,width 75");
		
		panel.add(clrCntBtn, "gapx 20px 25px, gapy 5px 20px,width 75,wrap");
		
		//Trending Stories elements
		panel.add(trndStr, "width 280px, gapx 20px 20px,wrap");
		trndStr.setFont(new Font("Consolas",Font.BOLD,18));
		trndTxArea.setBorder(BorderFactory.createLoweredBevelBorder());
		trndTxArea.setLineWrap(true);
		trndTxArea.setWrapStyleWord(true);
		panel.add(trndScrPne,"gapx 20px 20px, gapy 10px 15px,wrap");
		
		//Analysis elements
		panel.add(sisStr, "width 280px,gapx 20px 20px,wrap");
		sisStr.setFont(new Font("Consolas",Font.BOLD,18));
		sisTxArea.setBorder(BorderFactory.createLoweredBevelBorder());
		sisTxArea.setLineWrap(true);
		sisTxArea.setWrapStyleWord(true);
		panel.add(sisScrPne,"gapx 20px, gapy 10px 20px, wrap");
		
		//Generate Report Button
		panel.add(gnrRprtPDFBtn, "gapx 20px, gapy 0px 25px,split2");
		panel.add(rstRprtPDFBtn, "gapx 20px, gapy 0px 25px");
		
		//Action listeners for buttons
		relCntBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				relCnt++;
				relCntTx.setText(Integer.toString(relCnt));
			}
		});
		
		irrelCntBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				irrelCnt++;
				irrelCntTx.setText(Integer.toString(irrelCnt));
			}
		});
		
		movCntBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				movCnt++;
				movCntTx.setText(Integer.toString(movCnt));
			}
		});
		
		disCntBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				disCnt++;
				disCntTx.setText(Integer.toString(disCnt));
			}
		});
		
		clrCntBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				relCnt = 0;
				irrelCnt = 0;
				movCnt = 0;
				disCnt = 0;
				relCntTx.setText(Integer.toString(relCnt));
				irrelCntTx.setText(Integer.toString(irrelCnt));
				movCntTx.setText(Integer.toString(movCnt));
				disCntTx.setText(Integer.toString(disCnt));
			}
		});
		
		gnrRprtPDFBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				generatePDFReport();
			}
		});
		
		rstRprtPDFBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rstConfirm = JOptionPane.showOptionDialog(
			             null, "Reset the report?", 
			             "Reset Confirmation", JOptionPane.YES_NO_OPTION, 
			             JOptionPane.QUESTION_MESSAGE, null, null, null);
			    if (rstConfirm == 0) {
			    	repTtlTxF.setText("");
					fromTxF.setText("");
					toTxF.setText("");
					relCnt = 0;
					irrelCnt = 0;
					movCnt = 0;
					disCnt = 0;
					relCntTx.setText(Integer.toString(relCnt));
					irrelCntTx.setText(Integer.toString(irrelCnt));
					movCntTx.setText(Integer.toString(movCnt));
					disCntTx.setText(Integer.toString(disCnt));
					trndStr.setText("Please Add Title to Field");
					trndTxArea.setText("");
					sisStr.setText("Please Add Title to Field");
					sisTxArea.setText("");
			    }
			}
		});
		
		WindowListener exitListener = new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "Close Report Generator?", 
		             "Exit Confirmation", JOptionPane.YES_NO_OPTION, 
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0) {
		           System.exit(0);
		        }
		    }
		};
		
		
		
		/**
		private int close(){ 
		result = JOptionPane.showConfirmDialog(null, "Are you sure you want to close out JFrame?",
				"Select an option",JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			frame.dispose();
		}
		return result;
		}
		
		Preceding code left in for learning purposes. Listener required for user input event 
		(Clicking 'yes' or 'no' to close window) similar to the other button listeners. This one
		differs in that it causes the dialog window to appear and handles the close operation
		itself. 
	     **/
		
		//Frame Operations
		frame.addWindowListener(exitListener);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	
	//PDF creation, formatting, and closing
	private void generatePDFReport(){
		try {
			Document doc = new Document();
			try {
				PdfWriter.getInstance(doc, new FileOutputStream
						("E:/"
								+ "Report_" + strDate + ".pdf"));
			} catch (FileNotFoundException ex) {
				Logger.getLogger(Rep_GUI.class.getName()).log(Level.SEVERE, null, ex);
			}
			doc.open();
			doc.add(new Paragraph(title.getText().toString()));
			doc.add(new Paragraph("\n"));
			doc.add(new Paragraph("Title: " + 
					repTtlTxF.getText().toString()));
			doc.add(new Paragraph("From:  " + 
					fromTxF.getText().toString()));
			doc.add(new Paragraph("To:    " + 
					toTxF.getText().toString()));
			doc.add(new Paragraph("\n\n"));
			doc.add(new Paragraph("Items Marked Relevant:   " + relCntTx.getText()));
			doc.add(new Paragraph("Items Marked Irrelevant: " + irrelCntTx.getText()));
			doc.add(new Paragraph("Items Marked Dismissed:  " + disCntTx.getText()));
			doc.add(new Paragraph("Items Marked Moved:      " + movCntTx.getText()));
			doc.add(new Paragraph("\n\n"));
			doc.add(new Paragraph(trndStr.getText()));
			doc.add(new Paragraph("\n"));
			doc.add(new Paragraph(trndTxArea.getText()));
			doc.add(new Paragraph("\n"));
			doc.add(new Paragraph(sisStr.getText()));
			doc.add(new Paragraph("\n"));
			doc.add(new Paragraph(sisTxArea.getText()));
			doc.close();
		}
		catch (DocumentException ex) {
			Logger.getLogger(Rep_GUI.class.getName()).log(Level.SEVERE, null, ex);
			}
	}
}
