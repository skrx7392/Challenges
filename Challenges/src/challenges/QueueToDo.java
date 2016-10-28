package challenges;

public class QueueToDo {
	
	public static int queueToDo(int start, int length) {
		int checkSum=0;
		for(int i=0; i<length; i++) {
			int a = start+length*i+length-1-i;
			int b = start+length*i-1;
			if(b<1)
				b=0;
			int[] arr1 = new int[] {a, 1, a+1, 0};
			int[] arr2 = new int[] {b, 1, b+1, 0};
			checkSum = checkSum ^ arr1[a%4] ^ arr2[b%4];
		}
		return checkSum;
	}
}
