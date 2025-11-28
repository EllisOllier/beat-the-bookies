public class Bookie {
    private String name;
    private float win;
    private float draw;
    private float loss;

    public Bookie(String name, float value1, float value2, float value3){
        this.name = name;
        this.win = value1;
        this.draw = value2;
        this.loss = value3;
    }

    public String getName(){
        return this.name;
    }

    public float getWinOdd(){
        return this.win;
    }

    public float getDrawOdd(){
        return this.draw;
    }

    public float getLossOdd(){
        return this.loss;
    }

    public void displayBookie(){
        System.out.println("Name: " + name);
        System.out.println("Win odd: " + win);
        System.out.println("Draw odd: " + draw);
        System.out.println("Loss odd: " + loss);
    }
}
