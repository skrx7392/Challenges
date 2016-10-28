package challenges;

public class LuckyTriples {

	public int luckyTriples(int[] list) {
		int count = 0;
		if(list.length<3)
			return 0;
		int[] res = new int[list.length];
		for(int i=0; i<list.length; i++) {
			for(int j=i+1; j<list.length; j++) {
				if(list[j]%list[i]==0){
					res[j] += 1;
					count += res[i];
				}
			}
		}
		return count;
	}
	
}
