import java.awt.desktop.SystemEventListener;

public class NBody {
    public static double readRadius(String file) {
        In in = new In(file);
        int numofplanet = in.readInt(); /*first item in the file: the number of planet;*/
        double radius = in.readDouble();/* second item in the file: radius*/
        return radius;
    }
    public static Body[] readBodies(String file){
        In in = new In(file);
        int numofplanet = in.readInt(); /*first item in the file: the number of planet;*/
        double r = in.readDouble(); /* second item in the file: radius*/
        Body[] moreplanet = new Body[numofplanet]; /*declear a new Body[]*/
        for (int i = 0; i < moreplanet.length; i ++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            moreplanet[i] = new Body(xP,yP, xV, yV, m, img);/*store all the planet info in this new Body[]*/
        }
        return moreplanet;
    }

    public static void main(String[] args) {
        /*Collecting All Needed Input*/
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double universeradius = readRadius(filename);
        Body[] bodyinfo = readBodies(filename);


        StdDraw.enableDoubleBuffering();/*enableDoubleBuffering(): This is a graphics technique to prevent flickering in the animation*/
        int time = 0;
        while (time < T){
            double[] xForces = new double[bodyinfo.length];
            double[] yForces = new double[bodyinfo.length];
            for (int i = 0; i < bodyinfo.length; i ++){
                xForces[i] = bodyinfo[i].calcNetForceExertedByX(bodyinfo);
                yForces[i] = bodyinfo[i].calcNetForceExertedByY(bodyinfo);
            }
            for (int i = 0; i < bodyinfo.length; i ++){
                bodyinfo[i].update(dt, xForces[i], yForces[i]);
            }
            /*Drawing the Background*/
            String background = "images/starfield.jpg";
            StdDraw.setScale(-universeradius, universeradius);
            StdDraw.clear();
            StdDraw.picture(0, 0, background);

            /*Draw all of the Bodys.*/
            for (int i = 0; i < bodyinfo.length; i ++){
                bodyinfo[i].draw();
                // System.out.println(i);
            }
            StdDraw.show();
            StdDraw.pause(10);
            dt += 1;

        }
        StdOut.printf("%d\n", bodyinfo.length);
        StdOut.printf("%.2e\n", universeradius);
        for (int i = 0; i < bodyinfo.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodyinfo[i].xxPos, bodyinfo[i].yyPos, bodyinfo[i].xxVel,
                    bodyinfo[i].yyVel, bodyinfo[i].mass, bodyinfo[i].imgFileName);
        }

    }
}
