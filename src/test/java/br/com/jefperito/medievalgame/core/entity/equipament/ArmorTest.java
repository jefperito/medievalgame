package br.com.jefperito.medievalgame.core.entity.equipament;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArmorTest {

    @Test
    public void shouldBuildABasicArmor() {
        Armor basicArmor = Armor.basic();

        assertThat(basicArmor.getDefensePoints()).isEqualTo(7);
    }
}