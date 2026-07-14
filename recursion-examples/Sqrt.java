Public class Sqrt{
    public static void main(String[] args) {
       sqrt();
    }

    public static void Sqrt(){
        double guess = 1;
        double quotient = 2 / guess;
        double mean = (guess + quotient) / 2;

        for (int i = 0; i < 100; i++) {
           guess = mean;
           quotient = 2 / guess;
            mean = (guess + quotient) / 2;
            System.out.println(mean);


          
        }
    }
}