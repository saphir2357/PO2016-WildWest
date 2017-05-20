package dzikizachod;


/**
 * Domyślna strategia bandyty.
 * Dąży do ubicia szeryfa.
 */
public class StrategiaBandytyDomyslna extends StrategiaBandyty {
    protected class NieDaSieNicZrobicWyjatek extends BladKonrtoleraWyjatek {
        NieDaSieNicZrobicWyjatek() {
            super("Nie da się nic zrobić");
        }
    }

    @Override
    public void patrzKolejnaTura(int numerTury) {}

    @Override
    public void patrzKoniecGry(boolean czyDobroWygralo) {}

    @Override
    protected void ogarnijWykonalAkcje(StrategicznyWidokGracza ktoGra, Akcja a, StrategicznyWidokGracza naKim) {}

    @Override
    protected void ogarnijZabojstwo(StrategicznyWidokGracza ofiara, StrategicznyWidokGracza zabojca) {}

    protected void najazdNaSzeryfa() throws BladKonrtoleraWyjatek {
        int doSzeryfa = ja().odlegloscIKierunekOd(szeryf());
        if (Math.abs(doSzeryfa) <= ja().zasieg()) {
            while (ja().ileAkcji(Akcja.STRZEL) > 0)
                akcjaStrzel(szeryf());
        } else {
            StrategicznyWidokGracza cel = losowyPomocnikWZasieguNaLuku(doSzeryfa);
            if (cel == null)
                cel = losowyPomocnikWZasieguNaLuku(-doSzeryfa);
            if (cel == null)
                throw new NieDaSieNicZrobicWyjatek();
        }
    }

    @Override
    void graj() throws BladKonrtoleraWyjatek {
        super.graj();
        try {
            while (ja().ileAkcji(Akcja.STRZEL) > 0)
                najazdNaSzeryfa();
        } catch (NieDaSieNicZrobicWyjatek ignored) {
        } catch (BladKonrtoleraWyjatek e) {
            e.printStackTrace();
        }
    }
}
