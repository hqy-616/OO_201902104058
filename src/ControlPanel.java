import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public  class ControlPanel extends JPanel {
	//关联CanMove类型的对象
	private Movable movable;

	public ControlPanel(Movable toMove) {
		this.movable = toMove;
		addButtons(toMove);
	}
	//用按钮控制CanMoveShape类型的对象移动
	private void addButtons(Movable toMove) {

		JButton moveRightBtn = new JButton("右移");
		this.add(moveRightBtn);
		MoveRightHandler moveRightHandler = new MoveRightHandler();
		moveRightBtn.addActionListener(moveRightHandler);

		JButton moveLeftBtn = new JButton("左移");
		this.add(moveLeftBtn);
		MoveLeftHandler moveLeftHandler = new MoveLeftHandler();
		moveLeftBtn.addActionListener(moveLeftHandler);

		JButton moveDownBtn = new JButton("下移");
		this.add(moveDownBtn);
		MoveDownHandler moveDownHandler = new MoveDownHandler();
		moveDownBtn.addActionListener(moveDownHandler);

		JButton moveUpBtn = new JButton("上移");
		this.add(moveUpBtn);
		MoveUpHandler moveUpHandler = new MoveUpHandler();
		moveUpBtn.addActionListener(moveUpHandler);



		JButton closeBtn = new JButton("退出");
		this.add(closeBtn);
		CloseFrameHandler closeFrameHandler = new CloseFrameHandler();
		closeBtn.addActionListener(closeFrameHandler);
	}



    private class MoveRightHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			movable.moveRight();
			MyFrame myFrame = (MyFrame)(ControlPanel.this.getRootPane().getParent());
			myFrame.getDrawingPanel().repaint();
		}
	}

	private class MoveLeftHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			movable.moveLeft();
			((MyFrame)(ControlPanel.this.getRootPane().getParent())).getDrawingPanel().repaint();
		}
	}
	private class MoveDownHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			movable.moveDown();
			((MyFrame)(ControlPanel.this.getRootPane().getParent())).getDrawingPanel().repaint();
		}
	}
	private class MoveUpHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			movable.moveUp();
			((MyFrame)(ControlPanel.this.getRootPane().getParent())).getDrawingPanel().repaint();
		}
	}


	private class CloseFrameHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}		
	}		
}
