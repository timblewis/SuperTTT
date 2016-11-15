package superTTT.AI;


public class STTTPosScore implements Comparable<STTTPosScore>{
	protected double value;
	protected int depth;
	
	public STTTPosScore(double value, int depth){
		if(value < -1 || value > 1 || depth < 0){
			throw new IllegalArgumentException();
		}
		this.value = value;
		this.depth = depth;
	}
	
	public double getValue(){
		return value;
	}
	
	public void setValue(double value){
		this.value = value;
	}
	
	public int getDepth(){
		return depth;
	}
	
	public void setDepth(int depth){
		this.depth = depth;
	}
	
	public void incrementDepth(){
		depth++;
	}

	@Override
	public int compareTo(STTTPosScore score) {
		if(value > score.value){
			return 1;
		} else if (value < score.value){
			return -1;
		} else{
			if(value == 1.0){
				if(depth > score.depth){
					return -1;
				} else if(depth < score.depth){
					return 1;
				}
				return 0;
			} else if (value == -1.0){
				if(depth > score.depth){
					return 1;
				} else if(depth < score.depth){
					return -1;
				}
				return 0;
			}
			return 0;
		}
	}
}
