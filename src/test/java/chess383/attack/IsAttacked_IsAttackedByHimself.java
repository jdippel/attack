/*
 *  IsAttacked_IsAttackedByHimself.java
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
import chess383.piece.concretion.king.InitialKing;
import chess383.piece.concretion.queen.Queen;
import chess383.piece.concretion.rook.Rook;
import chess383.player.Player;

/**
 * <p>
 * The class IsAttacked_IsAttackedByHimself implements an upper tester
 * 
 * This test case is really special because there is no requirement.
 * Attacking is the question in combination with king moves - afterwards the king may not be captured by an opponents piece.
 * Thus it is a specification to interrupt the search if the location under inspection is occupied by an opponents piece.
 * </p>
 *
 * @author    Jörg Dippel
 * @version   October 2020
 *
 */
@DisplayName("the public method boolean isAttacked( ) for class Attack is tested for given player lists")
public class IsAttacked_IsAttackedByHimself {  
    
    private Attack define() {
        
        Player firstPlayer = Player.create( ColorEnum.WHITE, Arrays.asList( 
                InitialKing.create( "e1" ), Rook.create( "h1" ) ));
        
        Player secondPlayer = Player.create( ColorEnum.BLACK, Arrays.asList( 
                InitialKing.create( "e8" ), Queen.create( "f8" ) ));
      
        return Attack.create( firstPlayer, secondPlayer );
    }
    
    @Test
    @DisplayName("isAttacked(): return false for a location which is occupied by an opponent's piece.")
    public void isAttackedForKingLocationReturnsFalse_IfLocationIsOccupiedByAnOpponentsPiece() {
        
        final String LOCATION_UNDER_TEST = "f8";
        
        ICoordinateFactory.STANDARD.get();
        Attack attack = define();
        
        assertThat( false )
                .as( String.format( "Given two player lists then it is checked whether square %s is attacked by the opponent", LOCATION_UNDER_TEST ) )
                .isEqualTo( attack.isAttacked( LOCATION_UNDER_TEST ) );
    }
}


