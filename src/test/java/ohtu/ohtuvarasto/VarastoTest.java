package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto, varastoSaldolla;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varastoSaldolla = new Varasto(12, 11);

    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
   

    @Test
    public void konstruktoriLuoOlemattomanVarastonNegatiivisellaTilavuudella() {
        Varasto virheellinenVarasto = new Varasto(-123);
        assertEquals(0, virheellinenVarasto.getTilavuus(), vertailuTarkkuus );

        Varasto virheellinenVarastoSaldolla = new Varasto(-123, 123);
        assertEquals(0, virheellinenVarastoSaldolla.getTilavuus(), vertailuTarkkuus );
    }

    @Test
    public void konstruktoriLuoTyhjanVarastonNegatiivisellaSaldolla() {
        Varasto virheellinenVarasto = new Varasto(12,-23);
        assertEquals(0, virheellinenVarasto.getSaldo(), vertailuTarkkuus );
    }
    @Test
    public void konstruktoriLuoTaydenVarastonLiianSuurellaSaldolla() {
        Varasto virheellinenVarasto = new Varasto(12, 23);
        assertEquals(12, virheellinenVarasto.getSaldo(), vertailuTarkkuus );
    }


    

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    @Test
    public void uudellaVarastollaOikeaSaldo() {
        assertEquals(11, varastoSaldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaysEiTeeMitaan() {
        varasto.lisaaVarastoon(6);
        varasto.lisaaVarastoon(-8);

        // saldon pitäisi olla sama kun lisätty määrä ennen negatiivista lisäystä
        assertEquals(6, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void liianSuuriLisaysTayttaaVaraston() {
        varasto.lisaaVarastoon( varasto.getTilavuus() + 2 );
        

        // saldon pitäisi olla sama kuin varaston tilavuus
        assertEquals( varasto.getTilavuus() , varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenOttoEiTeeMitaan() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(-2);

        
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ylisuuriOttoAntaaKaiken() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta( varasto.getTilavuus() + 2);

        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void merkkijonoEsitysOikea() {
        varasto.lisaaVarastoon(8);

        assertEquals("saldo = 10.0, vielä tilaa 2.0", varasto.toString());
    }

}