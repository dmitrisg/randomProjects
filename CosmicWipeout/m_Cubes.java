package CosmicWipeout;

public class m_Cubes {
    private m_Collection<m_Cube> cubes;
    private final m_Collection<m_Cube> defaultCubes;

    //purpose: construct and initialize m_Cubes object.
    //inputs: self - the m_Cubes object being created
    //outputs: none.
    //assumptions: none.
    public m_Cubes(){
        defaultCubes = createDefaultCubes();
        resetCubes();
    }

    //purpose: create and populate collection of default cubes.
    //inputs: none.
    //outputs: defaultCubes - collection of default cubes.
    //assumptions: none.
    private m_Collection<m_Cube> createDefaultCubes(){
        m_Collection<m_Cube> defaultCubes = new m_Collection<m_Cube>();
        for (int cubeIndex = 0; cubeIndex < 4; cubeIndex++)
            defaultCubes.add(new m_RegularCube());
        defaultCubes.add(new m_FlamingSunCube());
        return defaultCubes;
    }

    //purpose: remove a number of cubes.
    //inputs: removeQuantity - how many cubes to remove.
    //outputs: none.
    //assumptions: cubes exists.
    public void removeCubesScored(int removeQuantity) {
        if (removeQuantity != 0) {
            for (int i = removeQuantity-1; i >= 0; i--) {
                cubes.remove(i);
            }//could use a foreach but its being wierd
        }
    }

    //purpose: reset cubes.
    //inputs: none.
    //outputs: none.
    //assumptions: defaultCubes exists.
    public void resetCubes(){ //bad performance to make a new one?
        this.cubes = new m_Collection<m_Cube>(); //put this in constructor but leading to some bugs
        for (m_Cube cube : defaultCubes){
            if (!this.cubes.contains(cube))
                this.cubes.add(cube);
        }
    }

    //purpose: gets cubes m_Collection.
    //inputs: self.
    //outputs: returns cubes m_Collection
    //assumptions: cubes m_Collection exists.
    public m_Collection<m_Cube> getCubes(){
        return this.cubes;
    }
}

