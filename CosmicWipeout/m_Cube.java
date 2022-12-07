package CosmicWipeout;

import java.util.ArrayList;
import java.util.Random;

public class m_Cube {
        protected ArrayList<m_Side> sides;
        protected class m_Side{
            protected String name;
            protected int value;

            //purpose: constructs and initializes m_Side object.
            //inputs: self - the m_Side object being constructed.
            //        name - a string
            // 	      value - an int
            //outputs: none
            //assumptions: none.
            protected m_Side(String name, int value) {
                this.name = name;
                this.value = value;
            }
        }
        //purpose: "roll" a cube (randomly select one of its sides)
        //inputs: self.
        //outputs: returns random side.
        //assumptions: sides ArrayList exists.
        public m_Side roll() {
            Random random = new Random();
            return sides.get(random.nextInt(sides.size()));
        }

        /*
        public static m_Side valueToName(int value){ //is there a better way?
            return new m_Side()
        }
        //gonna need to extend the side class
         */

        //purpose: construct and initialize a generic cube.
        //inputs: self - the m_Cube object being created.
        //outputs: none.
        //assumptions: none.
        public m_Cube() {
            sides = new ArrayList<m_Side>();
            sides.add(new m_Side("SWIRLS", 2));
            sides.add(new m_Side("LIGHTNING BOLTS", 4));
            sides.add(new m_Side("FIVE", 5));
            sides.add(new m_Side("STARS", 6));
            sides.add(new m_Side("TEN", 10));
        }

    }
    class m_RegularCube extends m_Cube{
        //purpose: construct and initialize an m_Cube with no flaming sun.
        //inputs: self - the m_Cube object being created.
        //outputs: none.
        //assumptions: none.
        public m_RegularCube() {
            sides.add(new m_Side("TRIANGULAR GLYPHS", 3));
        }
    }

    class m_FlamingSunCube extends m_Cube{
        //purpose: construct and initialize a m_Cube with a flaming sun.
        //inputs: self - the m_Cube object being created.
        //outputs: none.
        //assumptions: none.
        public m_FlamingSunCube() {
            sides.add(new m_Side("FLAMING SUN", 0));
        }
    }