public class NBody {
	
	public static double readRadius(String filePath) {
		
		In in  = new In(filePath);
		int planetNum = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
	
	public static Planet[] readPlanets(String filePath){
		
		In in  = new In(filePath);
		int planetNum = in.readInt();
		double radius = in.readDouble();
		
		Planet[] planets = new Planet[planetNum];
		
		for(int i = 0; i < planetNum; i++) {
			double xxPosition = in.readDouble();
			double yyPosition = in.readDouble();
			double xxVelocity = in.readDouble();
			double yyVelocity = in.readDouble();
			double mass = in.readDouble();
			String name = in.readString();
			
			planets[i] = new Planet(xxPosition, yyPosition, xxVelocity, yyVelocity, mass, name);
		}
		
		return planets;
		
	}
	
	
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		
		String filename = args[2];
		
		
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		
		String backgroundImg = "images/starfield.jpg";
		StdDraw.setScale(-radius, radius);
		
		StdDraw.clear();
		StdDraw.picture(0, 0, backgroundImg);
		
		for (Planet p:planets) {
			p.draw();
		}

		StdDraw.enableDoubleBuffering();
		
		for(double time = 0; time <= T; time += dt) {
			
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			
			for (int i = 0; i < planets.length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			
			for (int i = 0; i < planets.length; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			
		StdDraw.clear();
		StdDraw.picture(0, 0, backgroundImg);
		
		for (Planet p:planets) {
			p.draw();
		}
		
		StdDraw.show();
		StdDraw.pause(10);
			
		}	
		

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
		
}