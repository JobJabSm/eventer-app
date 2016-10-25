package com.eventer.app.Deck;

import com.wenchao.cardstack.DefaultStackEventListener;

/**
 * Created by Gulzar on 24-10-2016.
 */
public abstract class DeckListener extends DefaultStackEventListener {

    int LEFT = 1, RIGHT = 2;

    DeckListener() {
        super(300);
    }

    abstract void onCardSwiped(int direction, int swipedIndex);

    @Override
    public void discarded(int index, int direction) {
        onCardSwiped(direction == 0 || direction == 2 ? LEFT : RIGHT, index - 1);
    }
}
