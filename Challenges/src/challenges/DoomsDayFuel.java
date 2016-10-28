package challenges;

import java.util.*;

class Rational {
	int num;
	int den;
	public Rational(int num, int den) {
		this.num = num;
		this.den = den;
	}
	public static Rational add(Rational num1, Rational num2) {
		Rational res = new Rational(1,1);
		res.den = num1.den*num2.den;
		res.num = (num1.num*num2.den) + (num2.num*num1.den);
		return res;
	}
	
	public static Rational divide(Rational num1, Rational num2) {
		Rational res = new Rational((num1.num*num2.den), num1.den*num2.num); 
		return new Rational(res.num, res.den);
	}
	
	public static Rational minimize(Rational num) {
		if(num.num!=0 &&num.den!=0) {
			int gcd = getGCD(num.num, num.den);
			num.num=num.num/gcd;
			num.den=num.den/gcd;
		}
		return num;
	}
	
	public static int getGCD(int num1, int num2) {
		if(num2==0) {
			return num1;
		}
		return getGCD(num2, num1%num2);
	}
	
	public static int getLCM(int num1, int num2) {
		return num1*(num2/getGCD(num1, num2));
	}
}

class DoomsDayNode {
	List<Rational> values = new ArrayList<Rational>();
	int stage;
	HashMap<Integer, Integer> nextNodes = new HashMap<Integer, Integer>();
	int sum;
	Rational a;
	Rational r;
	public DoomsDayNode(int[] arr) {
		for(int i=0; i<arr.length; i++) {
			if(arr[i]!=0) {
				nextNodes.put(i, arr[i]);
				this.sum +=arr[i];
			}
		}
		this.values = new ArrayList<Rational>();
		this.a = new Rational(1,1);
		this.r = new Rational(1,1);
	}
}

public class DoomsDayFuel {
	
	public int[] doomsDayFuel(int[][] matrix) {
		DoomsDayNode[] stages = new DoomsDayNode[matrix.length];
		List<Integer> terminalStages = new ArrayList<Integer>();
		for(int i=0; i<matrix.length; i++) {
			stages[i] = new DoomsDayNode(matrix[i]);
			stages[i].stage=i;
			if(stages[i].sum==0)
				terminalStages.add(i);
			if(i==0)
				stages[i].values.add(new Rational(1,1));
		}
		HashMap<Integer, Rational> result = new HashMap<Integer, Rational>();
		Queue<DoomsDayNode> queue = new LinkedList<DoomsDayNode>();
		List<Integer> finishedStages = new ArrayList<Integer>();
		queue.add(stages[0]);
		int[] colSum = new int[matrix.length];
		for(int i=1; i<stages.length; i++) {
			for(int j=0; j<stages.length; j++) {
				colSum[i] += matrix[j][i];
			}
			if(colSum[i]==0) {
				stages[i].a = new Rational(0,1);
				finishedStages.add(i);
			}
		}
		while(!queue.isEmpty()) {
			DoomsDayNode node = queue.remove();
			for(int i:node.nextNodes.keySet()) {
				Rational value = new Rational(node.values.get(node.values.size()-1).num*node.nextNodes.get(i), node.values.get(node.values.size()-1).den*node.sum);
				if(!stages[i].values.isEmpty()){
					if(!finishedStages.contains(i) && terminalStages.contains(i)) {
						stages[i].r = Rational.divide(value, stages[i].a);
						finishedStages.add(i);
					}
				}
				else {
					stages[i].a=value;
				}
				stages[i].values.add(value);
				if(!terminalStages.contains(i))
					queue.add(stages[i]);
			}
			if(finishedStages.size()==terminalStages.size())
				break;
		}
		for(int i:terminalStages) {
			if(stages[i].r.num/stages[i].r.den!=1) {
				Rational temp = Rational.add(new Rational(1,1), new Rational(stages[i].r.num*(-1), stages[i].r.den));
				result.put(i, Rational.minimize(Rational.divide(stages[i].a, temp)));
			}
			else {
				result.put(i, Rational.minimize(stages[i].a));
			}
		}
		int lcm=1;
		for(int i:result.keySet()) {
			lcm = Rational.getLCM(lcm, result.get(i).den);
		}
		int[] res = new int[terminalStages.size()+1];
		int counter=0;
		for(int i:result.keySet()) {
			res[counter] = result.get(i).num*(lcm/result.get(i).den);
			counter++;
		}
		res[terminalStages.size()] = lcm;
		return res;
	}
}
