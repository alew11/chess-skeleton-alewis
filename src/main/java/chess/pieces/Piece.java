package chess.pieces;

import java.util.Map;
import java.util.Set;

import chess.Player;
import chess.Position;

/**
 * A base class for chess pieces
 */
public abstract class Piece {
    private final Player owner;

    protected Piece(Player owner) {
        this.owner = owner;
    }

    public char getIdentifier() {
        char id = getIdentifyingCharacter();
        if (owner.equals(Player.White)) {
            return Character.toLowerCase(id);
        } else {
            return Character.toUpperCase(id);
        }
    }

    public Player getOwner() {
        return owner;
    }

    protected abstract char getIdentifyingCharacter();
    
    public abstract Set<Position> getPossibleMoves(Map<Position, Piece> positionToPieceMap, Position originalPos);
}
