package view;

import java.util.ArrayList;
import java.util.List;

public class ListChoice<T> {
    
    private List<String> options;
    private List<T> values;

    public ListChoice() {
        options = new ArrayList<>();
        values = new ArrayList<>();
    }

    public void addOption(String option, T value) {
        options.add(option);
        values.add(value);
    }

    //<------ The following section was copied from the following link: https://github.com/Smileymans/Tema-Design-UCN-Spring-2016 ------>
    public T input(String title, boolean showCancelOption){
	    System.out.println(title);
	    if(showCancelOption){
		    System.out.println(" 0\t - Cancel");
	    }
	    for(int i = 0; i < options.size(); i++){
		    System.out.println(" " + (showCancelOption ? (i+1) : i) + "\t - " + this.options.get(i));
	    }
	    int choice = new Input().integerInput("Pick an option between 0 and " + (showCancelOption ? options.size() : options.size() - 1) + ": ");
	    if(showCancelOption && choice == 0){
		    return null;
    	}
	    if(showCancelOption){
	    	choice--;
	    }
	    if(choice >=0 && choice < options.size()){
		    return values.get(choice);
	    }
	    else{
		    return input(title, showCancelOption);
	    }
    }
}
