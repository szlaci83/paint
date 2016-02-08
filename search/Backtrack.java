package search;


import java.util.Stack;


import state.Staterep;

public class Backtrack {

	protected Stack<Node> backtrack = new Stack<Node>();
	protected Node actNode 	 		= null;

	protected enum Action{NO_ACTION, POP_NODE, PUSH_NODE };
	protected Action lastAction = Action.NO_ACTION;

	protected void pop(){
		backtrack.pop();
		actNode = backtrack.isEmpty()?null:backtrack.peek();
		lastAction = Action.POP_NODE;
	}

	protected void push(Node node){
		backtrack.push( node );
		actNode = node;
		lastAction = Action.PUSH_NODE;
	}

	public boolean circleWatch(){
		for (int i = 0; i < backtrack.size()-1; i++) {
			if(actNode.getState().equals( ((Node)backtrack.get(i)).getState() ) ){
				return true;
			}
		}
		return false;
	}

	public boolean routeLimit(){
		if(backtrack.size() > 8){
			return true;
		}
		return false;
	}

	public Node search(Staterep beginState){
		backtrack.removeAllElements();
		this.push(new Node( beginState ) );
          
		while(actNode != null && ( lastAction == Action.POP_NODE || !actNode.getState().isGoal() )){
			// System.out.println(actNode.toString());
			if (( circleWatch() ) || (routeLimit())) {pop();}
			else if( actNode.hasMoreChildren() ) {push( actNode.getNextChild() );}
			else pop();
		}

		return actNode;
	}


	public static void main(String[] args){
		Backtrack problem = new Backtrack();
		Staterep beginState = new Staterep();
		long startTime = System.currentTimeMillis();
		Node solution = problem.search( beginState );
		startTime = System.currentTimeMillis() - startTime; 

		if(solution != null){
			solution.utKiir(System.out);
			System.out.println("A megoldás: " + problem.backtrack.size()+" lépés.");
			System.out.println("Valamint : " + startTime +" millisecundum időt vesz igénybe.");
		}
		else{
			System.out.println("Nincs megoldás, �s erre " + startTime +" ms idő alatt jöttem rá.");
		}

	}
}
