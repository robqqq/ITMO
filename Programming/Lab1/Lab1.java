public class Lab1 {

    public static void main(String[] args) {
        short[] g = new short[8];
        for(short i = 0; i < 8; i += 1){
            g[i] = (short) (20 - 2 * i);
        }
        float[] x = new float[10];
        for(int i = 0; i < 10; i++){
            x[i] = (float) Math.random() * 10 - 6;
        }
        double[][] d = new double[8][10];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 10; j++){
                switch(g[i]){
                    case(8):
                        d[i][j] = Math.tan(Math.atan((x[j] - 1) * Math.E + 1)) / 2;
                        break;
                    case(12): case(14): case(16): case(18):
                        d[i][j] = Math.cos(Math.pow((Math.pow((x[j] + 1) / 2 / 3, x[j])),(Math.PI * (4 - 2 - x[j]))));
                        break;
                    default:
                        d[i][j] = Math.cbrt(Math.sin(Math.pow((x[j] * (4 - x[j]) - 4),(x[j] - 1) / 0.25)));
                }
            }
        }
        for(double i[]: d){
            for(double j: i){
                System.out.printf("%.2f", j);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}