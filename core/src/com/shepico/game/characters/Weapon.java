package com.shepico.game.characters;

public class Weapon {

    private String nsme;
    private float attackRadius;
    private float attackPeriod;
    private float damage;

    public String getNsme() {
        return nsme;
    }

    public float getAttackRadius() {
        return attackRadius;
    }

    public float getAttackPeriod() {
        return attackPeriod;
    }

    public float getDamage() {
        return damage;
    }

    public Weapon(String nsme, float attackRadius, float attackPeriod, float damage) {
        this.nsme = nsme;
        this.attackRadius = attackRadius;
        this.attackPeriod = attackPeriod;
        this.damage = damage;
    }
}
