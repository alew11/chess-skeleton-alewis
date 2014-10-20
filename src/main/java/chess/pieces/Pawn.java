package chess.pieces;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import chess.Player;
import chess.Position;

/**
 * The Pawn
 * PLAYER COLOR IMPORTANT 
 * Moves 1 space vertically from anywhere (except edge)
 * Optionally can move twice if yet unmoved
 * Takes diagonally forwards
 */
public class Pawn extends Piece {
    public Pawn(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'p';
    }

	@Override
	public Set<Position> getPossibleMoves(Map<Position, Piece> board, Position originalPos) {
		
		Set<Position> possiblePositions = new HashSet<Position>();
		
		Player player = board.get(originalPos).getOwner();
		char col = originalPos.getColumn();
		int row = originalPos.getRow();
		int direction;
		
		// If white up the board, if black down the board
		if (player == Player.White) {
			direction = 1;
		} else {
			direction = -1;
		}
		
		// If edge of board no moves
		if ((row == 8 && player == Player.White) || row == 1 && player == Player.Black) {
			return null;
		}
		
		// One move forward
		if (board.get(new Position(col, row + direction)) == null) {
			possiblePositions.add(new Position(col, row + (direction)));
		}
		
		// Two forward
		if (board.get(new Position(col, row + direction)) == null && board.get(new Position(col, row + direction + direction)) == null && row == 2 && player == Player.White) {
			possiblePositions.add(new Position(col, row + direction + direction));
		}
		if (board.get(new Position(col, row + direction)) == null && board.get(new Position(col, row + direction + direction)) == null && row == 7 && player == Player.Black) {
			possiblePositions.add(new Position(col, row + direction + direction));
		}
		
		// Take left
		if (board.get(new Position((char)(col - 1), row + direction)) != null && board.get(new Position((char)(col - 1), row + direction)).getOwner() != player) {
			possiblePositions.add(new Position((char)(col - 1), row + direction));
		}
		
		// Take right
		if (board.get(new Position((char)(col + 1), row + direction)) != null && board.get(new Position((char)(col + 1), row + direction)).getOwner() != player) {
			possiblePositions.add(new Position((char)(col + 1), row + direction));
		}
		
		return possiblePositions;
	}
	
}
