package gui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;

public class MainUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JButton btngo;
	static String path = "";/// Users/heshuhao/Downloads/Album
	static String targetPath = "";/// Users/heshuhao/Downloads/Album2
	static String csvPath = "";/// Users/heshuhao/Downloads/id_name_map.csv
	static Set hs = new HashSet();
	static Map<String, String> hm = new HashMap<String, String>();
	static Map<String, List<String>> id_name_map = new HashMap<String, List<String>>();
	String[] columnName = { "ID", "标准名称", "自定名称" };
	Object[][] data = { { "请", "检", "查" } };
	private JButton button_3;
	private JButton button_4;
	private JButton btnNewButton;
	private JTextPane txtpnnssd;
	private JButton button_5;
	private JButton button_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI frame = new MainUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainUI() {
		Font font = new Font("黑体",Font.PLAIN,18);
		
        UIManager.put("Button.font", font);
        UIManager.put("TextField.font", font);
//        UIManager.put("OptionPane.font", font);//无效？
		setTitle("NS Album Helper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton button = new JButton("选择相册目录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//		        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY)

				jfc.showDialog(new JLabel(), "选择相册目录");
				File file = jfc.getSelectedFile();
				textField.setText(file.getAbsolutePath());
				path = file.getAbsolutePath();
				System.out.println(path);
			}
		});
		button.setBounds(33, 36, 155, 50);
		contentPane.add(button);

		JButton button_1 = new JButton("选择配置文件");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

				jfc.showDialog(new JLabel(), "选择配置文件");
				File file = jfc.getSelectedFile();
				textField_1.setText(file.getAbsolutePath());
				csvPath = file.getAbsolutePath();
				System.out.println(csvPath);

			}
		});
		button_1.setBounds(33, 100, 155, 50);
		contentPane.add(button_1);

		JButton button_1_1 = new JButton("选择导出目录");
		button_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				jfc.showDialog(new JLabel(), "选择导出目录");
				File file = jfc.getSelectedFile();
				textField_2.setText(file.getAbsolutePath());
				targetPath = file.getAbsolutePath();
				System.out.println(targetPath);

			}
		});
		button_1_1.setBounds(33, 166, 155, 50);
		contentPane.add(button_1_1);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(200, 36, 471, 50);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(200, 100, 471, 50);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(200, 166, 471, 50);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 296, 796, 420);
		contentPane.add(scrollPane);

		table = new JTable(data, columnName);
		
		table.setRowHeight(35);
		
		scrollPane.setColumnHeaderView(table);

		scrollPane.setViewportView(table);

		btngo = new JButton("开始检查，GO！");
		btngo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (path.equals("") || targetPath.equals("") || csvPath.equals("")) {
					// dialog
					JOptionPane.showMessageDialog(null, "请正确选择相册目录、导出目录和配置文件！", "提示", JOptionPane.INFORMATION_MESSAGE);
//					JOptionPane.showMessageDialog(null, "文字", "标题", JOptionPane.ERROR_MESSAGE);

				} else {
					readFileGameID(path);
					System.out.println(hs);

					id_name_map = readCSV(csvPath);
					System.out.println(id_name_map.size());

//					Map<String, List<String>> my_id_name_map = new HashMap<String, List<String>>();
					List<String> names_list_blank = new ArrayList<>();
					names_list_blank.add("null");
					names_list_blank.add("null");
					String gameName = "null";
					String gameNameDIY = "null";
					Iterator it = hs.iterator();
					Object[][] data2 = new Object[hs.size()][3];
					int index = 0;
					while (it.hasNext()) {
						String gameID = it.next().toString();
						System.out.println(gameID);
						if (id_name_map.containsKey(gameID)) {

							gameName = id_name_map.get(gameID).get(0);
							gameNameDIY = id_name_map.get(gameID).get(1);
							System.out.println("配置文件含有该游戏信息！");
//							contentPane.add(new MyJPanel(null, gameID, gameName, gameNameDIY));
//							myUpdateUI();
							data2[index][0] = gameID;
							data2[index][1] = gameName;
							data2[index][2] = gameNameDIY;

						} else {
							id_name_map.put(gameID, names_list_blank);
							System.out.println("配置文件空缺该游戏信息？");
//							contentPane.add(new MyJPanel(null, gameID, gameName, gameNameDIY));
//							myUpdateUI();
							data2[index][0] = gameID;
							data2[index][1] = "null";
							data2[index][2] = "null";
						}
						index++;

					}
					table = new JTable(data2, columnName);
					table.setRowHeight(35);
					
					scrollPane.setColumnHeaderView(table);

					scrollPane.setViewportView(table);
					
					System.out.println(id_name_map.size());
					btnNewButton.setEnabled(true);
					button_3.setEnabled(true);
					button_4.setEnabled(true);


				}
			}
		});
		btngo.setBounds(33, 234, 638, 50);
		contentPane.add(btngo);

		button_3 = new JButton("查看选中示例");
		button_3.setEnabled(false);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableModel tableModel = (TableModel) table.getModel();
				int rowCount = tableModel.getRowCount();

				System.out.println(rowCount);
//				System.out.println((String) tableModel.getValueAt(rowCount - 1, 2));

				int selectRows = table.getSelectedRows().length;
				int selectRow = table.getSelectedRow();
				System.out.println("!!!" + table.getSelectedRow());

				if (selectRows == 1) {
					String imageORvideoPath = hm.get((String) tableModel.getValueAt(selectRow, 0));
					openFile(imageORvideoPath);
				} else if (selectRows < 1) {
					JOptionPane.showMessageDialog(null, "请选择你想查看的一行后重试", "提示", JOptionPane.INFORMATION_MESSAGE);

				} else if (selectRows > 1) {
					JOptionPane.showMessageDialog(null, "您选择了多行，请选择一行重试", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		button_3.setBounds(846, 296, 134, 50);
		contentPane.add(button_3);

		button_4 = new JButton("保存配置");
		button_4.setEnabled(false);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					TableModel tableModel = (TableModel) table.getModel();
					int rowCount = tableModel.getRowCount();
					for (int i = 0; i < rowCount; i++) {
						String gameID = (String) tableModel.getValueAt(i, 0);
						String gameName = (String) tableModel.getValueAt(i, 1);
						String gameNameDIY = (String) tableModel.getValueAt(i, 2);
						gameID=gameID.replace(":", "");//去除所有的英语冒号，防止文件创建失败
						gameName=gameName.replace(":", "");
						gameNameDIY=gameNameDIY.replace(":", "");
						List<String> names_list_blank = new ArrayList<>();
						names_list_blank.add(gameName);
						names_list_blank.add(gameNameDIY);
						id_name_map.put(gameID, names_list_blank);
					}
//					File csv = new File(csvPath); // CSV数据文件
//					BufferedWriter bw = new BufferedWriter(new FileWriter(csv, false)); // true附加,false重写.乱码了，所以换了另一种
					
					FileOutputStream fos = new FileOutputStream(csvPath, false);
		            fos.write(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF});//利用文件头加入MS Excel需要的BOM信息以防止乱码。
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
					
					
					for (Entry<String, List<String>> entry : id_name_map.entrySet()) {
						String mapKey = entry.getKey();
						List<String> mapValue = entry.getValue();
						bw.write(mapKey + "," + mapValue.get(0) + "," + mapValue.get(1));
						System.out.println(mapKey + "," + mapValue.get(0) + "," + mapValue.get(1));
//					    System.out.println(mapKey+":"+mapValue);
						bw.newLine();
					}
//					bw.write("\"水电sd费四大\"" + "," + "\"1988\"" + "," + "\"1992\"");
					
					bw.close();

				} catch (FileNotFoundException e1) {
					// File对象的创建过程中的异常捕获
					e1.printStackTrace();
				} catch (IOException e1) {
					// BufferedWriter在关闭对象捕捉异常
					e1.printStackTrace();
				}
			}

		});
		button_4.setBounds(846, 358, 134, 50);
		contentPane.add(button_4);

		btnNewButton = new JButton("开始导出");
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readFile(path,id_name_map);
				JOptionPane.showMessageDialog(null, "导出完毕！" ,"操作说明",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton.setBounds(846, 424, 134, 50);
		contentPane.add(btnNewButton);
		
//		txtpnnssd = new JTextPane();
//		txtpnnssd.setEditable(false);
//		txtpnnssd.setText("操作说明：\n1、本程序帮助您将NS外置SD卡中的截图和视频按”游戏“分类导出。\n2、软件的使用顺序是：\n①设置好相册目录、配置文件、导出目录。\n相册目录的名称通常是Nintendo文件夹中的“Album”文件夹。\n配置文件是一个游戏ID和名称对应的CVS格式文件（见附件）。\n导出目录可以多次使用。多次导入时，为增量导入，即仅导入新增的截图和视频。\n②点击“开始检查”按钮，检查到的游戏将会显示在下方列表。\n③单击选中一行，点击“查看选中行示例”，可用系统软件打开最后一个媒体文件，方便分辨游戏以自定名称。\n④双击某个单元格，修改游戏列表中的名称，未知的游戏默认名称为“null”。回车键或单击表中其他行或空白处临时确认修改。\n⑤确认游戏名称无误后，点击“保存配置”按钮来二次确认，新的配置将会覆盖保存在原配置文件中，方便下次使用。\n⑥点击“开始导出”按钮，软件将会依照当前配置文件按游戏导出截图和视频。\nVersion 1.1更新内容：\n - CSV文件写入时加入BOM文件头，防止Excel读取乱码。");
//		txtpnnssd.setBounds(33, 6, 752, 274);
//		contentPane.add(txtpnnssd);
		
		button_5 = new JButton("说明");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String abc="操作说明：\n1、本程序帮助您将NS外置SD卡中的截图和视频按”游戏“分类导出，并保留时间属性。\n2、软件的使用顺序是：\n①设置好相册目录、配置文件、导出目录。\n相册目录的名称通常是Nintendo文件夹中的“Album”文件夹。\n配置文件是一个游戏ID和名称对应的CVS格式文件（见附件）。\n导出目录可以多次使用。多次导入时，为增量导入，即仅导入新增的截图和视频。\n②点击“开始检查”按钮，检查到的游戏将会显示在下方列表。\n③单击选中一行，点击“查看选中行示例”，可用系统软件打开最后一个媒体文件，方便分辨游戏以自定名称。\n④双击某个单元格，修改游戏列表中的名称，未知的游戏默认名称为“null”。回车键或单击表中其他行或空白处临时确认修改。\n⑤确认游戏名称无误后，点击“保存配置”按钮来二次确认，新的配置将会覆盖保存在原配置文件中，方便下次使用。\n⑥点击“开始导出”按钮，软件将会依照当前配置文件按游戏导出截图和视频。\n注意：如果您的内容较多（例如从来没分过类），可能需要花费数十分钟时间，请耐心等待至出现“导出完毕”对话框出现为止。";
				JOptionPane.showMessageDialog(null, abc ,"操作说明",JOptionPane.INFORMATION_MESSAGE);

			}
		});
		button_5.setBounds(729, 36, 251, 96);
		contentPane.add(button_5);
		
		button_6 = new JButton("关于");
		button_6.setBounds(729, 168, 251, 96);
		contentPane.add(button_6);
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String about="软件作者：@hshsilver\n"
						+ "数据综合自GitHub\n"
						+ "本程序免费开源\n\n"
						+ "——更新记录——\n"
						+ "Version 1.0 2020.04.08 软件基本功能完成。\n"
						+ "Version 1.1 2020.04.09 小修小补，更新内容：\n - CSV文件写入时加入BOM文件头，防止Excel读取CSV时乱码。\n - 拷贝时保留截图和视频本身的时间属性。\n"
						+ "Version 1.2 2020.04.10 修正Bug：\n - 如果游戏名称中有英文冒号，则去掉，防止文件夹创建时出错或冒号变为其他符号。";
				JOptionPane.showMessageDialog(null, about ,"关于软件",JOptionPane.INFORMATION_MESSAGE);

			}
		});

	}

	public static Map<String, List<String>> readCSV(String readCSVpath) {
		Map<String, List<String>> id_name_map = new HashMap<String, List<String>>();
		try {

			BufferedReader reader = new BufferedReader(new FileReader(readCSVpath));// 换成你的文件名
			reader.readLine();// 第一行信息，为标题信息，不用,如果需要，注释掉
			String line = null;
			System.out.println("自定义名字的游戏有：");
			while ((line = reader.readLine()) != null) {
				String item[] = line.split(",");// CSV格式文件为逗号分隔符文件，这里根据逗号切分

				String id = "";
				List<String> names_list = new ArrayList<>();
				id = item[0];

				names_list.add(item[1]);
				names_list.add(item[2]);

				id_name_map.put(id, names_list);

				if (id_name_map.get(id).get(1).equals("null")) {
				} else {
					System.out.println(id_name_map.get(id).get(1));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(id_name_map);
		return id_name_map;
	}

	public static void copyFile(File source, Map<String, List<String>> map) {

		String fileName = "";
		String fileGameID = "";
		String fileFormat = "";
		String fileTime = "";

		fileName = source.getName();
		fileGameID = source.getName().substring(17, 49);
		fileFormat = source.getName().substring(50);
		fileTime = source.getName().substring(0, 15);
		System.out.println("文件" + fileName);
		System.out.println("游戏——" + fileGameID);
		System.out.println("格式——" + fileFormat);
		System.out.println("时间——" + fileTime);

		String fileGameName = "null";
		String fileGameNameDIY = "null";
		String gameName = "";
//		System.out.println(map.containsKey(fileGameID));
		if (map.containsKey(fileGameID)) {
			fileGameName = map.get(fileGameID).get(0);
			fileGameNameDIY = map.get(fileGameID).get(1);
			if(fileGameNameDIY.equals("null")) {
				if(fileGameName.equals("null")){
					gameName = fileGameID;
				}else {
					gameName = fileGameName;
				}
			}else {
				gameName= fileGameNameDIY;
			}
		} else {
			gameName = fileGameID;//当前版本下，在检查阶段就会自动加入没有的ID，所以这里其实已经没有太大用处了
		}

		File destDir = new File(targetPath + "/" + gameName);
		if (!(destDir.exists() && destDir.isDirectory())) {
			destDir.mkdirs();
			System.out.println("创建文件夹" + targetPath + "/" + gameName);

		}
		File dest = new File(destDir + "/" + source.getName());
		if (!dest.exists()) {
			try {

//              Files.copy(source.toPath(), dest.toPath(),StandardCopyOption.ATOMIC_MOVE);
//              Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
				Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.COPY_ATTRIBUTES);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void readFile(String path, Map<String, List<String>> map) {
		File file = new File(path);
		File[] tempList = file.listFiles();
		// tempList.length获取文件夹下所包含的文件或者文件夹的个数

		if (tempList.length > 0) {
			for (File f : tempList) {
				// 如果是文件，直接打印文件名称
				if (f.isFile() && f.getName().length() == 53) {

					copyFile(f, map);
				}
				if (f.isDirectory()) {
					System.out.println("文件夹" + f.getName());
					readFile(path + "/" + f.getName(), map);
				}
			}
		}
	}

	public static Set readFileGameID(String path) {
		File file = new File(path);
		File[] tempList = file.listFiles();

		if (tempList.length > 0) {
			for (File f : tempList) {
				// 如果是文件，直接打印文件名称
				if (f.isFile() && f.getName().length() == 53) {
					hs.add(f.getName().substring(17, 49));
					hm.put(f.getName().substring(17, 49), f.getAbsolutePath());
				}
				if (f.isDirectory()) {
					System.out.println("文件夹" + f.getName());
					readFileGameID(path + "/" + f.getName());
				}
			}
		}

		return hs;
	}

	public void openFile(String filePath) {
		try {
			File file = new File(filePath); // 创建文件对象
			Desktop.getDesktop().open(file); // 启动已在本机桌面上注册的关联应用程序，打开文件文件file。
		} catch (IOException | NullPointerException e) { // 异常处理
			System.err.println(e);
		}
	}
}
