
public class Computer {
	
	
	public int X,Y;
	
	public static int MAX = Main.MaxXY-1;
	
	int map_weight[][] = 
		  { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	
	public void SetWeight(int _x,int _y,int _weight) {
		
		if((_x%2==0||_y%2==0)) {
			_weight = _weight*2;//짝수이면 확률이 늘어난다.
		}
		
		if(_x==0||_y==0||_x==MAX||_y==MAX) {
			_weight = _weight*10;//변 부분은 먹으면 좋다.
		}
		if((_x==0&&_y==0)||(_x==MAX&&_y==MAX)||(_x==0&&_y==MAX)||(_x==MAX&&_y==0)) {// 네 귀퉁이는 무조건 먹자.
			_weight = _weight*100;//100으로 설정
		}
		else if((_x<=1&&_y<=1)||(_x>=MAX-1&&_y>=MAX-1)) {// 네 귀퉁이가 아닌데 2x2 사각형에 들어갈경우
			_weight = 1;//쓰레기 가중치가 된다.
		}
		
		map_weight[_x][_y] = _weight;
		
	}
	
	public void ThinkBestPosition() {
		
		int max = 0;
		
		for(int i =0;i<16;i++) {
			
			for(int j=0;j<16;j++) {
				
				//System.out.print(map_weight[j][i]+" ");
				
				if(max<map_weight[i][j]) {
					max = map_weight[i][j];
					X = i;
					Y = j;
				}
				
			}
			//System.out.println("");
		}
		
		//System.out.println("max = "+max+" X = "+X+" Y : " +Y+"\n\n");
	}

}
