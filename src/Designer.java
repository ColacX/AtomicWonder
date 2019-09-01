import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.net.URLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import javax.swing.JOptionPane;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.LayoutManager;
import javax.swing.JRadioButton;
import java.awt.Image;
import javax.swing.JSlider;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JFrame;

// 
// Decompiled by Procyon v0.5.36
// 

public class Designer extends JFrame {
    static String gallery;
    double blocksize;
    Graphics2D g2d;
    static int height;
    static int width;
    String name;
    Color[] palette;
    static int[][] picture;
    int currentColor;
    JComboBox<String> cbHeight;
    JComboBox<String> cbWidth;
    int maxPixels;
    RadioListener myListener;
    JColorChooser cc;
    JButton btnColor1;
    JButton btnColor2;
    JButton btnColor3;
    JPanel panelPalette;
    JButton brushButton1;
    JButton brushButton2;
    JButton brushButton3;
    int curPalette;
    JButton eraseButton;
    JComboBox<String> cbCommPort;
    JPanel pnlStep1;
    JPanel pnlStep2;
    private JLabel lblWidthXHeight;
    private JPanel panel;
    boolean settingSize;
    boolean repainting;
    String hoopPort;
    JFormattedTextField txtName;
    JButton btnConnect;
    JSlider sliderSpacing;
    JSlider sliderSpeed;
    Image imgConnect;
    boolean connected;
    private JPanel2 panelMain;
    Thread getConnected;
    JPanel panelColor1;
    JPanel panelColor2;
    JPanel panelColor3;
    JPanel panelErase;
    JPanel panelSearching;
    JRadioButton rdbtnPixels1;
    JRadioButton rdbtnPixels2;
    JRadioButton rdbtnPixels3;

    static {
        Designer.gallery = "";
        Designer.height = 0;
        Designer.width = 0;
        Designer.picture = new int[][] { new int[30], new int[30], new int[30], new int[30], new int[30], new int[30],
                new int[30], new int[30], new int[30], new int[30], new int[30], new int[30], new int[30], new int[30],
                new int[30], new int[30], new int[30], new int[30], new int[30], new int[30], new int[30], new int[30],
                new int[30], new int[30], new int[30], new int[30], new int[30], new int[30], new int[30],
                new int[30] };
    }

    public Designer() {
        this.name = "";
        this.palette = new Color[] { Color.black, Color.red, Color.blue, Color.green };
        this.currentColor = 1;
        this.maxPixels = 0;
        this.myListener = null;
        this.curPalette = 1;
        this.settingSize = false;
        this.repainting = false;
        this.hoopPort = "";
        this.connected = false;
        this.getConnected = null;
        (this.panelSearching = new JPanel()).setLayout(null);
        this.panelSearching.setBackground(new Color(29, 29, 29));
        this.panelSearching.setOpaque(true);
        this.panelSearching.setBounds(0, 0, 700, 550);
        this.panelSearching.setVisible(true);
        final Image imgConnect = new ImageIcon(this.getClass().getResource("searching.jpg")).getImage();
        final JLabel picLabel = new JLabel(new ImageIcon(imgConnect));
        picLabel.setBounds(0, 0, 700, 500);
        this.panelSearching.add(picLabel);
        this.add(this.panelSearching);
        final ButtonGroup group = new ButtonGroup();
        (this.panelMain = new JPanel2()).setBackground(new Color(29, 29, 29));
        this.panelMain.setOpaque(true);
        this.panelMain.setBounds(0, 0, 700, 500);
        this.panelMain.setLayout(null);
        this.panelMain.setVisible(false);
        (this.brushButton2 = new JButton("")).setBounds(390, 34, 30, 30);
        this.brushButton2.setOpaque(true);
        this.brushButton2.setBackground(this.palette[2]);
        this.brushButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                Designer.this.currentColor = 2;
                Designer.this.brushButton2.setIcon(new ImageIcon(this.getClass().getResource("brush.png")));
                Designer.this.brushButton1.setIcon(null);
                Designer.this.brushButton3.setIcon(null);
                Designer.this.eraseButton.setIcon(null);
            }
        });
        (this.eraseButton = new JButton("")).setBounds(540, 34, 30, 30);
        this.eraseButton.setOpaque(true);
        this.eraseButton.setBackground(this.palette[0]);
        this.eraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                Designer.this.currentColor = 0;
                Designer.this.brushButton1.setIcon(null);
                Designer.this.brushButton2.setIcon(null);
                Designer.this.brushButton3.setIcon(null);
                Designer.this.eraseButton.setIcon(new ImageIcon(this.getClass().getResource("eraser.png")));
            }
        });
        (this.brushButton3 = new JButton("")).setBounds(465, 34, 30, 30);
        this.brushButton3.setOpaque(true);
        this.brushButton3.setBackground(this.palette[3]);
        this.brushButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                Designer.this.currentColor = 3;
                Designer.this.brushButton3.setIcon(new ImageIcon(this.getClass().getResource("brush.png")));
                Designer.this.brushButton2.setIcon(null);
                Designer.this.brushButton1.setIcon(null);
                Designer.this.eraseButton.setIcon(null);
            }
        });
        this.panelMain.add(this.brushButton3);
        this.panelMain.add(this.eraseButton);
        (this.brushButton1 = new JButton("")).setBounds(315, 34, 30, 30);
        this.panelMain.add(this.brushButton1);
        this.brushButton1.setOpaque(true);
        this.brushButton1.setIcon(new ImageIcon(this.getClass().getResource("brush.png")));
        this.brushButton1.setBackground(this.palette[1]);
        this.brushButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                Designer.this.currentColor = 1;
                Designer.this.brushButton1.setIcon(new ImageIcon(this.getClass().getResource("brush.png")));
                Designer.this.brushButton2.setIcon(null);
                Designer.this.brushButton3.setIcon(null);
                Designer.this.eraseButton.setIcon(null);
            }
        });
        this.panelMain.add(this.brushButton2);
        (this.txtName = new JFormattedTextField()).setBounds(10, 296, 149, 20);
        this.panelMain.add(this.txtName);
        final JLabel lblNewLabel = new JLabel("Name Your Mode");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(10, 276, 168, 14);
        lblNewLabel.setFont(new Font("Tahoma", 1, 11));
        this.panelMain.add(lblNewLabel);
        final JLabel lblNumberOfColors = new JLabel("Number of Colors");
        lblNumberOfColors.setForeground(Color.WHITE);
        lblNumberOfColors.setBounds(11, 19, 111, 14);
        lblNumberOfColors.setFont(new Font("Tahoma", 1, 11));
        this.panelMain.add(lblNumberOfColors);
        this.myListener = new RadioListener();
        (this.rdbtnPixels1 = new JRadioButton("1 (320 pixels max)")).setForeground(Color.WHITE);
        this.rdbtnPixels1.setBounds(11, 39, 168, 23);
        this.rdbtnPixels1
                .setFont(this.rdbtnPixels1.getFont().deriveFont(this.rdbtnPixels1.getFont().getStyle() & 0xFFFFFFFE));
        this.rdbtnPixels1.setActionCommand("1");
        this.rdbtnPixels1.addActionListener(this.myListener);
        this.rdbtnPixels1.setBackground(new Color(29, 29, 29));
        this.panelMain.add(this.rdbtnPixels1);
        (this.rdbtnPixels2 = new JRadioButton("2 (200 pixels max)")).setForeground(Color.WHITE);
        this.rdbtnPixels2.setBounds(11, 58, 168, 23);
        this.rdbtnPixels2
                .setFont(this.rdbtnPixels2.getFont().deriveFont(this.rdbtnPixels2.getFont().getStyle() & 0xFFFFFFFE));
        this.rdbtnPixels2.setActionCommand("2");
        this.rdbtnPixels2.addActionListener(this.myListener);
        this.rdbtnPixels2.setBackground(new Color(29, 29, 29));
        this.panelMain.add(this.rdbtnPixels2);
        (this.rdbtnPixels3 = new JRadioButton("3 (160 pixels max)")).setForeground(Color.WHITE);
        this.rdbtnPixels3.setBounds(11, 78, 168, 23);
        this.rdbtnPixels3
                .setFont(this.rdbtnPixels3.getFont().deriveFont(this.rdbtnPixels3.getFont().getStyle() & 0xFFFFFFFE));
        this.rdbtnPixels3.setActionCommand("3");
        this.rdbtnPixels3.addActionListener(this.myListener);
        this.rdbtnPixels3.setBackground(new Color(29, 29, 29));
        this.panelMain.add(this.rdbtnPixels3);
        group.add(this.rdbtnPixels1);
        group.add(this.rdbtnPixels2);
        group.add(this.rdbtnPixels3);
        final JLabel lblImageSize = new JLabel("Image Size");
        lblImageSize.setForeground(Color.WHITE);
        lblImageSize.setBounds(11, 119, 77, 14);
        lblImageSize.setFont(new Font("Tahoma", 1, 11));
        this.panelMain.add(lblImageSize);
        (this.cbHeight = new JComboBox<String>()).setEnabled(false);
        this.cbHeight.setBounds(11, 139, 70, 20);
        this.cbHeight.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent arg0) {
                if (!Designer.this.settingSize) {
                    Designer.this.settingSize = true;
                    Designer.height = Integer.valueOf((String) Designer.this.cbHeight.getSelectedItem());
                    Designer.this.repaint();
                    int maxWidth = (int) Math.floor(Designer.this.maxPixels
                            / Integer.valueOf((String) Designer.this.cbHeight.getSelectedItem()));
                    if (maxWidth > 30) {
                        maxWidth = 30;
                    }
                    final String[] widths = new String[maxWidth];
                    widths[0] = "";
                    int idxWidth = Designer.this.cbWidth.getSelectedIndex();
                    for (int i = 1; i < maxWidth; ++i) {
                        widths[i] = Integer.toString(i + 1);
                    }
                    if (maxWidth < Designer.width) {
                        idxWidth = maxWidth - 1;
                        Designer.width = maxWidth;
                    }
                    Designer.this.cbWidth.setModel(new DefaultComboBoxModel<String>(widths));
                    Designer.this.cbWidth.setSelectedIndex(idxWidth);
                    Designer.this.settingSize = false;
                }
            }
        });
        this.cbHeight.setModel(new DefaultComboBoxModel<String>(
                new String[] { "", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
                        "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));
        this.panelMain.add(this.cbHeight);
        final JButton btnSave = new JButton("Save to Community Gallery");
        btnSave.setBounds(10, 357, 220, 23);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                String errMsg = "";
                String pattern = "";
                boolean gotData = false;
                float[] hsv = new float[3];
                hsv = Color.RGBtoHSB(Designer.this.palette[1].getRed(), Designer.this.palette[1].getGreen(),
                        Designer.this.palette[1].getBlue(), hsv);
                if (hsv[1] < 0.1 && hsv[2] > 0.3) {
                    pattern = pattern.concat("211,");
                } else if (hsv[1] < 0.1) {
                    pattern = pattern.concat("210,");
                } else {
                    pattern = pattern.concat(Integer.toString(Math.round(hsv[0] * 200.0f))).concat(",");
                }
                hsv = Color.RGBtoHSB(Designer.this.palette[2].getRed(), Designer.this.palette[2].getGreen(),
                        Designer.this.palette[2].getBlue(), hsv);
                if (hsv[1] < 0.1 && hsv[2] > 0.3) {
                    pattern = pattern.concat("211,");
                } else if (hsv[1] < 0.1) {
                    pattern = pattern.concat("210,");
                } else {
                    pattern = pattern.concat(Integer.toString(Math.round(hsv[0] * 200.0f))).concat(",");
                }
                hsv = Color.RGBtoHSB(Designer.this.palette[3].getRed(), Designer.this.palette[3].getGreen(),
                        Designer.this.palette[3].getBlue(), hsv);
                if (hsv[1] < 0.1 && hsv[2] > 0.3) {
                    pattern = pattern.concat("211,");
                } else if (hsv[1] < 0.1) {
                    pattern = pattern.concat("210,");
                } else {
                    pattern = pattern.concat(Integer.toString(Math.round(hsv[0] * 200.0f))).concat(",");
                }
                pattern = pattern.concat("0,");
                pattern = pattern.concat(Integer.toString(Designer.this.sliderSpacing.getValue())).concat(",");
                pattern = pattern.concat(Integer.toString(100 - Designer.this.sliderSpeed.getValue())).concat(",");
                pattern = pattern.concat("0,");
                int colors = 4;
                if (Designer.this.maxPixels == 320) {
                    colors = 2;
                }
                if (Designer.this.maxPixels == 200) {
                    colors = 3;
                }
                if (Designer.this.maxPixels == 160) {
                    colors = 4;
                }
                pattern = pattern.concat(Integer.toString(colors)).concat(",");
                pattern = pattern.concat(Integer.toString(Designer.height)).concat(",");
                pattern = pattern.concat(Integer.toString(Designer.width)).concat(",");
                for (int x = 0; x < Designer.width; ++x) {
                    for (int y = 0; y < Designer.height; ++y) {
                        final String thisData = Integer.toString(Designer.picture[x][y]);
                        if (thisData != "0") {
                            gotData = true;
                        }
                        pattern = pattern.concat(thisData).concat(",");
                    }
                }
                System.out.println(pattern);
                if (!gotData) {
                    errMsg = errMsg.concat("Please draw a pattern before saving.\n");
                }
                if (Designer.this.txtName.getText().length() < 1) {
                    errMsg = errMsg.concat("Please give your work of art a name first.\n");
                }
                if (errMsg.length() > 0) {
                    JOptionPane.showMessageDialog(null, errMsg);
                } else {
                    try {
                        final String url = "http://www.astralhoops.com/savepattern?title="
                                .concat(URLEncoder.encode(Designer.this.txtName.getText(), "UTF-8")).concat("&pattern=")
                                .concat(pattern);
                        System.out.println(url);
                        final URL savepattern = new URL(url);
                        final URLConnection spc = savepattern.openConnection();
                        final BufferedReader in = new BufferedReader(new InputStreamReader(spc.getInputStream()));
                        while (in.readLine() != null) {
                        }
                        in.close();
                        JOptionPane.showMessageDialog(null, "Saved!");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
        this.panelMain.add(btnSave);
        final JButton btnDownload = new JButton("Download to Atomic");
        btnDownload.setBounds(10, 390, 220, 23);
        btnDownload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                if (!Designer.this.connected) {
                    JOptionPane.showMessageDialog(null,
                            "ATOMIC NOT CONNECTED\n\nPlease plug your Atomic into the USB port of your computer, then click Connect to Atomic.");
                } else {
                    final int[] data = new int[50];
                    float[] hsv = new float[3];
                    hsv = Color.RGBtoHSB(Designer.this.palette[1].getRed(), Designer.this.palette[1].getGreen(),
                            Designer.this.palette[1].getBlue(), hsv);
                    data[0] = Math.round(hsv[0] * 200.0f);
                    if (hsv[1] < 0.1) {
                        data[0] = 211;
                    }
                    System.out.println("RGB");
                    System.out.println(Designer.this.palette[1].getRed());
                    System.out.println(Designer.this.palette[1].getBlue());
                    System.out.println(Designer.this.palette[1].getGreen());
                    System.out.println("HSV:");
                    System.out.println(hsv[0]);
                    hsv = Color.RGBtoHSB(Designer.this.palette[2].getRed(), Designer.this.palette[2].getGreen(),
                            Designer.this.palette[2].getBlue(), hsv);
                    data[1] = Math.round(hsv[0] * 200.0f);
                    if (hsv[1] < 0.1) {
                        data[1] = 211;
                    }
                    hsv = Color.RGBtoHSB(Designer.this.palette[3].getRed(), Designer.this.palette[3].getGreen(),
                            Designer.this.palette[3].getBlue(), hsv);
                    data[2] = Math.round(hsv[0] * 200.0f);
                    if (hsv[1] < 0.1) {
                        data[2] = 211;
                    }
                    data[3] = 0; // what is 3?
                    // data[4] = Designer.this.sliderSpacing.getValue();
                    data[4] = 0; // led spacing
                    // data[5] = 100 - Designer.this.sliderSpeed.getValue();
                    data[5] = 1; // slider speed
                    data[6] = 0; // what is 6 this?
                    // what happend to 7
                    data[8] = Designer.height;
                    data[9] = Designer.width;
                    final int[] rawData = new int[Designer.height * Designer.width + 7];
                    for (int x = 0; x < Designer.width; ++x) {
                        for (int y = 0; y < Designer.height; ++y) {
                            rawData[x * Designer.height + y] = Designer.picture[x][y];
                        }
                    }
                    for (int x = 0; x < 7; ++x) {
                        rawData[x + Designer.height * Designer.width] = 0;
                    }
                    int j = 10;
                    int colors = 4;
                    if (Designer.this.maxPixels == 320) {
                        colors = 2;
                    }
                    if (Designer.this.maxPixels == 200) {
                        colors = 3;
                    }
                    if (Designer.this.maxPixels == 160) {
                        colors = 4;
                    }
                    data[7] = colors; // number of colors
                    int compression = 4;
                    if (colors == 4) {
                        compression = 4;
                    }
                    if (colors == 3) {
                        compression = 5;
                    }
                    if (colors == 2) {
                        compression = 8;
                    }
                    for (int i = 0; i < Designer.width * Designer.height; i += compression) {
                        if (colors == 4) {
                            data[j] = rawData[i] + rawData[i + 1] * 4 + rawData[i + 2] * 16 + rawData[i + 3] * 64;
                        }
                        if (colors == 3) {
                            data[j] = rawData[i] + rawData[i + 1] * 3 + rawData[i + 2] * 9 + rawData[i + 3] * 27
                                    + rawData[i + 4] * 81;
                        }
                        if (colors == 2) {
                            data[j] = rawData[i] + rawData[i + 1] * 2 + rawData[i + 2] * 4 + rawData[i + 3] * 8
                                    + rawData[i + 4] * 16 + rawData[i + 5] * 32 + rawData[i + 6] * 64
                                    + rawData[i + 7] * 128;
                        }
                        ++j;
                    }
                    JOptionPane.showMessageDialog(null, "Click OK to start the transfer.");
                    try {
                        var deviceService = new DeviceService();
                        deviceService.Transfer(Designer.this.hoopPort, data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        this.panelMain.add(btnDownload);
        (this.panelColor1 = new JPanel()).setBounds(300, 28, 60, 42);
        this.panelColor1.setBackground(this.palette[1]);
        this.panelMain.add(this.panelColor1);
        (this.panelColor2 = new JPanel()).setBackground(this.palette[2]);
        this.panelColor2.setBounds(375, 28, 60, 42);
        this.panelMain.add(this.panelColor2);
        (this.panelColor3 = new JPanel()).setBackground(this.palette[3]);
        this.panelColor3.setBounds(450, 27, 60, 42);
        this.panelMain.add(this.panelColor3);
        (this.panelErase = new JPanel()).setBackground(this.palette[0]);
        this.panelErase.setBounds(525, 27, 60, 42);
        this.panelMain.add(this.panelErase);
        (this.btnColor1 = new JButton("EDIT")).setFont(new Font("Tahoma", 0, 10));
        this.btnColor1.setBounds(300, 72, 60, 14);
        this.btnColor1.setOpaque(true);
        this.btnColor1.setBackground(this.palette[1]);
        this.btnColor1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                Designer.this.curPalette = 1;
                Designer.this.showPaletteChooser();
            }
        });
        this.panelMain.add(this.btnColor1);
        (this.btnColor2 = new JButton("EDIT")).setFont(new Font("Tahoma", 0, 10));
        this.btnColor2.setBounds(375, 72, 61, 15);
        this.btnColor2.setOpaque(true);
        this.btnColor2.setBackground(this.palette[2]);
        this.btnColor2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                Designer.this.curPalette = 2;
                Designer.this.showPaletteChooser();
            }
        });
        this.panelMain.add(this.btnColor2);
        (this.btnColor3 = new JButton("EDIT")).setFont(new Font("Tahoma", 0, 10));
        this.btnColor3.setBounds(450, 71, 61, 15);
        this.btnColor3.setOpaque(true);
        this.btnColor3.setBackground(this.palette[3]);
        this.btnColor3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                Designer.this.curPalette = 3;
                Designer.this.showPaletteChooser();
            }
        });
        this.panelMain.add(this.btnColor3);
        (this.pnlStep1 = new JPanel()).setBounds(1, 13, 294, 95);
        this.pnlStep1.setBorder(new LineBorder(new Color(0, 255, 0), 4));
        this.pnlStep1.setBackground(UIManager.getColor("Button.background"));
        this.pnlStep1.setBackground(new Color(29, 29, 29));
        this.panelMain.add(this.pnlStep1);
        this.pnlStep1.setLayout(null);
        this.pnlStep1.setVisible(true);
        final JLabel lblHowManyColors = new JLabel("How Many Colors?");
        lblHowManyColors.setForeground(Color.GREEN);
        lblHowManyColors.setFont(new Font("Tahoma", 1, 15));
        lblHowManyColors.setBounds(146, 10, 138, 17);
        this.pnlStep1.add(lblHowManyColors);
        final JLabel lblClickAColor = new JLabel("Click a color to select a brush.  Use black to erase.");
        lblClickAColor.setForeground(Color.WHITE);
        lblClickAColor.setBounds(297, 10, 265, 14);
        lblClickAColor.setFont(new Font("Tahoma", 0, 11));
        this.panelMain.add(lblClickAColor);
        (this.cbWidth = new JComboBox<String>()).setBounds(141, 139, 70, 20);
        this.panelMain.add(this.cbWidth);
        this.cbWidth.setEnabled(false);
        this.cbWidth.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent arg0) {
                if (!Designer.this.settingSize) {
                    Designer.this.settingSize = true;
                    Designer.width = Integer.valueOf((String) Designer.this.cbWidth.getSelectedItem());
                    Designer.this.repaint();
                    int maxHeight = (int) Math.floor(Designer.this.maxPixels
                            / Integer.valueOf((String) Designer.this.cbWidth.getSelectedItem()));
                    if (maxHeight > 30) {
                        maxHeight = 30;
                    }
                    final String[] heights = new String[maxHeight];
                    heights[0] = "";
                    int idxHeight = Designer.height - 1;
                    System.out.println(idxHeight);
                    for (int i = 1; i < maxHeight; ++i) {
                        heights[i] = Integer.toString(i + 1);
                    }
                    if (maxHeight < Designer.height) {
                        idxHeight = maxHeight - 1;
                        Designer.height = maxHeight;
                    }
                    Designer.this.cbHeight.setModel(new DefaultComboBoxModel<String>(heights));
                    Designer.this.cbHeight.setSelectedIndex(idxHeight);
                    Designer.this.pnlStep2.setVisible(false);
                    Designer.this.settingSize = false;
                }
            }
        });
        this.cbWidth.setModel(new DefaultComboBoxModel<String>(new String[] { "" }));
        (this.lblWidthXHeight = new JLabel("height X                      width")).setBounds(87, 139, 184, 18);
        this.panelMain.add(this.lblWidthXHeight);
        this.lblWidthXHeight.setForeground(Color.WHITE);
        this.lblWidthXHeight.setFont(new Font("Tahoma", 0, 13));
        final JLabel lblSpacingBetweenPattern = new JLabel("Spacing between pattern");
        lblSpacingBetweenPattern.setFont(new Font("Tahoma", 1, 11));
        lblSpacingBetweenPattern.setForeground(Color.WHITE);
        lblSpacingBetweenPattern.setBounds(10, 178, 149, 14);
        this.panelMain.add(lblSpacingBetweenPattern);
        final JLabel lblFrameSpeed = new JLabel("Frame speed (0-100)");
        lblFrameSpeed.setFont(new Font("Tahoma", 1, 11));
        lblFrameSpeed.setForeground(Color.WHITE);
        lblFrameSpeed.setBounds(10, 224, 138, 14);
        this.panelMain.add(lblFrameSpeed);
        (this.sliderSpeed = new JSlider()).setValue(100);
        this.sliderSpeed.setBounds(12, 239, 200, 26);
        this.sliderSpeed.setBackground(new Color(29, 29, 29));
        this.panelMain.add(this.sliderSpeed);
        (this.sliderSpacing = new JSlider()).setValue(5);
        this.sliderSpacing.setMaximum(20);
        this.sliderSpacing.setBounds(10, 192, 200, 26);
        this.sliderSpacing.setBackground(new Color(29, 29, 29));
        this.panelMain.add(this.sliderSpacing);
        (this.pnlStep2 = new JPanel()).setBounds(1, 112, 294, 57);
        this.pnlStep2.setBorder(new LineBorder(Color.GREEN, 4));
        this.pnlStep2.setBackground(new Color(29, 29, 29));
        this.panelMain.add(this.pnlStep2);
        this.pnlStep2.setLayout(null);
        this.pnlStep2.setVisible(false);
        final JLabel lblWhatSize = new JLabel("What Size?");
        lblWhatSize.setBounds(200, 10, 84, 19);
        lblWhatSize.setForeground(Color.GREEN);
        lblWhatSize.setFont(new Font("Tahoma", 1, 15));
        this.pnlStep2.add(lblWhatSize);
        this.add(this.panelMain);
        this.connectHoop();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent e) {
            }

            @Override
            public void mouseReleased(final MouseEvent e) {
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
            }

            @Override
            public void mouseExited(final MouseEvent e) {
            }

            @Override
            public void mouseClicked(final MouseEvent e) {
                System.out.println("Mouse clicked");
                int mx = e.getX();
                int my = e.getY();
                System.out.println("mouse: " + mx + ":" + my);
                var x = (int) Math.floor((mx - 310) / Designer.this.blocksize);
                var y = (int) Math.floor((my - 180) / Designer.this.blocksize);
                System.out.println("canvas: " + x + ":" + y);
                Designer.picture[x][y] = Designer.this.currentColor;
                Designer.this.repainting = true;
                Designer.this.repaint();
                Designer.this.repainting = false;
                Designer.this.setColorSelectEnable();
            }
        });
    }

    public static void main(final String[] args) {
        final Designer d = new Designer();
        d.setSize(1800, 1200);
        d.setVisible(true);
        d.setBackground(new Color(29, 29, 29));
        if (args.length > 0) {
            Designer.gallery = args[0];
        } else {
            Designer.gallery = " ";
        }
        final JFrame parent = new JFrame();
    }

    public void loadGallery() {
        if (Designer.gallery == null) {
            Designer.gallery = " ";
        }
        Designer.gallery = Designer.gallery.replace(" ", "");
        final int gallerylen = Designer.gallery.length();
        if (gallerylen > 0) {
            final String[] sgdata = Designer.gallery.split(",");
            final int sgdatalen = sgdata.length;
            final int[] gdata = new int[sgdatalen];
            for (int i = 0; i < sgdatalen; ++i) {
                gdata[i] = Integer.parseInt(sgdata[i]);
                System.out.print(gdata[i]);
                System.out.print(", ");
            }
            for (int i = 0; i < 3; ++i) {
                if (gdata[i] >= 200) {
                    switch (gdata[i]) {
                    case 210: {
                        this.palette[i + 1] = Color.getHSBColor(0.0f, 0.0f, 0.0f);
                        break;
                    }
                    case 211: {
                        this.palette[i + 1] = Color.getHSBColor(0.0f, 0.0f, 1.0f);
                        break;
                    }
                    default: {
                        this.palette[i + 1] = Color.getHSBColor(0.0f, 0.0f, 0.0f);
                        break;
                    }
                    }
                } else {
                    this.palette[i + 1] = Color.getHSBColor(gdata[i] / 200.0f, 1.0f, 1.0f);
                }
            }
            this.brushButton1.setBackground(this.palette[1]);
            this.brushButton2.setBackground(this.palette[2]);
            this.brushButton3.setBackground(this.palette[3]);
            this.panelColor1.setBackground(this.palette[1]);
            this.panelColor2.setBackground(this.palette[2]);
            this.panelColor3.setBackground(this.palette[3]);
            this.btnColor1.setBackground(this.palette[1]);
            this.btnColor2.setBackground(this.palette[2]);
            this.btnColor3.setBackground(this.palette[3]);
            this.sliderSpacing.setValue(gdata[4]);
            this.sliderSpeed.setValue(100 - gdata[5]);
            Designer.height = gdata[8];
            Designer.width = gdata[9];
            final int colors = gdata[7];
            int compression = 0;
            if (colors == 4) {
                compression = 4;
                this.rdbtnPixels1.setSelected(true);
                this.rdbtnPixels3.doClick();
            }
            if (colors == 3) {
                compression = 5;
                this.rdbtnPixels2.setSelected(true);
                this.rdbtnPixels2.doClick();
            }
            if (colors == 2) {
                compression = 8;
                this.rdbtnPixels3.setSelected(true);
                this.rdbtnPixels1.doClick();
            }
            this.cbHeight.setSelectedIndex(Designer.height - 1);
            this.cbWidth.setSelectedIndex(Designer.width - 1);
            final int[] p1 = new int[8];
            final int[] p2 = new int[8];
            for (int j = 0; j < compression; ++j) {
                p1[j] = (int) Math.round(Math.pow(colors, j + 1));
                p2[j] = (int) Math.round(Math.pow(colors, j));
            }
            int h = 0;
            int w = 0;
            System.out.println(" ");
            for (int k = 10; k < gdata.length; ++k) {
                Designer.picture[w][h] = gdata[k];
                System.out.print(gdata[k]);
                System.out.print(" ");
                if (++h >= Designer.height) {
                    h = 0;
                    ++w;
                    System.out.println(" ");
                }
            }
            this.setColorSelectEnable();
        }
    }

    public void setColorSelectEnable() {
        boolean gotData = false;
        for (int x = 0; x < Designer.width; ++x) {
            for (int y = 0; y < Designer.height; ++y) {
                if (Designer.picture[x][y] != 0) {
                    gotData = true;
                }
            }
        }
        this.rdbtnPixels1.setEnabled(!gotData);
        this.rdbtnPixels2.setEnabled(!gotData);
        this.rdbtnPixels3.setEnabled(!gotData);
    }

    private void showPaletteChooser() {
        (this.panel = new JPanel()).setLayout(new BorderLayout());
        (this.cc = new JColorChooser(this.btnColor1.getBackground())).setPreviewPanel(new JPanel());
        final Color color = JColorChooser.showDialog(this.panel, "Choose Color", Color.white);
        switch (this.curPalette) {
        case 1: {
            this.btnColor1.setBackground(color);
            this.brushButton1.setBackground(color);
            this.panelColor1.setBackground(color);
            break;
        }
        case 2: {
            this.btnColor2.setBackground(color);
            this.brushButton2.setBackground(color);
            this.panelColor2.setBackground(color);
            break;
        }
        case 3: {
            this.btnColor3.setBackground(color);
            this.brushButton3.setBackground(color);
            this.panelColor3.setBackground(color);
            break;
        }
        }
        this.palette[this.curPalette] = color;
        this.repaint();
    }

    private void connectHoop() {
        final SimpleSerial usber = new SimpleSerial();
        usber.getPorts();
        this.panelMain.setVisible(false);
        this.connected = false;
        (this.getConnected = new Thread(new connect())).start();
    }

    private void doDrawing(final Graphics g) {
        this.g2d = (Graphics2D) g;
        if (Designer.width > Designer.height) {
            this.blocksize = Math.floor(1000.0 / Designer.width);
        } else {
            this.blocksize = Math.floor(1000.0 / Designer.height);
        }
        this.g2d.setColor(Color.black);
        this.g2d.fillRect(300, 100, (int) (Designer.width * this.blocksize), (int) (Designer.height * this.blocksize));
        this.g2d.setColor(Color.white);
        for (int x = 0; x <= Designer.width; ++x) {
            final int xadjust = x * (int) this.blocksize + 300;
            this.g2d.drawLine(xadjust, 100, xadjust, 100 + (int) this.blocksize * Designer.height);
        }
        for (int y = 0; y <= Designer.height; ++y) {
            final int yadjust = y * (int) this.blocksize + 100;
            this.g2d.drawLine(300, yadjust, 300 + (int) this.blocksize * Designer.width, yadjust);
        }
        for (int x = 0; x < Designer.width; ++x) {
            for (int y2 = 0; y2 < Designer.height; ++y2) {
                if (Designer.picture[x][y2] != 0) {
                    this.paintBlock(x, y2, this.palette[Designer.picture[x][y2]]);
                }
            }
        }
    }

    public void paintBlock(final int x, final int y, final Color c) {
        this.g2d.setColor(c);
        this.g2d.fill(new Rectangle(x * (int) this.blocksize + 301, y * (int) this.blocksize + 101,
                (int) this.blocksize - 1, (int) this.blocksize - 1));
    }

    class JPanel2 extends JPanel {
        public void paintComponent(final Graphics g) {
            super.paintComponent(g);
            Designer.this.doDrawing(g);
        }
    }

    class RadioListener implements ActionListener {
        @Override
        public void actionPerformed(final ActionEvent e) {
            boolean gotData = false;
            for (int x = 0; x < Designer.width; ++x) {
                for (int y = 0; y < Designer.height; ++y) {
                    if (Designer.picture[x][y] != 0) {
                        gotData = true;
                    }
                }
            }
            if (gotData) {
                JOptionPane.showMessageDialog(null,
                        "Sorry, the number of colors can't be changed after you've started drawling.  Erase the drawling area and try again.");
            } else {
                Designer.this.cbWidth.setEnabled(true);
                Designer.this.cbHeight.setEnabled(true);
                if (e.getActionCommand() == "1") {
                    Designer.this.maxPixels = 320;
                    Designer.this.brushButton2.setVisible(false);
                    Designer.this.btnColor2.setVisible(false);
                    Designer.this.panelColor2.setVisible(false);
                    Designer.this.brushButton3.setVisible(false);
                    Designer.this.btnColor3.setVisible(false);
                    Designer.this.panelColor3.setVisible(false);
                }
                if (e.getActionCommand() == "2") {
                    Designer.this.maxPixels = 200;
                    Designer.this.brushButton2.setVisible(true);
                    Designer.this.btnColor2.setVisible(true);
                    Designer.this.panelColor2.setVisible(true);
                    Designer.this.brushButton3.setVisible(false);
                    Designer.this.btnColor3.setVisible(false);
                    Designer.this.panelColor3.setVisible(false);
                }
                if (e.getActionCommand() == "3") {
                    Designer.this.maxPixels = 160;
                    Designer.this.brushButton2.setVisible(true);
                    Designer.this.btnColor2.setVisible(true);
                    Designer.this.panelColor2.setVisible(true);
                    Designer.this.brushButton3.setVisible(true);
                    Designer.this.btnColor3.setVisible(true);
                    Designer.this.panelColor3.setVisible(true);
                }
                Designer.this.pnlStep1.setVisible(false);
                Designer.this.pnlStep2.setVisible(true);
            }
        }
    }

    public class connect implements Runnable {
        @Override
        public void run() {
            Designer.this.loadGallery();
            Designer.this.panelMain.setVisible(true);
            Designer.this.panelSearching.setVisible(false);

            SimpleSerial usber = new SimpleSerial();
            while (usber.hoopPort == "HOOP NOT FOUND") {
                usber.getPorts();
                Designer.this.connected = false;
                try {
                    Thread.sleep(250L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Designer.this.connected = true;
            Designer.this.hoopPort = usber.hoopPort;
            Designer.this.panelMain.setVisible(true);
            usber = null;
        }
    }
}
