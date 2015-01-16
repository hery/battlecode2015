package team273;

class RL {

	private static int[] Q;

	public static void main(String[] args) {
		initialize();
	}

	static void initialize() {
		System.out.println("Initializing...");
		Q = new int[10];
		for (int i = 0; i < Q.length; i++) {
			Q[i] = 0;
		}
	}
}