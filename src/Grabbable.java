import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Grabbable implements MouseListener {
	
	private int x=0,y=0,width,height;
	private boolean grabbed;
	
	public Grabbable(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		 int mouseX = e.getX();
	     int mouseY = e.getY();
	     if(mouseX > x && mouseY > y && mouseX < x+width && mouseY < y+height){
	    	 grabbed = true;
	     }
	     
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		grabbed = true;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
