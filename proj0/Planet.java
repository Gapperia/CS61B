public class Planet {
	
	public static final double GRAVITATION = 6.67e-11;
	
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	
	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
				  
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
		
	}
	
	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}
	
	public double calcDistance(Planet p) {
		double dx = p.xxPos - this.xxPos;
		double dy = p.yyPos - this.yyPos;
		double r = Math.pow((Math.pow(dx, 2) + Math.pow(dy, 2)), 0.5);
		return r;
	}
	
	public double calcForceExertedBy(Planet p) {
		double forceExerted = GRAVITATION * p.mass * this.mass / Math.pow(calcDistance(p), 2);
		return forceExerted;
	}
	
	public double calcNetForceExertedByX(Planet[] ps) {
		double totalForceX = 0;
		
		for (Planet p:ps) {
			if(p.equals(this)) {
				continue;
			} else {
				double dx = p.xxPos - this.xxPos;
				totalForceX += calcForceExertedBy(p) * dx / Math.pow(calcDistance(p), 2);
			}
		}
		
		return totalForceX;
	}
	
	public double calcNetForceExertedByY(Planet[] ps) {
		double totalForceY = 0;
		
		for (Planet p:ps) {
			if(p.equals(this)) {
				continue;
			} else {
				double dy = p.yyPos - this.yyPos;
			totalForceY += calcForceExertedBy(p) * dy / Math.pow(calcDistance(p), 2);
			}
		}
		
		return totalForceY;
	}
	
	public void update(double dt, double fX, double fY) {
		double ax = fX / this.mass;
		double ay = fY / this.mass;
		
		this.xxVel += (ax * dt);
		this.yyVel += (ay * dt);
		
		this.xxPos += (this.xxVel * dt);
		this.yyPos += (this.yyVel * dt);
	
	}
	
	public void draw() {
		String imgPath = "images/" + this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, imgPath);
	}

}