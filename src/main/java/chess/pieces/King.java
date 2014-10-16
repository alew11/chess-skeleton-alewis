package chess.pieces;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * The King class
 */
public class King extends Piece {
    public King(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'k';
    }

	@Override
	public Set<Position> getPossibleMoves(GameState state, Position originalPos) {
		
		Set<Position> possiblePositions = new HashSet<Position>();
		
		// Left, Up
		possiblePositions.addAll(getMovesByDirection(state, originalPos, -1, 1));
		// Right, Up
		possiblePositions.addAll(getMovesByDirection(state, originalPos, 1, 1));
		// Right, Down
		possiblePositions.addAll(getMovesByDirection(state, originalPos, 1, -1));
		// Left, Down
		possiblePositions.addAll(getMovesByDirection(state, originalPos, -1, -1));
		// Left
		possiblePositions.addAll(getMovesByDirection(state, originalPos, -1, 0));
		// Up
		possiblePositions.addAll(getMovesByDirection(state, originalPos, 0, 1));
		// Right
		possiblePositions.addAll(getMovesByDirection(state, originalPos, 1, 0));
		// Down
		possiblePositions.addAll(getMovesByDirection(state, originalPos, 0, -1));

		return possiblePositions;
	}
	
	private Set<Position> getMovesByDirection(GameState state, Position originalPos, int horiz, int vert) {
		
		Map<Position, Piece> positionToPieceMap = state.getCurrentPositions();
		Set<Position> possiblePositions = new HashSet<Position>();
		Player player = positionToPieceMap.get(originalPos).getOwner();
		char col = originalPos.getColumn();
		int row = originalPos.getRow();
		
		col += horiz;
		row += vert;
		
		// While the piece is still on the board
		if (col >= Position.MIN_COLUMN && col <= Position.MAX_COLUMN && row >= Position.MIN_ROW && row <= Position.MAX_ROW) {
			Position testPos = new Position(col, row);
			
			// If no piece is on the next square add the square to possibilities
			if (positionToPieceMap.get(testPos) == null) {
				possiblePositions.add(testPos);
			} else {
				// If the piece is the opponents add the square, else do not
				if (!positionToPieceMap.get(testPos).getOwner().equals(player)) {
					possiblePositions.add(testPos);
				} 
			}
		}
		
		return possiblePositions;
	}
}
