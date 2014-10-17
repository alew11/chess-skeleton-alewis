package chess;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class KingSafetyTest {

	private GameState state;

	@Before
	public void setUp() throws Exception {
		state = new GameState();

	}

	@Test
	public void testPins() {
		
		Map<Position, Set<Position>> actualPossible =  new HashMap<Position, Set<Position>>();
    	Set<Position> positionsTo = new HashSet<Position>();
		
		state.placePiece(new King(Player.White), new Position("d1"));
    	state.placePiece(new Pawn(Player.White), new Position("e2"));
    	state.placePiece(new Bishop(Player.Black), new Position("g4"));
    	
    	positionsTo.add(new Position("c1"));
    	positionsTo.add(new Position("c2"));
    	positionsTo.add(new Position("d2"));
    	positionsTo.add(new Position("e1"));

    	actualPossible.put(new Position("d1"), positionsTo);
    	
    	Map<Position, Set<Position>> calcedPossible = state.getPossibleMoveList(state.getCurrentPlayer()); 

    	assertEquals(actualPossible, calcedPossible);
    	
	}
	
	@Test
	public void testKingIntoCheck() {
		
		Map<Position, Set<Position>> actualPossible =  new HashMap<Position, Set<Position>>();
    	Set<Position> positionsTo = new HashSet<Position>();
		
		state.placePiece(new King(Player.White), new Position("e1"));
    	state.placePiece(new Bishop(Player.Black), new Position("g4"));
    	
    	positionsTo.add(new Position("d2"));
    	positionsTo.add(new Position("f1"));
    	positionsTo.add(new Position("f2"));

    	actualPossible.put(new Position("e1"), positionsTo);
    	
    	Map<Position, Set<Position>> calcedPossible = state.getPossibleMoveList(state.getCurrentPlayer()); 

    	assertEquals(actualPossible, calcedPossible);
    	
	}
	
	@Test
	public void testBlackKingPinned() {
		
		state.placePiece(new King(Player.Black), new Position("d7"));
    	state.placePiece(new Rook(Player.Black), new Position("d6"));
    	state.placePiece(new Queen(Player.White), new Position("d3"));
    	
    	state.setCurrentPlayer(Player.Black);
    	
    	Map<Position, Set<Position>> calcedPossible = state.getPossibleMoveList(state.getCurrentPlayer()); 

    	assertEquals(7, calcedPossible.get(new Position("d7")).size());
    	assertEquals(3, calcedPossible.get(new Position("d6")).size());

    	
	}

}
