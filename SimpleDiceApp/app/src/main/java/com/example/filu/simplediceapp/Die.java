package com.example.filu.simplediceapp;

import java.util.ArrayList;

/**
 * Created by filu on 20.04.17.
 */

public class Die {
    private String optionalName;
    private ArrayList<DieSide> sides;
    private DieSide current;

    Die(String name) {
        optionalName = name;
        sides = new ArrayList<>();
        current = null;
    }
    Die(ArrayList<DieSide> sides, String name) {
        optionalName = name;
        this.sides = sides;
        current = roll();
    }

    int indexOf(DieSide toFind) {
        int index = 0;
        for(DieSide knownSide : sides)
        {
            if(knownSide.equals(toFind))
            {
                return index;
            }
            index++;
        }
        return -1;
    }

    boolean contains(DieSide side) {
        for (DieSide knownSide : sides) {
            if(knownSide.equals(side))
            {
                return true;
            }
        }
        return false;
    }

    DieSide roll() {
        if (!sides.isEmpty()) {
            RandomIntGenerator generator = new RandomIntGenerator();
            int rolledIndex = generator.generate(sides.size() - 1);
            current = sides.get(rolledIndex);
            return current;
        }
        return null;
    }

    ArrayList<DieSide> getSides() {
        return sides;
    };

    DieSide getCurrent() {
        return current;
    }
    String getName() {
        return optionalName;
    }

    void addSide(DieSide toAdd) {
        if(toAdd != null) {
            sides.add(toAdd);
        }
    }

    void remove(int index) {
        sides.remove(index);
    }

    void remove(DieSide toRemove) {
        sides.remove(toRemove);
    }

    void replace(int index, DieSide newSide) {
        sides.set(index, newSide);
    }

    void replace(DieSide toReplace, DieSide newSide) {
        int index = indexOf(toReplace);
        sides.set(index, newSide);
    }

    @Override
    public boolean equals(Object other) {
        Die cast_other = ((Die) other);
        if(getSides().size() == cast_other.getSides().size())
        {
            for(int i = 0; i < cast_other.getSides().size(); i++)
            {
                if(!(getSides().get(i).equals(cast_other.getSides().get(i))))
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
