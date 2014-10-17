package chess.pieces;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import chess.Player;
import chess.Position;

/**
 * The 'Rook' class
 */
public class Rook extends Piece {

    public Rook(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'r';
    }

	@Override
	public Set<Position> getPossibleMoves(Map<Position, Piece> board, Position originalPos) {
		
		Set<Position> possiblePositions = new HashSet<Position>();
		
		// Left
		possiblePositions.addAll(getMovesByDirection(board, originalPos, -1, 0));
		// Up
		possiblePositions.addAll(getMovesByDirection(board, originalPos, 0, 1));
		// Right
		possiblePositions.addAll(getMovesByDirection(board, originalPos, 1, 0));
		// Down
		possiblePositions.addAll(getMovesByDirection(board, originalPos, 0, -1));

		
		return possiblePositions;
	}
	
	private Set<Position> getMovesByDirection(Map<Position, Piece> board, Position originalPos, int horiz, int vert) {
		
		Set<Position> possiblePositions = new HashSet<Position>();
		Player player = board.get(originalPos).getOwner();
		char col = originalPos.getColumn();
		int row = originalPos.getRow();
		
		col += horiz;
		row += vert;
		
		// While the piece is still on the board
		while (col >= Position.MIN_COLUMN && col <= Position.MAX_COLUMN && row >= Position.MIN_ROW && row <= Position.MAX_ROW) {
			Position testPos = new Position(col, row);
			
			// If no piece is on the next square add the square to possibilities
			if (board.get(testPos) == null) {
				possiblePositions.add(testPos);
			} else {
				// If the piece is the opponents add the square, else do not
				if (!board.get(testPos).getOwner().equals(player)) {
					possiblePositions.add(testPos);
				} 
				break;
			}
			col += horiz;
			row += vert;
		}
		
		return possiblePositions;
	}
}
