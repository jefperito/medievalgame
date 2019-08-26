package br.com.jefperito.medievalgame.core.entity.equipament;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BootTest {

    @Test
    public void shouldBuildABasicBoot() {
        Boot basicBoot = Boot.basic();

        assertThat(basicBoot.getDefensePoints()).isEqualTo(6);
        assertThat(basicBoot.getImpetiusPoints()).isEqualTo(9);
    }
}