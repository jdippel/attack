/*
 *  IsAttacked_Bugs.java
 *
 *  chess383 is a collection of chess related utilities.
 *  Copyright (C) 2020 Jörg Dippel
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package chess383.attack;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess383.ColorEnum;
import chess383.ICoordinateFactory;
import chess383.piece.concretion.bishop.Bishop;
import chess383.piece.concretion.king.InitialKing;
import chess383.piece.concretion.knight.Knight;
import chess383.piece.concretion.pawn.InitialWhitePawn;
import chess383.piece.concretion.pawn.MovedWhitePawn;
import chess383.piece.concretion.queen.Queen;
import chess383.piece.concretion.rook.Rook;
import chess383.player.Player;

/**
 * <p>
 * The class IsAttacked_Bugs implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   August 2020
 *
 */
@DisplayName("the public method boolean isAttacked( ) for class Attack is tested for given player lists")
public class IsAttacked_Bugs {  
    
    @Test
    @DisplayName("isAttacked(): pinned knight cannot move.")
    public void isAttackedForKingLocationReturnsTrue_IfPinnedKnightMoves() {
        
        // Example is from ICOfY Database:
        // "e4", "d5", "exd5", "Qxd5", "Nc3", "Qa5", "d4", "e5", "Bc4", "Nc6", "d5", "Nd4", "Be3", "Bc5", "Ne2"
        
        final String LOCATION_UNDER_TEST = "e1";
        
        ICoordinateFactory.STANDARD.get();
        Player whitePlayer = Player.create( ColorEnum.WHITE, Arrays.asList( 
                InitialKing.create( "e1" ), Queen.create( "d1" ), Rook.create( "a1" ), Rook.create( "h1" ), Knight.create( "e2" ), Knight.create( "g1" ), Bishop.create( "e3" ), Bishop.create( "c4" ),
                InitialWhitePawn.create( "a2" ), InitialWhitePawn.create( "b2" ), InitialWhitePawn.create( "c2" ), MovedWhitePawn.create( "d5" ), InitialWhitePawn.create( "f2" ), InitialWhitePawn.create( "g2" ), InitialWhitePawn.create( "h2" ) ));
        
        Player blackPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( 
                InitialKing.create( "e8" ), Queen.create( "a5" ) ));
        
        Attack attack = Attack.create( whitePlayer, blackPlayer );
        
        assertThat( true )
                .as( String.format( "Given two player lists then it is checked whether square %s is attacked by the opponent", LOCATION_UNDER_TEST ) )
                .isEqualTo( attack.isAttacked( LOCATION_UNDER_TEST ) );
    }
}


