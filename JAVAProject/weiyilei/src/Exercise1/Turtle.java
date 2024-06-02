package Exercise1;

public class Turtle {
	private double x, y;
	private double angle;
	
	public Turtle(double x0, double y0, double a0) {
		x = x0;
		y = y0;
		angle = a0;
	}
	
	public void turnLeft (double delta)  { angle += delta; }
	
	public void turnRight (double delta) { angle -= delta; }
   
   public double getX() { return x; }
   public double getY() { return y; }
   public double getAngle() { return angle; }
   
   public void setX (double x) { this.x = x; }
   public void setY (double y) { this.y = y; }
   public void setAngle (double a) { this.angle = a; }

	public void goForward(double d) {
		double oldx = x;
		double oldy = y;
		x += d * Math.cos( Math.toRadians(angle));
		y += d * Math.sin( Math.toRadians(angle));
		StdDraw.line( oldx, oldy, x, y);
	}
}
