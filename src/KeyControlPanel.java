import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class KeyControlPanel extends JPanel {
	private Controllable toControl;

	public KeyControlPanel(Controllable toControl) {
		this.toControl = toControl;
		this.setControls(toControl);
	}

	private void setControls(final Controllable toControl) {
		this.setFocusable(true);
		this.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if(keyCode==KeyEvent.VK_RIGHT){
					toControl.moveRight();
				}else if(keyCode==KeyEvent.VK_LEFT){
					toControl.moveLeft();
				}else if(keyCode==KeyEvent.VK_UP){
					toControl.moveUp();
				}else if(keyCode==KeyEvent.VK_DOWN){
					toControl.moveDown();
				}else if(keyCode==KeyEvent.VK_SPACE){
					toControl.fire();
				}
				MyFrame drawingFrame = (MyFrame)(KeyControlPanel.this.getRootPane().getParent());
				Commons.drawingPanel.repaint();
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}			
		});
	}
}
