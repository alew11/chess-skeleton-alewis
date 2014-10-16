package chess.pieces;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import chess.GameState;
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
	public Set<Position> getPossibleMoves(GameState state, Position originalPos) {
		
		Map<Position, Piece> positionToPieceMap = state.getCurrentPositions();
		Set<Position> possiblePositions = new HashSet<Position>();
		int direction;
		char col = originalPos.getColumn();
		int row = originalPos.getRow();
		Player player = positionToPieceMap.get(originalPos).getOwner();
		
		// Check to make sure within bounds
		// TODO: Can expand to handle positions that aren't possible (Pawn in back row)
		if (row == Position.MAX_ROW || row == Position.MIN_ROW) {
			return null;
		}
		
		if (player.equals(Player.White)) {
			direction = 1;
		} else {
			direction = -1;
		}
		
		// One space ahead
		if (!positionToPieceMap.containsKey(new Position(col, row + direction))) {
			possiblePositions.add(new Position(col, row + direction));
		}
		
		// Two space ahead
		if ((player.equals(Player.White) && row == 2) || (player.equals(Player.Black) && row == 7)) {	
		
			if (!positionToPieceMap.containsKey(new Position(col, row + 2*(direction)))) {
				possiblePositions.add(new Position(col, row + 2*(direction)));
			}
		}
		
		
		// Take diagonal
		Piece diagLeft = positionToPieceMap.get(new Position((char)(col - 1), row + direction));
		Piece diagRight = positionToPieceMap.get(new Position((char)(col + 1), row + direction));
		
		if (diagLeft != null && !diagLeft.getOwner().equals(player)) {
			possiblePositions.add(new Position((char)(col - 1), row + direction));
		}
		if (diagRight != null && !diagRight.getOwner().equals(player)) {
			possiblePositions.add(new Position((char)(col + 1), row + direction));
		}

		return possiblePositions;
	}
	
}
