import java.util.Random;

public class Autoassociator {
	private int weights[][];
	private int trainingCapacity;
	private Random random = new Random();
	//creating hopeField network with number of courses
	public Autoassociator(CourseArray courses) {
		weights = new int[courses.length()][courses.length()];
		trainingCapacity = (int) (0.14 * weights.length);
	}
	public int getTrainingCapacity() {
		return trainingCapacity;
	}
	public void training(int pattern[]) {
		if(pattern.length == weights.length && trainingCapacity > 0){
			int prod;
			for(int i = 0; i< pattern.length - 1; i++)
				for(int j = i+1; j< pattern.length; j++ ){
					prod = pattern[i] * pattern[j];
					weights[i][j] += prod;
					weights[j][i] += prod;
				}
			trainingCapacity--;
		}

	}
	//implementing a single update and returning the index of randomly selected update
	public int unitUpdate(int neurons[]) {
		int index = random.nextInt(neurons.length);
		unitUpdate(neurons, index);
		return index;
	}
	//implementing single update for specified neuron
	public void unitUpdate(int neurons[], int index) {
		int sum = 0;
		for(int i = 0; i < neurons.length; i++)
			sum += weights[index][i] = neurons[i];
		neurons[index] = sum >= 0 ? 1 : -1;
	}
	//implementing the specified num of updates
	public void chainUpdate(int neurons[], int steps) {
		for(; steps > 0; steps--)
			unitUpdate(neurons);
	}
	//updateing the input until final state
	public void fullUpdate(int neurons[]) {
		boolean flag = false;
		while(!flag){
			int[] copy;
			copy = new int[neurons.length];
			System.arraycopy(neurons, 0, copy, 0, neurons.length);
			unitUpdate(neurons);
			flag = java.util.Arrays.equals(copy, neurons);
		}
	}
}
