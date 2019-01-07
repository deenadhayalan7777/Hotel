package hotel;

import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Constraint {
	
	public Set<Order> evaluate(List<Integer> operator,List<Set<Order>> constraints)
	{
		
		Stack<Integer> operatorStack=new Stack<Integer>();
		Stack<Set<Order>> constraintStack=new Stack<Set<Order>>();
		constraintStack.push(constraints.get(0));
	if(!operator.isEmpty())
	{		
		 operatorStack.push(operator.get(0));
		for(int i=1;i<operator.size();i++)
		{
			constraintStack.push(constraints.get(i));
			int op=operatorStack.peek();
			if(op==1)
			{
				Set<Order> c1=constraintStack.pop();
				Set<Order> c2=constraintStack.pop();
				c1.retainAll(c2);
				constraintStack.push(c1);
				operatorStack.pop();
			}
			operatorStack.push(operator.get(i));
			
		}
		constraintStack.push(constraints.get(constraints.size()-1));
		while(!operatorStack.isEmpty())
		{
			int op=operatorStack.pop();
			Set<Order> c1=constraintStack.pop();
			Set<Order> c2=constraintStack.pop();
			if(op==1)
				c1.retainAll(c2);
		
			if(op==2)
				c1.addAll(c2);
		constraintStack.push(c1); 		
		}}
		return constraintStack.pop();
	}
	


}
