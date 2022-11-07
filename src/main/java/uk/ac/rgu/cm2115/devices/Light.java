package uk.ac.rgu.cm2115.devices;

public class Light extends Device<LightStatus> implements Switchable, Dimmable{

    private Color color;

    public Light(String name) {
        super(name);
        this.color = new Color(255, 255, 255);
        this.status = LightStatus.OFF;
    }

    public void switchOn(){
        System.out.println(this.name + " is switched on");
        this.status = LightStatus.OFF;
    }

    public void switchOff(){
        System.out.println(this.name + " is switched off");
        this.status = LightStatus.OFF;
    }

    public void dimUp(){
        System.out.println(this.name + " is dimmed up");
        this.status = LightStatus.DIMMED;
    }

    public void dimDown(){
        System.out.println(this.name + " is dimmed down");
        this.status = LightStatus.DIMMED;
    }

    public void setColor(int r, int g, int b){
        this.color.setR(r);
        this.color.setG(g);
        this.color.setB(b);
    }

    public class Color{
        private int r, g, b;

        public void setB(int b){
            this.b = b;
        }

        public int getB() {
            return b;
        }

        public void setG(int g){
            this.g = g;
        }

        public int getG() {
            return g;
        }

        public void setR(int r){
            this.r = r;
        }

        public int getR() {
            return r;
        }

        public Color(int r, int g, int b){
            this.r = r;
            this.g = g;
            this.b = b;
        }

        @Override
        public String toString(){
            return r + "," + g + "," + b;
        }

        
    }
    
}
