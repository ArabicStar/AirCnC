package presentation.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import vo.OrderVo;

/**
 * TODO: 用JavaFx写界面
 * @author 15125_000
 *
 */
public class ProcessOrderView extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private int hotelId;
	
	private ProcessOrderViewControllerService controller;
	
	private JComboBox<String> orderTypeComboBox;
	
	private JButton delayButton;
	
	private JButton entryButton;
	
	private JTable orderTable;
	
	private DefaultTableModel orderListModel;
	
	private JPanel delayPanel;
	
	private JLabel delayLabel;
	
	private JTextField delayTextField;
	
	private JButton delayProcessButton;
	
	private JButton cancelButton;
	
	private JFrame delayFrame;
	
	public ProcessOrderView(ProcessOrderViewControllerService controller){
		this.controller = controller;
		this.hotelId = controller.getHotelId();
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		//初始化订单类型选择框
		initOrderTypeCombobox();
		
		//初始化操作按钮
		initOrderProcessButtons();
		
		//初始化订单列表
		initOrderListTable();
		
		this.validate();
	}

	private void initOrderTypeCombobox() {
		
		orderTypeComboBox = new JComboBox<String>();
		
		//订单类型列表
		List<String> list=new ArrayList<String>();
		list.add("所有类型");
		list.add("未执行订单");
		list.add("已执行订单");
		list.add("异常订单");
		
		//初始化combobox
		for (String str : list) {
			orderTypeComboBox.addItem(str);
		}
		
		//设置选择事件
		orderTypeComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
			
				if(evt.getStateChange() == ItemEvent.SELECTED){
					
					String selected=(String)orderTypeComboBox.getSelectedItem();
					
					//更换数据源
					controller.updateListModel(selected);
				}
			}

		});
		
		//添加下拉框
		JPanel orderTypeJpanel = new JPanel();
		orderTypeJpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel orderTypeJLabel=new JLabel("订单类型：");
		orderTypeJpanel.add(orderTypeJLabel);
		orderTypeJpanel.add(orderTypeComboBox);
		this.add(orderTypeJpanel);
		
	}
	
	private void initOrderProcessButtons(){
	
		delayButton = new JButton("订单延期");
		//添加按钮监听事件
		delayButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				//界面跳转
				controller.delayOrderButtonClicked();
			}
		});
		
		entryButton = new JButton("客户入住");
		//添加按钮监听事件
		entryButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				//处理未执行订单按钮点击事件
				controller.processOrderButtonClicked();
				
			}
		});
		
		//添加订单处理栏
		JPanel processPanel = new JPanel();
		processPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		processPanel.add(delayButton);
		processPanel.add(entryButton);
		
		delayButton.setEnabled(false);
		entryButton.setEnabled(false);
		
		this.add(processPanel);
	}

	private void initOrderListTable() {
		JScrollPane scrollPane = new JScrollPane();
		
		//表头
		Vector<String> vColumns = new Vector<String>();
		vColumns.add("订单编号");
		vColumns.add("订单详情");
		vColumns.add("用户信息");
		vColumns.add("入住时间");
		vColumns.add("订单最晚执行时间");
		vColumns.add("订单状态");
		vColumns.add("订单价值");
	
		//数据
		Vector<OrderVo> vData = new Vector<OrderVo>();
		vData.addAll(controller.getAllOrders(hotelId));
		//模型
		orderListModel = new DefaultTableModel(vData, vColumns);
		//表格
		orderTable = new JTable(orderListModel){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		orderTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane.getViewport().add(orderTable);
		orderTable.setFillsViewportHeight(true);
		this.add(scrollPane);
	}
	
	/**
	 * 处理订单按钮点击事件
	 */
	public void processOrderButtonClicked() {
		int index = orderTable.getSelectedRow();
		if(index == -1){
			JOptionPane.showMessageDialog(null, "请选择订单！","", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int orderNo=Integer.valueOf((String)orderTable.getValueAt(index, 0));
		if(controller.processUnfinishedOrder(orderNo)){
			orderListModel.removeRow(index);
		}else{
			JOptionPane.showMessageDialog(null, "办理用户入住失败！","", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * 在订单类型改变之后更改列表数据源
	 * @param selected
	 */
	public void updateListModel(String selected) {
		if(selected == "所有类型"){
			//更新订单列表
			orderListModel.setRowCount(0);
			for (OrderVo orderVo : controller.getAllOrders(hotelId)) {
				orderListModel.addRow(orderVo);
			}
			
			//设置控件可用类型
			delayButton.setEnabled(false);
			entryButton.setEnabled(false);
			
		}else if(selected == "未执行订单"){
			//更新订单列表
			orderListModel.setRowCount(0);
			for (OrderVo orderVo : controller.getUnfinishedOrders(hotelId)) {
				orderListModel.addRow(orderVo);
			}
			
			//设置控件可用类型
			delayButton.setEnabled(false);
			entryButton.setEnabled(true);
			
		}else if(selected == "已执行订单"){
			//更新订单列表
			orderListModel.setRowCount(0);
			for (OrderVo orderVo : controller.getFinishedOrders(hotelId)) {
				orderListModel.addRow(orderVo);
			}
			
			//设置控件可用类型
			delayButton.setEnabled(false);
			entryButton.setEnabled(false);
		}else if(selected == "异常订单"){
			//更新订单列表
			orderListModel.setRowCount(0);
			for (OrderVo orderVo : controller.getAbnormalOrders(hotelId)) {
				orderListModel.addRow(orderVo);
			}
			
			//设置控件可用类型
			delayButton.setEnabled(true);
			entryButton.setEnabled(false);
		}
	}
	
	/**
	 * 打开异常订单处理界面
	 * @param index
	 */
	public void delayOrderButtonClicked(){
		
		int index = orderTable.getSelectedRow();
		
		if(index == -1){
			JOptionPane.showMessageDialog(null, "请选择订单！","", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		final int rowIndex = index;
		final int orderNo =Integer.valueOf((String)orderTable.getValueAt(index, 0));
		
		delayFrame = new JFrame();
		delayFrame.setSize(600, 80);
		delayFrame.setLocation(400, 400);
		
		delayPanel = new JPanel();
		delayPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		delayLabel = new JLabel("订单延期至：（yyyy/MM/dd HH:mm:ss）");
		delayTextField = new JTextField(10);
		delayProcessButton = new JButton("延期");
		//处理订单延期
		delayProcessButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				if(delayOrder(orderNo)){
					orderListModel.removeRow(rowIndex);
				}
				
			}
		});
		cancelButton = new JButton("取消");
		//取消订单延期处理
		cancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//关闭窗口
				delayFrame.dispose();
				
			}
		});
		delayPanel = new JPanel();
		delayPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		delayPanel.add(delayLabel);
		delayPanel.add(delayTextField);
		delayPanel.add(delayProcessButton);
		delayPanel.add(cancelButton);
		
		delayFrame.getContentPane().add(delayPanel);
		delayFrame.setVisible(true);
		
	}
	
	//订单延期按钮点击事件
	private boolean delayOrder(int orderNo) {
	
		String delayTime = delayTextField.getText();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			Date date = sdf.parse(delayTime);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "请按格式（yyyy/MM/dd HH:mm:ss）填写时间！","", JOptionPane.ERROR_MESSAGE);
			delayTextField.setText("");
			return false;
		}
		
		
		if(controller.processAbnormalOrder(orderNo,delayTime)){	
			delayFrame.dispose();
			return true;
		}else{
			JOptionPane.showMessageDialog(null, "延期异常订单失败！","", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
	}

}
