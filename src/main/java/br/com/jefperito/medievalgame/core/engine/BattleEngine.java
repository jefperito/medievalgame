package br.com.jefperito.medievalgame.core.engine;

import br.com.jefperito.medievalgame.core.engine.plugin.Listener;
import br.com.jefperito.medievalgame.core.entity.creature.Character;
import br.com.jefperito.medievalgame.core.entity.creature.Creature;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleEngine {

    private static final int POINTS_TO_ATTACK = 100;
    private List<Listener> listeners;
    private double accumulatorCharacter = 0;
    private double accumulatorEnemy = 0;
    private AttackTurn attackTurn;

    private BattleEngine() {
        listeners = new ArrayList<>();
    }

    public static BattleEngine start() {
        return new BattleEngine();
    }

    public List<String> battle(Character character, Creature enemy) {
        try {
            listeners.forEach(listener -> listener.onEvent(Event.PRE_COMBAT, character));
            return startBattle(character, enemy);
        } finally {
            listeners.forEach(listener -> listener.onEvent(Event.POST_COMBAT, character));
        }
    }

    private List<String> startBattle(Character character, Creature enemy) {
        List<String> battleMessages = new ArrayList<>();
        while (character.isAlive() && enemy.isAlive()) {
            accumulatorCharacter += new Random().nextInt(8) * .7 * character.getVelocityPoints();
            accumulatorEnemy += new Random().nextInt(8) * .7 * enemy.getVelocityPoints();

            defineAttackTurn(accumulatorCharacter, accumulatorEnemy);
            if (attackTurn == AttackTurn.NO_OPTIONS) {
                continue;
            }
            battleMessages.addAll(attackPhase(character, enemy));
            decreaseAccumulator();
        }
        battleMessages.add((character.isAlive() ? enemy.getName() : character.getName()) + " faleceu");
        return battleMessages;
    }

    private void decreaseAccumulator() {
        if (attackTurn == AttackTurn.CHARACTER) {
            accumulatorCharacter -= POINTS_TO_ATTACK;
        }
        if (attackTurn == AttackTurn.ENEMY) {
            accumulatorEnemy -= POINTS_TO_ATTACK;
        }
    }

    private List<String> attackPhase(Character character, Creature enemy) {
        List<String> attackPhaseMessages = new ArrayList<>();

        if (attackTurn == AttackTurn.CHARACTER) {
            attackPhaseMessages.add(character.getName() + " atacando");
            int attack = calculateAttack(character);
            int defense = calculateDefense(enemy);

            int damage = attack * (100 / (50 + defense));
            attackPhaseMessages.add(character.getName() + " causou " + damage + " de dano.");
            enemy.decreaseHealthPoints(damage);
        }
        if (attackTurn == AttackTurn.ENEMY) {
            attackPhaseMessages.add(enemy.getName() + " atacando");
            int attack = calculateAttack(enemy);
            int defense = calculateDefense(character);

            int damage = attack - defense;
            attackPhaseMessages.add(enemy.getName() + " causou " + damage + " de dano.");
            character.decreaseHealthPoints(damage);
        }
        return attackPhaseMessages;
    }

    private int calculateDefense(Creature creature) {
        return (int) (creature.getPowerDefense() + (new Random().nextInt(8) * 0.7));
    }

    private int calculateAttack(Creature creature) {
        return (int) (creature.getPowerAttack() + (new Random().nextInt(12) * 0.875));
    }

    private void defineAttackTurn(double accumulatorCharacter, double accumulatorEnemy) {
        if (accumulatorCharacter > accumulatorEnemy && accumulatorCharacter >= POINTS_TO_ATTACK) {
            attackTurn = AttackTurn.CHARACTER;
            return;
        }
        if (accumulatorCharacter < accumulatorEnemy && accumulatorEnemy >= POINTS_TO_ATTACK) {
            attackTurn = AttackTurn.ENEMY;
            return;
        }
        if (accumulatorCharacter == accumulatorEnemy && (accumulatorCharacter >= POINTS_TO_ATTACK || accumulatorEnemy >= POINTS_TO_ATTACK)) {
            int randomChoose = new Random().nextInt(2);
            attackTurn = randomChoose % 2 == 0 ? AttackTurn.CHARACTER : AttackTurn.ENEMY;
            return;
        }
        attackTurn = AttackTurn.NO_OPTIONS;
    }

    enum AttackTurn {
        CHARACTER,
        ENEMY,
        NO_OPTIONS
    }
}
