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

}
