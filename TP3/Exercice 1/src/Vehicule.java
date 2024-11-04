public class Vehicule {
    public Vehicule() {

    }

    //We made these throws to test exceptions
    public void testVitesse(int i) throws TropViteException {
        if (i > 90) {
            throw new TropViteException(i);
        }

    }

    public static void main(String[] args) {
        Vehicule v = new Vehicule();

        try {
            v.testVitesse(80);  // Should not throw an exception
            v.testVitesse(100); // This will throw TropViteException
        } catch (TropViteException e) {
            // Handle the exception
           e.printStackTrace();
        }
    }
}
