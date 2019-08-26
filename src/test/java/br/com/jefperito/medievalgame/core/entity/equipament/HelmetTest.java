package br.com.jefperito.medievalgame.core.entity.equipament;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelmetTest {

    @Test
    public void shouldBuildABasicHelmet() {
        Helmet basicHelmet = Helmet.basic();

        assertThat(basicHelmet.getDefensePoints()).isEqualTo(8);
    }
}