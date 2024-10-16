
public class Tester {

	private char[][] grid;
	public static final char EMPTY = ' ';
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Ni");
		new Tester(0,0," ");
	}

	public Tester(int width, int height, String initial) {
		width = 4;
		height = 3;
		initial = "ABCDEFGHIJKL";

		this.grid = new char[height][width];
		char[] initialArr = initial.toCharArray();
		System.out.println(initialArr);
		for (int j = 0;j<height;j++) {
			for (int k = 0;k<width;k++) {
				try {
					this.grid[j][k] = initial.charAt((width*j)+k);
				}catch (Exception e) {
					this.grid[j][k] = this.EMPTY;
				}
				
			}
		}
		for(int i=0;i<height;i++) {
			System.out.println(String.format("[%c,%c,%c,%c]",grid[i][0],grid[i][1],grid[i][2],grid[i][3]));
		}
		
	}
}
