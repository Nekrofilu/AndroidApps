package com.example.filu.simplediceapp;

import java.util.ArrayList;

/**
 * Created by filu on 20.04.17.
 */

public class DieSet {
    private String name;
    private ArrayList<Die> dice;

    int indexOf(Die toFind) {
        int index = 0;
        for(Die knownDie : dice)
        {
            if(knownDie.equals(toFind))
            {
                return index;
            }
            index++;
        }
        return -1;
    }

    boolean contains(Die die) {
        for (Die knownDie : dice) {
            if(knownDie.equals(die))
            {
                return true;
            }
        }
        return false;
    }


    DieSet(String name) {
        this.name = name;
    }
    DieSet(ArrayList<Die> dice, String name) {
        this.dice = dice;
        this.name = name;
    }

    ArrayList<Die> getDice() {
        return dice;
    }

    String getName() {
        return name;
    }

    void addDie(Die toAdd) {
        dice.add(toAdd);
    }

    void remove(int index) {
        dice.remove(index);
    }

    void removeDie(Die toRemove) {
        dice.remove(toRemove);
    }

    void replaceDieAt(int index, Die newDie) {
        dice.set(index, newDie);
    }

    void replaceDie(Die toReplace, Die newDie) {
        int index = indexOf(toReplace);
        dice.set(index, newDie);
    }

    @Override
    public boolean equals(Object other) {
        DieSet cast_other = ((DieSet) other);
        if(dice.size() == cast_other.getDice().size())
        {
            for(int i = 0; i < cast_other.getDice().size(); i++)
            {
                if(!(dice.get(i).equals(cast_other.getDice().get(i))))
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
