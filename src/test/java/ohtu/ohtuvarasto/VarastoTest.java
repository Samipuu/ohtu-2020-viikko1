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

    Varasto varasto;
    Varasto varastoSaldolla;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varastoSaldolla = new Varasto(20, 5);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoVarastonSaldolla() {
        assertEquals(5, varastoSaldolla.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoVuotaaYlimaaraisen() {
        varastoSaldolla = new Varasto(10,20);
        assertEquals(10, varastoSaldolla.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiNegatiivistaVarastoaSaldolla() {
        varastoSaldolla = new Varasto(-20,10);
        assertEquals(0, varastoSaldolla.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void eiNegatiivistaSaldoa() {
        varastoSaldolla = new Varasto(10 -30.0);
        assertEquals(0.0, varastoSaldolla.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiNegatiivistaAlkuSaldoa() {
        Varasto varastoNegatiivinen = new Varasto(-20, -30);
        assertEquals(0, varastoNegatiivinen.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiNegatiivistaVarastoa() {
        varasto = new Varasto(-20);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiLisätäNegatiivistaVarastoon() {
        varastoSaldolla.lisaaVarastoon(-5);
        assertEquals(5, varastoSaldolla.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisatessaYlimaarainenVuotaa() {
        varastoSaldolla.lisaaVarastoon(20);
        assertEquals(20, varastoSaldolla.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoidaOttaaNegatiivistaMaaraa() {
        varastoSaldolla.otaVarastosta(-20);
        assertEquals(5, varastoSaldolla.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaEnempaaKuinVarastossa() {
        double saatu = varastoSaldolla.otaVarastosta(20);
        assertEquals(5, saatu, vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimii() {
        assertEquals("saldo = 5.0, vielä tilaa 15.0", varastoSaldolla.toString());
    }
    
    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
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

}