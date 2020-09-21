package sparsearray;

/**
 * @author LingFeng
 * @create 2020-09-10 2:48 下午
 */
public class SparseArray {

    public static void main(String[] args) {
        //生成二位数组
        int chess[][] = new int[11][11];
        //赋值
        chess[1][2] = 1;
        chess[2][3] = 2;
        //遍历现有的二维数组
        System.out.println("double array is :");
        for(int[] row : chess){
            for(int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //获取有效数据的数量；
        int sum = 0;
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j <chess[i].length ; j++) {
                if(chess[i][j] != 0){
                    sum++;
                }
            }
        }

        //创建稀疏数组
        int[][] sparsearray = new int[sum+1][3];
        sparsearray[0][0] = chess.length;
        sparsearray[0][1] = chess[0].length;
        sparsearray[0][2] = sum;

        //有效值计数
        int count = 0;
        //遍历原数组，赋值稀疏数组
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[i].length; j++) {
                if(chess[i][j] != 0 ){
                    count++;
                    sparsearray[count][0] = i;
                    sparsearray[count][1] = j;
                    sparsearray[count][2] = chess[i][j];

                }
            }
        }
        //输出稀疏数组
        System.out.println("sparse array : ");
        for (int i = 0; i <sparsearray.length; i++) {
            System.out.printf("%d\t%d\t%d\n",sparsearray[i][0],sparsearray[i][1],sparsearray[i][2]);
        }
        System.out.println();


        //将稀疏数组转换成二维数组
        int chess1[][] = new int[sparsearray[0][0]][sparsearray[0][1]];
        for (int i = 1; i <sparsearray.length; i++) {
            chess1[sparsearray[i][0]][sparsearray[i][1]] = sparsearray[i][2];
        }

        //输出二维数组
        System.out.println("double array is :");
        for(int[] row : chess1 ){
            for(int colume : row){
                System.out.printf("%d\t",colume);
            }
            System.out.println();
        }

    }
}
