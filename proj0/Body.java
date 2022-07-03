public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV,
                double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
    public double calcDistance(Body b){
        /*return the distance between two Bodys*/
        double xxDis = b.xxPos - xxPos;
        double yyDis = b.yyPos - yyPos;
        double r = Math.sqrt(Math.pow(xxDis,2) + Math.pow(yyDis,2));
        return r;
    }
    public double calcForceExertedBy(Body b){
        /* return the force exerted on this body by the given body */
        double force = 6.67e-11 * b.mass * mass / Math.pow(calcDistance(b),2);
        return force;
    }
    public double calcForceExertedByX(Body b){
        /* return the force exerted in the X directions*/
        double forcex = calcForceExertedBy(b) * (b.xxPos - xxPos) / calcDistance(b);
        return forcex;
    }
    public double calcForceExertedByY(Body b){
        /* return the force exerted in the Y directions*/
        double forcey = calcForceExertedBy(b) * (b.yyPos - yyPos) / calcDistance(b);
        return forcey;
    }
    public double calcNetForceExertedByX(Body[] blist){
        /* return the net X force exerted by all bodies in that array upon the current Body*/
        double netforceX = 0;
        for (int i = 0; i < blist.length; i = i + 1){
            if (!(this.equals(blist[i]))){
                netforceX = netforceX + calcForceExertedByX(blist[i]);
            }
        }
        return netforceX;
    }

    public double calcNetForceExertedByY(Body[] blist){
        /* return the net Y force exerted by all bodies in that array upon the current Body*/
        double netforceY = 0;
        for (int i = 0;i < blist.length; i = i + 1){
            if (!(this.equals(blist[i]))){
                netforceY = netforceY + calcForceExertedByY(blist[i]);
            }
        }
        return netforceY;
    }
    public void update(double dt, double fX, double fY){
       double accx = fX / mass;
       double accy = fY / mass;
       xxVel = xxVel + dt * accx;
       yyVel = yyVel + dt * accy;
       xxPos = xxPos + dt * xxVel;
       yyPos = yyPos + dt * yyVel;
    }
    public void draw(){
        StdDraw.picture(xxPos, yyPos,  "./images/" + imgFileName);
    }

}
