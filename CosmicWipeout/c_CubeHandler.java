package CosmicWipeout;
public class c_CubeHandler {
    private c_Scorer scorer;
    private View view;
    private c_RollInfo rollInfo;

    //purpose: construct and initialize c_CubeHandler object.
    //inputs: self - the c_CubeHandler object being created
    //        view - View object
    //outputs: none.
    //assumptions: none.
    public c_CubeHandler(View view) {
        this.rollInfo = new c_RollInfo();
        this.scorer = new c_Scorer(rollInfo);
        this.view = view;
    }
        //purpose: set score according to roll result.
        //inputs: self.
        //        cubes - collection of cubes that are in play,
        //        flashMatchValue - value that cannot be rolled because of flash.
        //outputs: returns calculated score.
        //assumptions: none.
        public int calculateScore(m_Collection<m_Cube> cubes, int flashMatchValue){
            rollInfo.reset();
            return scorer.calculateScore(getRolledValues(rollLoop(cubes)), flashMatchValue);
        }

        //purpose: extract rolled values from rolled sides.
        //inputs:  rolledSides - m_Collection<m_Cube.m_Side> of the sides rolled.
        //outputs: returns rolled values.
        //assumptions: none.
        private m_Collection<Integer> getRolledValues(m_Collection<m_Cube.m_Side> rolledSides) {
            m_Collection<Integer> rolledValues = new m_Collection<Integer>();
            for (m_Cube.m_Side side : rolledSides) {
                rolledValues.add(side.value);
            }
            return rolledValues;
        }

        //purpose: rolls each cube that is in play.
        //inputs: cubes - m_Collection<m_Cube.m_Side> with cubes
        //outputs: displays results of each cube, returns m_Collection<m_Cube.m_Side> with results of rolling.
        //assumptions: none.
        private m_Collection<m_Cube.m_Side> rollLoop(m_Collection<m_Cube> cubes){
            String rolledSideRecord = "";
            m_Collection<m_Cube.m_Side>  rolledSides = new m_Collection<m_Cube.m_Side> ();
            m_Cube.m_Side rolledSide;
            for (m_Cube cube : cubes) {
                rolledSide = cube.roll();
                rolledSideRecord += rolledSide.name+ "(" + rolledSide.value + ")" + "\r\n";
                rolledSides.add(rolledSide);
            }
            view.display(v_DisplayType.SIDES, rolledSideRecord);
            return rolledSides;
        }

        //purpose: reset roll info.
        //inputs: none.
        //outputs: none.
        //assumptions: rollInfo exists.
        public void resetRollInfo(){
            rollInfo.reset();
        }

        //purpose: get the number of cubes scored.
        //inputs: none.
        //outputs: returns number of cubes scored.
        //assumptions: rollInfo exists.
        public int getCubesScoredCount(){
            return rollInfo.getCubesScoredCount();
        }

       //purpose: get the value that a flash was formed with.
       //inputs: none.
       //outputs: returns the value that a flash was formed with.
       //assumptions: rollInfo exists.
        public int getFlashSide(){
            return rollInfo.getFlashSide();
        }

}
