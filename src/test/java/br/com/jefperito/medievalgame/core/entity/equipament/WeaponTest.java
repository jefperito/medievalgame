package br.com.jefperito.medievalgame.core.entity.equipament;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WeaponTest {

    @Test
    public void shouldBuildABasicSword() {
        Weapon basicSword = Weapon.basicSword();

        assertThat(basicSword.getAttackPoints()).isEqualTo(8);
    }
}