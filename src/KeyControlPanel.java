import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class KeyControlPanel extends JPanel {
	private Movable toMove;

	public KeyControlPanel(Movable toMove) {
		this.toMove = toMove;
		this.setControls(toMove);
	}

	private void setControls(final Movable toMove) {
		this.setFocusable(true);
		this.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if(keyCode==KeyEvent.VK_RIGHT){
					toMove.moveRight();
				}else if(keyCode==KeyEvent.VK_LEFT){
					toMove.moveLeft();
				}else if(keyCode==KeyEvent.VK_UP){
					toMove.moveUp();
				}else if(keyCode==KeyEvent.VK_DOWN){
					toMove.moveDown();
				}/*else if(keyCode==KeyEvent.VK_SPACE){
					Tank tank = (Tank)toMove;
					tank.fire();
				}else if (keyCode==KeyEvent.VK_W){*/
//				toMove.oneKeyInvincible();
//				}else{
//			}
				MyFrame drawingFrame = (MyFrame)(KeyControlPanel.this.getRootPane().getParent());
				Commons.drawingPanel.repaint();
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}			
		});		
	}
}
