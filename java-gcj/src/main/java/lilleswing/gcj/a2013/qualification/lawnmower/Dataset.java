package lilleswing.gcj.a2013.qualification.lawnmower;

class Dataset {
    private int[][] lawn;

    public Dataset(final int[][] lawn) {
        this.lawn = lawn;
    }

    public int[][] getLawn() {
        return lawn;
    }

    public int[] getRowMaxes() {
        final int[] rowMaxes = new int[lawn.length];
        for(int i = 0 ; i < lawn.length; i++) {
            int max = Integer.MIN_VALUE;
            for(int j = 0; j < lawn[0].length; j++) {
                if(lawn[i][j] > max) {
                    max = lawn[i][j];
                }
            }
            rowMaxes[i] = max;
        }
        return rowMaxes;
    }

    public int[] getColMaxes() {
        final int[] colMaxes = new int[lawn[0].length];
        for(int j = 0 ; j < lawn[0].length; j++) {
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < lawn.length; i++) {
                if(lawn[i][j] > max) {
                    max = lawn[i][j];
                }
            }
            colMaxes[j] = max;
        }
        return colMaxes;
    }
}
