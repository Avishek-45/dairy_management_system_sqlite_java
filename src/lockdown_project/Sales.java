package lockdown_project;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.proteanit.sql.DbUtils;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;



/**
 *
 * @author Dell
 */
public class Sales extends javax.swing.JFrame {
    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    int empty=0;
    
    public Sales() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        conn=javaconnect.connector1();
        Update_table();
        CurrentDate();
        combobox_name();
        combobox_product();
        CurrentTime();
            }
    
    public void close(){
        WindowEvent winClosingEvent=new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }
    
    public void Update_table(){
       try{  String sql="select sales.sales_id,customer.C_ID ,customer.FIRST_NAME,customer.PHONE,SALES.PRODUCT_NAME ,sales.QTy, sales.RATE ,\n" +
                        "(sales.QTY * sales.RATE) as TOTAL_AMT,SALES.PAID_AMT,(SALES.QTY*SALES.RATE)-SALES.PAID_AMT AS DUE_AMT,SALES.DATE from customer \n" +
                        "inner join sales on customer.C_ID = sales.C_ID ";
       
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            jsalestable.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
       
        String query="select sum(QTY *RATE) as Total, sum(Paid_amt) as Paid, sum((QTY*RATE)-PAID_AMT) as Due from sales";
        try {
            pst=conn.prepareStatement(query);
            rs=pst.executeQuery();
            String a=rs.getString("Total");
            String b=rs.getString("Paid");
            String c=rs.getString("Due");
            Total.setText(a);
            Paid.setText(b);
            Due.setText(c);
        }catch (Exception e) {
           JOptionPane.showMessageDialog(null,e);
        }
    }
    
     public void CurrentDate(){
         GregorianCalendar c=new GregorianCalendar();
        int day=c.get(Calendar.DAY_OF_MONTH);
        int month=c.get(Calendar.MONTH);
        int year=c.get(Calendar.YEAR);
        String date = +day+"/"+(month+1)+"/"+year;
        TxtDate.setText(String.valueOf(date));
        
    }
     
     public void CurrentTime(){
         Thread clock=new Thread(){
             public void run(){
                 for(;;){
         
         GregorianCalendar c=new GregorianCalendar();
       int second=c.get(Calendar.SECOND);
       int hour=c.get(Calendar.HOUR_OF_DAY);
        int minute=c.get(Calendar.MINUTE);
        String time="Time:  "+hour+" : "+minute+" : "+second;
        Txttime.setText(String.valueOf(time));
                     try {
                         sleep(1000);
                     } catch (InterruptedException ex) {
                         Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
                     }
        }}
    };
                          clock.start();

                 }
     
      public void combobox_name(){
        try{
            String query="Select * from customer";
            pst=conn.prepareStatement(query);
            rs=pst.executeQuery();
            while(rs.next())
            {
                String FName=rs.getString("FIRST_NAME"); 
                                 
                jComboBox1.addItem(FName); 
                Name_src.addItem(FName);
                jComboBox1.setSelectedItem(""); 
                Name_src.setSelectedItem("");
                AutoCompleteDecorator.decorate(jComboBox1);
                AutoCompleteDecorator.decorate(Name_src);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
    }
      
       public void combobox_product(){
        try{
            String query="Select * from stock";
            pst=conn.prepareStatement(query);
            rs=pst.executeQuery();
            while(rs.next())
            {
                String name=rs.getString("Product_name");                
                jComboBox2.addItem(name);   
                Product_src.addItem(name);
                jComboBox2.setSelectedItem("");   
                Product_src.setSelectedItem("");
                AutoCompleteDecorator.decorate(jComboBox2); 
                AutoCompleteDecorator.decorate(Product_src);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
       
        
        public int check(){
       
        if(jComboBox1.getSelectedItem().toString().equals("") || jComboBox2.getSelectedItem().toString().equals("") || TxtQty.getText().equals("") || TxtRate.getText().equals("") ||TxtPaid.getText().equals("")||TxtDate.getText().equals("")){
            return 1; 
        }
        else{
            return 0;
        }
    }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TxtQty = new javax.swing.JTextField();
        TxtRate = new javax.swing.JTextField();
        TxtPaid = new javax.swing.JTextField();
        TxtDate = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btn_insert = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jsalestable = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        TxtSearch = new javax.swing.JTextField();
        jScrollBar1 = new javax.swing.JScrollBar();
        Txttime = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        FROM = new com.toedter.calendar.JDateChooser();
        TO = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        Product_src = new javax.swing.JComboBox<>();
        Paid = new javax.swing.JLabel();
        Due = new javax.swing.JLabel();
        Total = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Name_src = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        TxtSales = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SALES DETAILS");
        setBackground(new java.awt.Color(51, 51, 51));

        Panel.setBackground(new java.awt.Color(44, 62, 80));
        Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(248, 148, 6));

        jLabel1.setFont(new java.awt.Font("Sitka Heading", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Sales Details");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(587, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(603, 603, 603))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        Panel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1399, 70));

        jLabel3.setFont(new java.awt.Font("Sitka Heading", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Customer Name");
        Panel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 97, -1, 30));

        jLabel4.setFont(new java.awt.Font("Sitka Heading", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Product Name");
        Panel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 163, 142, 30));

        jLabel5.setFont(new java.awt.Font("Sitka Heading", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Qty");
        Panel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 228, 142, 30));

        jLabel6.setFont(new java.awt.Font("Sitka Heading", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Rate");
        Panel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 295, 142, 30));

        jLabel7.setFont(new java.awt.Font("Sitka Heading", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Paid Amt");
        Panel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 362, 142, 30));

        jLabel8.setFont(new java.awt.Font("Sitka Heading", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Date");
        Panel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 429, 142, 30));
        Panel.add(TxtQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 228, 281, 30));

        TxtRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtRateActionPerformed(evt);
            }
        });
        Panel.add(TxtRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 293, 281, 30));
        Panel.add(TxtPaid, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 360, 281, 30));
        Panel.add(TxtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 427, 281, 30));

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));

        btn_insert.setBackground(new java.awt.Color(102, 102, 102));
        btn_insert.setFont(new java.awt.Font("Sitka Heading", 1, 18)); // NOI18N
        btn_insert.setForeground(new java.awt.Color(255, 255, 255));
        btn_insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lockdown_project/save.png"))); // NOI18N
        btn_insert.setText("Insert");
        btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertActionPerformed(evt);
            }
        });

        btn_update.setBackground(new java.awt.Color(102, 102, 102));
        btn_update.setFont(new java.awt.Font("Sitka Heading", 1, 18)); // NOI18N
        btn_update.setForeground(new java.awt.Color(255, 255, 255));
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lockdown_project/upddate.png"))); // NOI18N
        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setBackground(new java.awt.Color(102, 102, 102));
        btn_delete.setFont(new java.awt.Font("Sitka Heading", 1, 18)); // NOI18N
        btn_delete.setForeground(new java.awt.Color(255, 255, 255));
        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lockdown_project/delete.png"))); // NOI18N
        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_reset.setBackground(new java.awt.Color(102, 102, 102));
        btn_reset.setFont(new java.awt.Font("Sitka Heading", 1, 18)); // NOI18N
        btn_reset.setForeground(new java.awt.Color(255, 255, 255));
        btn_reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lockdown_project/Clear.png"))); // NOI18N
        btn_reset.setText("Clear");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(102, 102, 102));
        jButton4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lockdown_project/print.jpg"))); // NOI18N
        jButton4.setText("Print");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(102, 102, 102));
        jButton5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lockdown_project/logout.png"))); // NOI18N
        jButton5.setText("Logout");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_reset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton5)
                                    .addComponent(btn_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 135, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Panel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 450, 210));

        jPanel6.setBackground(new java.awt.Color(44, 62, 80));
        jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jsalestable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sales Id", "C Id", "First Name", "Last  Name", "Phone", "Product Name", "Qty", "Rate", "Total Amt", "Paid Amt", "Due Amt", "Date"
            }
        ));
        jsalestable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jsalestableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jsalestable);

        jPanel6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 100, 860, 380));

        jLabel9.setFont(new java.awt.Font("Sitka Heading", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lockdown_project/search.png"))); // NOI18N
        jLabel9.setText("Search");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, -1, -1));

        TxtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtSearchActionPerformed(evt);
            }
        });
        TxtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtSearchKeyReleased(evt);
            }
        });
        jPanel6.add(TxtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 191, 28));
        jPanel6.add(jScrollBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 100, 19, 380));

        Txttime.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Txttime.setForeground(new java.awt.Color(255, 255, 255));
        jPanel6.add(Txttime, new org.netbeans.lib.awtextra.AbsoluteConstraints(683, 543, 180, 30));

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Show");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, -1, -1));
        jPanel6.add(FROM, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 135, -1));
        jPanel6.add(TO, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 135, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("FROM:");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("TO:");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        jButton2.setBackground(new java.awt.Color(51, 51, 51));
        jButton2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Hide");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jPanel6.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, -1, -1));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Product Search:");
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jPanel6.add(Product_src, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 140, -1));

        Paid.setBackground(new java.awt.Color(51, 51, 51));
        Paid.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Paid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel6.add(Paid, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 530, 100, 30));

        Due.setBackground(new java.awt.Color(51, 51, 51));
        Due.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Due.setForeground(new java.awt.Color(255, 255, 255));
        jPanel6.add(Due, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 570, 100, 30));

        Total.setBackground(new java.awt.Color(51, 51, 51));
        Total.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Total.setForeground(new java.awt.Color(255, 255, 255));
        jPanel6.add(Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 490, 80, 30));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Total Amt:");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 130, 30));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Total Paid:");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 80, 30));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Total Due:");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 70, 30));

        jPanel6.add(Name_src, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 160, -1));

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Name Search:");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, -1, -1));

        Panel.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(453, 71, -1, 600));

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        Panel.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 97, 281, -1));
        Panel.add(TxtSales, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 68, 0, -1));

        Panel.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 163, 281, -1));

        jMenu1.setText("New");

        jMenuItem1.setText("Customer ");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Stock ");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem2MouseClicked(evt);
            }
        });
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem4.setText("Sales");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        jMenuItem3.setText("Change Password");
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem3MouseClicked(evt);
            }
        });
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("About");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Exit");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void TxtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtSearchKeyReleased

        DefaultTableModel table=(DefaultTableModel)jsalestable.getModel();
        String search=TxtSearch.getText().toLowerCase();
        TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>(table);
        jsalestable.setRowSorter(tr);
        if (search.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter("(?i)" + search));
        }
    }//GEN-LAST:event_TxtSearchKeyReleased

    private void TxtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtSearchActionPerformed

    private void jsalestableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jsalestableMouseClicked
        try{
            int row=jsalestable.getSelectedRow();
            String s=(jsalestable.getModel().getValueAt(row,0).toString());
            String query="select * from SALES where SALES_ID='"+s+"'";
            pst=conn.prepareStatement(query);
            rs=pst.executeQuery();
            if(rs.next()){
                String add1=rs.getString("SALES_ID");
                TxtSales.setText(add1);
              
              
                String add4=rs.getString("QTY");
                TxtQty.setText(add4);
                String add5=rs.getString("RATE");
                TxtRate.setText(add5);
                String add6=rs.getString("PAID_AMT");
                TxtPaid.setText(add6);
                String add7=rs.getString("DATE");
                TxtDate.setText(add7);
                  String add2=rs.getString("C_ID");
                String product=rs.getString("PRODUCT_NAME");
                try{
                    String query1="Select * from sales where Product_name='"+product+"'";
                    pst=conn.prepareStatement(query1);
                    rs=pst.executeQuery();
                    if(rs.next())
                    {
                        String name=rs.getString("Product_name");
                        jComboBox2.setSelectedItem(name);

                    }
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null,e);
                }
                
                try{
                    String query2="Select * from customer where c_id='"+add2+"'";
                    pst=conn.prepareStatement(query2);
                    rs=pst.executeQuery();
                    if(rs.next()){
                        String FName=rs.getString("First_name");                       
                        jComboBox1.setSelectedItem(FName);                        
                        }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null,e);
                }
               
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        Update_table();
    }//GEN-LAST:event_jsalestableMouseClicked

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        TxtSales.setText(" ");
        jComboBox1.setSelectedItem("");
        jComboBox2.setSelectedItem(" ");
        TxtQty.setText(" ");
        TxtRate.setText(" ");
        TxtPaid.setText(" ");
        TxtDate.setText(" ");
         GregorianCalendar c=new GregorianCalendar();
        int day=c.get(Calendar.DAY_OF_MONTH);
        int month=c.get(Calendar.MONTH);
        int year=c.get(Calendar.YEAR);
        String date = +day+"/"+(month+1)+"/"+year;
        TxtDate.setText(String.valueOf(date));
    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        String query = "delete from SALES where SALES_ID=?";
        try {
            pst=conn.prepareStatement(query);
            pst.setString(1,TxtSales.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Record Deleted");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        Update_table();
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        String query = " update SALES set PRODUCT_NAME=?,QTY=?,RATE=?,PAID_AMT=?,Date=? where SALES_ID=?";
        try{
            pst=conn.prepareStatement(query);
           
            pst.setString(1,jComboBox2.getSelectedItem().toString());
            pst.setString(2,TxtQty.getText());
            pst.setString(3,TxtRate.getText());
            pst.setString(4,TxtPaid.getText());
            pst.setString(5,TxtDate.getText());
            pst.setString(6,TxtSales.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Record Updated");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        Update_table();
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
       String query="Select * from customer where first_name=?";
        try {
            pst=conn.prepareStatement(query);
            pst.setString(1,jComboBox1.getSelectedItem().toString());  
             empty=check();
            if(empty==1){
                    JOptionPane.showMessageDialog(null,"Please fill all the fields"); 
            }
            
            else{rs=pst.executeQuery();
            if(rs.next()){
                    String id=rs.getString("C_id");
                    try{
                        String query2="insert into sales(C_ID,PRODUCT_NAME,QTY,RATE,PAID_AMT,DATE) values(?,?,?,?,?,?)";
                        pst=conn.prepareStatement(query2);
                        pst.setString(1,id);
                        pst.setString(2,jComboBox2.getSelectedItem().toString());
                        pst.setString(3,TxtQty.getText());
                        pst.setString(4,TxtRate.getText());
                        pst.setString(5,TxtPaid.getText());
                        pst.setString(6,TxtDate.getText());
                        pst.execute();
                        JOptionPane.showMessageDialog(null,"Record Inserted");
                    }
                    catch(Exception e){
                        JOptionPane.showMessageDialog(null,e);
                    }
            }
        }}
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        Update_table();
    }//GEN-LAST:event_btn_insertActionPerformed

    private void TxtRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtRateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtRateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         SimpleDateFormat dateformat=new SimpleDateFormat("dd/MM/YYYY");
       
        
        String From_date = null,To_date = null,product = null ,Name=null;
        
        if(FROM.getDate()!=null){
                From_date=dateformat.format(FROM.getDate());        }
        if(TO.getDate()!=null){
        To_date=dateformat.format(TO.getDate());
        }
        if(Product_src.getSelectedItem()!=null){
        product=Product_src.getSelectedItem().toString();
        }
        if(Name_src.getSelectedItem()!=null){
        Name=Name_src.getSelectedItem().toString();
        }
         
        
    
         if(From_date!=null && To_date!=null && !product.equals("") && !Name.equals("")){
        
       
        String query="select sales.sales_id,customer.C_ID ,customer.FIRST_NAME,customer.PHONE,SALES.PRODUCT_NAME ,sales.QTy, sales.RATE ,\n" +
                        "(sales.QTY * sales.RATE) as TOTAL_AMT,SALES.PAID_AMT,(SALES.QTY*SALES.RATE)-SALES.PAID_AMT AS DUE_AMT,SALES.DATE  \n" +
                        " from customer,sales where sales.DATE between ? and ? and PRODUCT_NAME=? and customer.FIRST_NAME=? and customer.C_ID = sales.C_ID ";
                      
        try {
            pst=conn.prepareStatement(query);
            pst.setString(1,From_date);
            pst.setString(2,To_date);
            pst.setString(3, product);
            pst.setString(4, Name);

            
            rs=pst.executeQuery();
            jsalestable.setModel(DbUtils.resultSetToTableModel(rs));
            if(rs.next())
            {
                rs.close();
                pst.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
         
        String query1="Select sum(sales.QTY * sales.RATE) as TOTAL_AMT,sum(SALES.PAID_AMT)as Paid,sum((SALES.QTY*SALES.RATE)-SALES.PAID_AMT) AS DUE_AMT \n "+
         " from customer,sales where sales.C_ID=customer.C_ID and PRODUCT_NAME='"+product+"'and customer.FIRST_NAME='"+Name+"' and sales.DATE between '"+From_date+"' and '"+To_date+"' ";
        
        try{
         pst=conn.prepareStatement(query1);
                        rs=pst.executeQuery();
                        String a=rs.getString("TOTAL_AMT");
                        String b=rs.getString("Paid");
                        String c=rs.getString("Due_AMT");
                         Total.setText(a);
                         Paid.setText(b);
                         Due.setText(c);

        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
        else if(From_date==null && To_date==null && !product.equals("") && !Name.equals("")){
        
       
        String query="select sales.sales_id,customer.C_ID ,customer.FIRST_NAME,customer.PHONE,SALES.PRODUCT_NAME ,sales.QTy, sales.RATE ,\n" +
                        "(sales.QTY * sales.RATE) as TOTAL_AMT,SALES.PAID_AMT,(SALES.QTY*SALES.RATE)-SALES.PAID_AMT AS DUE_AMT,SALES.DATE  \n" +
                        " from customer,sales where  PRODUCT_NAME=? and customer.FIRST_NAME=? and customer.C_ID = sales.C_ID ";
                      
        try {
            pst=conn.prepareStatement(query);
         
            pst.setString(1, product);
            pst.setString(2, Name);

            
            rs=pst.executeQuery();
            jsalestable.setModel(DbUtils.resultSetToTableModel(rs));
            if(rs.next())
            {
                rs.close();
                pst.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
         
        String query1="Select sum(sales.QTY * sales.RATE) as TOTAL_AMT,sum(SALES.PAID_AMT)as Paid,sum((SALES.QTY*SALES.RATE)-SALES.PAID_AMT) AS DUE_AMT \n "+
         " from customer,sales where sales.C_ID=customer.C_ID and PRODUCT_NAME='"+product+"'and customer.FIRST_NAME='"+Name+"'  ";
        
        try{
         pst=conn.prepareStatement(query1);
                        rs=pst.executeQuery();
                        String a=rs.getString("TOTAL_AMT");
                        String b=rs.getString("Paid");
                        String c=rs.getString("Due_AMT");
                         Total.setText(a);
                         Paid.setText(b);
                         Due.setText(c);

        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
         else if(From_date!=null && To_date!=null && product.equals("") && !Name.equals("")){
        
       
        String query="select sales.sales_id,customer.C_ID ,customer.FIRST_NAME,customer.PHONE,SALES.PRODUCT_NAME ,sales.QTy, sales.RATE ,\n" +
                        "(sales.QTY * sales.RATE) as TOTAL_AMT,SALES.PAID_AMT,(SALES.QTY*SALES.RATE)-SALES.PAID_AMT AS DUE_AMT,SALES.DATE  \n" +
                        " from customer,sales where sales.DATE between ? and ?  and customer.FIRST_NAME=? and customer.C_ID = sales.C_ID ";
                      
        try {
            pst=conn.prepareStatement(query);
            pst.setString(1,From_date);
            pst.setString(2,To_date);
            pst.setString(3, Name);

            
            rs=pst.executeQuery();
            jsalestable.setModel(DbUtils.resultSetToTableModel(rs));
            if(rs.next())
            {
                rs.close();
                pst.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
         
        String query1="Select sum(sales.QTY * sales.RATE) as TOTAL_AMT,sum(SALES.PAID_AMT)as Paid,sum((SALES.QTY*SALES.RATE)-SALES.PAID_AMT) AS DUE_AMT \n "+
         " from customer,sales where sales.C_ID=customer.C_ID and  customer.FIRST_NAME='"+Name+"' and sales.DATE between '"+From_date+"' and '"+To_date+"' ";
        
        try{
         pst=conn.prepareStatement(query1);
                        rs=pst.executeQuery();
                        String a=rs.getString("TOTAL_AMT");
                        String b=rs.getString("Paid");
                        String c=rs.getString("Due_AMT");
                         Total.setText(a);
                         Paid.setText(b);
                         Due.setText(c);

        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
        else if(From_date!=null && To_date!=null && !product.equals("") && Name.equals("")){
        
       
        String query="select sales.sales_id,customer.C_ID ,customer.FIRST_NAME,customer.PHONE,SALES.PRODUCT_NAME ,sales.QTy, sales.RATE ,\n" +
                        "(sales.QTY * sales.RATE) as TOTAL_AMT,SALES.PAID_AMT,(SALES.QTY*SALES.RATE)-SALES.PAID_AMT AS DUE_AMT,SALES.DATE  \n" +
                        " from customer,sales where sales.DATE between ? and ? and PRODUCT_NAME=?  and customer.C_ID = sales.C_ID ";
                      
        try {
            pst=conn.prepareStatement(query);
            pst.setString(1,From_date);
            pst.setString(2,To_date);
            pst.setString(3, product);
           

            
            rs=pst.executeQuery();
            jsalestable.setModel(DbUtils.resultSetToTableModel(rs));
            if(rs.next())
            {
                rs.close();
                pst.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
         
        String query1="Select sum(sales.QTY * sales.RATE) as TOTAL_AMT,sum(SALES.PAID_AMT)as Paid,sum((SALES.QTY*SALES.RATE)-SALES.PAID_AMT) AS DUE_AMT \n "+
         " from customer,sales where sales.C_ID=customer.C_ID and PRODUCT_NAME='"+product+"' and sales.DATE between '"+From_date+"' and '"+To_date+"' ";
        
        try{
         pst=conn.prepareStatement(query1);
                        rs=pst.executeQuery();
                        String a=rs.getString("TOTAL_AMT");
                        String b=rs.getString("Paid");
                        String c=rs.getString("Due_AMT");
                         Total.setText(a);
                         Paid.setText(b);
                         Due.setText(c);

        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
        
         
         else if(From_date!=null && To_date!=null && product.equals("") && Name.equals("")){
         
          String query="select sales.sales_id,customer.C_ID ,customer.FIRST_NAME,customer.PHONE,SALES.PRODUCT_NAME ,sales.QTy, sales.RATE ,\n" +
                        "(sales.QTY * sales.RATE) as TOTAL_AMT,SALES.PAID_AMT,(SALES.QTY*SALES.RATE)-SALES.PAID_AMT AS DUE_AMT,SALES.DATE  \n" +
                        " from customer,sales where sales.DATE between ? and ?  and customer.C_ID = sales.C_ID ";
                      
        try {
            pst=conn.prepareStatement(query);
            pst.setString(1,From_date);
            pst.setString(2,To_date);
            
            rs=pst.executeQuery();
            jsalestable.setModel(DbUtils.resultSetToTableModel(rs));
            if(rs.next())
            {
                rs.close();
                pst.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
         String query1="select sum(QTY * RATE) as Total, sum(Paid_amt) as Paid, sum((QTY*RATE)-PAID_AMT) as Due \n "+
         " from sales,customer where sales.C_ID = customer.C_ID  and sales.date between '"+From_date+"' and '"+To_date+"' ";
        
        try{
         pst=conn.prepareStatement(query1);
                        rs=pst.executeQuery();
                        String a=rs.getString("Total");
                        String b=rs.getString("Paid");
                        String c=rs.getString("Due");
                        


                            Total.setText(a);
                         Paid.setText(b);
                         Due.setText(c);

        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
         
         else if( !product.equals("")){
         String query="select sales.sales_id,customer.C_ID ,customer.FIRST_NAME,customer.PHONE,SALES.PRODUCT_NAME ,sales.QTy, sales.RATE ,\n" +
                        "(sales.QTY * sales.RATE) as TOTAL_AMT,SALES.PAID_AMT,(SALES.QTY*SALES.RATE)-SALES.PAID_AMT AS DUE_AMT,SALES.DATE  \n" +
                        " from customer,sales where  PRODUCT_NAME=? and customer.C_ID = sales.C_ID ";
                      
        try {
            pst=conn.prepareStatement(query);
            
            pst.setString(1, product);
            
            rs=pst.executeQuery();
            jsalestable.setModel(DbUtils.resultSetToTableModel(rs));
            if(rs.next())
            {
                rs.close();
                pst.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
         }
         
         String query1="select sum(QTY * RATE) as Total, sum(Paid_amt) as Paid, sum((QTY*RATE)-PAID_AMT) as Due \n "+
         " from sales,customer where sales.C_ID = customer.C_ID  and PRODUCT_NAME='"+product+"' ";
        
        try{
         pst=conn.prepareStatement(query1);
                        rs=pst.executeQuery();
                        String a=rs.getString("Total");
                        String b=rs.getString("Paid");
                        String c=rs.getString("Due");
                        
                         Total.setText(a);
                         Paid.setText(b);
                         Due.setText(c);

        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }}
         else if(From_date==null && To_date==null && product.equals("") && Name.equals("")){
                    Update_table();
                    
         }
          else if( !Name.equals("")){
         String query="select sales.sales_id,customer.C_ID ,customer.FIRST_NAME,customer.PHONE,SALES.PRODUCT_NAME ,sales.QTy, sales.RATE ,\n" +
                        "(sales.QTY * sales.RATE) as TOTAL_AMT,SALES.PAID_AMT,(SALES.QTY*SALES.RATE)-SALES.PAID_AMT AS DUE_AMT,SALES.DATE  \n" +
                        " from customer,sales where  customer.FIRST_NAME=? and customer.C_ID = sales.C_ID ";
                      
        try {
            pst=conn.prepareStatement(query);
            
            pst.setString(1, Name);
            
            rs=pst.executeQuery();
            jsalestable.setModel(DbUtils.resultSetToTableModel(rs));
            if(rs.next())
            {
                rs.close();
                pst.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
         }
         
         String query1="select sum(QTY * RATE) as Total, sum(Paid_amt) as Paid, sum((QTY*RATE)-PAID_AMT) as Due \n "+
         " from sales,customer where sales.C_ID = customer.C_ID  and customer.FIRST_NAME='"+Name+"' ";
        
        try{
         pst=conn.prepareStatement(query1);
                        rs=pst.executeQuery();
                        String a=rs.getString("Total");
                        String b=rs.getString("Paid");
                        String c=rs.getString("Due");
                        
                         Total.setText(a);
                         Paid.setText(b);
                         Due.setText(c);

        } catch (SQLException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }}
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
                FROM.setDate(null);
                TO.setDate(null);
                Product_src.setSelectedItem("");
                Total.setText("");
                                Paid.setText("");
                Due.setText("");
                Name_src.setSelectedItem("");
                        Update_table();

                

    }//GEN-LAST:event_jButton2MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        Customer cus=new Customer();
        cus.setVisible(true);
                this.dispose();

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked

    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void jMenuItem2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MouseClicked
     
    }//GEN-LAST:event_jMenuItem2MouseClicked

    private void jMenuItem3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MouseClicked
   
    }//GEN-LAST:event_jMenuItem3MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
close();        
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        Stock cus=new Stock();
        cus.setVisible(true);
                this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        Change cus=new Change();
        cus.setVisible(true);
                this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu4ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try{
            boolean print=jsalestable.print();
            if(!print){
                JOptionPane.showMessageDialog(null,"Unable yo print!!");
            }
        }catch(java.awt.print.PrinterException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        Login ln=new Login();
        ln.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
         Sales s=new Sales();
        s.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Due;
    private com.toedter.calendar.JDateChooser FROM;
    private javax.swing.JComboBox<String> Name_src;
    private javax.swing.JLabel Paid;
    private javax.swing.JPanel Panel;
    private javax.swing.JComboBox<String> Product_src;
    private com.toedter.calendar.JDateChooser TO;
    private javax.swing.JLabel Total;
    private javax.swing.JTextField TxtDate;
    private javax.swing.JTextField TxtPaid;
    private javax.swing.JTextField TxtQty;
    private javax.swing.JTextField TxtRate;
    private javax.swing.JTextField TxtSales;
    private javax.swing.JTextField TxtSearch;
    private javax.swing.JLabel Txttime;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_update;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jsalestable;
    // End of variables declaration//GEN-END:variables
}
